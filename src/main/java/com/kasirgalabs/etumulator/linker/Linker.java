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
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Linker extends ArmBaseListener {
    private final InstructionUnit instructionUnit;
    private final List<Label> definedLabels;
    private final List<Label> targetLabels;

    public Linker(InstructionUnit instructionUnit) {
        this.instructionUnit = instructionUnit;
        definedLabels = new ArrayList<>();
        targetLabels = new ArrayList<>();
    }

    @Override
    public void exitLabel(ArmParser.LabelContext ctx) {
        Label label = new Label(ctx.LABEL().getText());
        label.setAddress(ctx.getStart().getLine() - 1);
        if(definedLabels.contains(label)) {
            return;
        }
        definedLabels.add(label);
    }

    @Override
    public void exitB(ArmParser.BContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBeq(ArmParser.BeqContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBne(ArmParser.BneContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBcs(ArmParser.BcsContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBhs(ArmParser.BhsContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBcc(ArmParser.BccContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBlo(ArmParser.BloContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBmi(ArmParser.BmiContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBpl(ArmParser.BplContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBvs(ArmParser.BvsContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBvc(ArmParser.BvcContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBhi(ArmParser.BhiContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBls(ArmParser.BlsContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBge(ArmParser.BgeContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBlt(ArmParser.BltContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBgt(ArmParser.BgtContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBle(ArmParser.BleContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBal(ArmParser.BalContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    @Override
    public void exitBl(ArmParser.BlContext ctx) {
        targetLabels.add(new Label(ctx.LABEL().getText()));
    }

    public void linkAndLoad(String code) {
        definedLabels.clear();
        targetLabels.clear();
        List<Label> resolvedLabels = resolveLabels(code);
        char[][] instructions = parseInstructions(code);
        instructionUnit.loadLabels(resolvedLabels);
        instructionUnit.loadInstructions(instructions);
    }

    private List<Label> resolveLabels(String code) {
        ANTLRInputStream in = new ANTLRInputStream(code.toCharArray(), code.length());
        ArmLexer lexer = new ArmLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArmParser parser = new ArmParser(tokens);
        ArmParser.ProgContext tree = parser.prog();
        ParseTreeWalker.DEFAULT.walk(this, tree);
        List<Label> resolvedLabels = new ArrayList<Label>();
        for(Label label : targetLabels) {
            if(definedLabels.contains(label)) {
                resolvedLabels.add(definedLabels.get(definedLabels.indexOf(label)));
                continue;
            }
            return null;
        }
        return resolvedLabels;
    }

    private char[][] parseInstructions(String code) {
        String[] parts = code.split("\\n");
        char[][] instructions = new char[parts.length][];
        for(int i = 0; i < instructions.length; i++) {
            instructions[i] = (parts[i] + "\n").toCharArray();
        }
        return instructions;
    }
}
