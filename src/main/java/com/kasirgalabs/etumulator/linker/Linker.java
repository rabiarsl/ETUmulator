/*
 * Copyright (C) 2017 Kasirgalabs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kasirgalabs.etumulator.linker;

import com.kasirgalabs.arm.ArmBaseListener;
import com.kasirgalabs.arm.ArmLexer;
import com.kasirgalabs.arm.ArmParser;
import com.kasirgalabs.etumulator.processor.InstructionUnit;
import com.kasirgalabs.etumulator.processor.MemoryUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Linker extends ArmBaseListener {
    private final InstructionUnit instructionUnit;
    private final MemoryUnit memoryUnit;
    private final List<Label> definedBranchLabels;
    private final List<Label> targetBranchLabels;
    private final List<Data> definedData;
    private final List<Data> targetData;

    public Linker(InstructionUnit instructionUnit, MemoryUnit memoryUnit) {
        this.instructionUnit = instructionUnit;
        this.memoryUnit = memoryUnit;
        definedBranchLabels = new ArrayList<>();
        targetBranchLabels = new ArrayList<>();
        definedData = new ArrayList<>();
        targetData = new ArrayList<>();
    }

    @Override
    public void exitLabel(ArmParser.LabelContext ctx) {
        Label label = new Label(ctx.LABEL().getText());
        label.setAddress(ctx.getStart().getLine() - 1);
        if(definedBranchLabels.contains(label)) {
            return;
        }
        definedBranchLabels.add(label);
    }

    @Override
    public void exitB(ArmParser.BContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBeq(ArmParser.BeqContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBne(ArmParser.BneContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBcs(ArmParser.BcsContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBhs(ArmParser.BhsContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBcc(ArmParser.BccContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBlo(ArmParser.BloContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBmi(ArmParser.BmiContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBpl(ArmParser.BplContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBvs(ArmParser.BvsContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBvc(ArmParser.BvcContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBhi(ArmParser.BhiContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBls(ArmParser.BlsContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBge(ArmParser.BgeContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBlt(ArmParser.BltContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBgt(ArmParser.BgtContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBle(ArmParser.BleContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBal(ArmParser.BalContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBl(ArmParser.BlContext ctx) {
        targetBranchLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitData(ArmParser.DataContext ctx) {
        String label = ctx.label().LABEL().getText();
        String text = ctx.asciz().getChild(1).getText();
        Data data = new Data(new Label(label));
        data.setData(text);
        if(definedData.contains(data)) {
            return;
        }
        definedData.add(data);
    }

    @Override
    public void exitLdr(ArmParser.LdrContext ctx) {
        if(ctx.LABEL() != null) {
            Label label = new Label(ctx.LABEL().getText());
            targetData.add(new Data(label));
        }
    }

    public void linkAndLoad(String code) {
        definedBranchLabels.clear();
        targetBranchLabels.clear();
        inspectCode(code);

        char[][] instructions = parseInstructions(code);
        List<Label> resolvedBranchLabels = resolveBranchLabels();
        instructionUnit.loadLabels(resolvedBranchLabels);
        instructionUnit.loadInstructions(instructions);

        List<Data> resolvedData = resolveData();
        if(resolvedData.size() != 0) {
            memoryUnit.loadData(resolvedData);
        }
    }

    private void inspectCode(String code) {
        ANTLRInputStream in = new ANTLRInputStream(code.toCharArray(), code.length());
        ArmLexer lexer = new ArmLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArmParser parser = new ArmParser(tokens);
        ArmParser.ProgContext tree = parser.prog();
        ParseTreeWalker.DEFAULT.walk(this, tree);
    }

    private char[][] parseInstructions(String code) {
        String[] parts = code.split("\\n");
        char[][] instructions = new char[parts.length][];
        for(int i = 0; i < instructions.length; i++) {
            instructions[i] = (parts[i] + "\n").toCharArray();
        }
        return instructions;
    }

    private List<Label> resolveBranchLabels() {
        List<Label> resolvedLabels = new ArrayList<>();
        for(Label label : targetBranchLabels) {
            if(definedBranchLabels.contains(label)) {
                resolvedLabels.add(definedBranchLabels.get(definedBranchLabels.indexOf(label)));
                continue;
            }
            return null;
        }
        return resolvedLabels;
    }

    private List<Data> resolveData() {
        List<Data> resolvedData = new ArrayList<>();
        for(Data data : targetData) {
            if(definedData.contains(data)) {
                Data temp = definedData.get(definedData.indexOf(data));
                loadIntoMemory(temp);
                resolvedData.add(definedData.get(definedData.indexOf(data)));
                continue;
            }
            return null;
        }
        return resolvedData;
    }

    private void loadIntoMemory(Data data) {
        boolean addressNotFound = true;
        Random rand = new Random();
        while(addressNotFound) {
            int address = rand.nextInt(Integer.MAX_VALUE - data.getData().length()) + 0;
            for(int i = 0; i < data.getData().length(); i++) {
                if(memoryUnit.getData(address + i) != null) {
                    break;
                }
                memoryUnit.setData(address + i, (byte) data.getData().charAt(i));
                if(i == data.getData().length() - 1) {
                    addressNotFound = false;
                }
            }
        }
    }
}
