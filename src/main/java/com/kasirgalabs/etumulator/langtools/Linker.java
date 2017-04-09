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
package com.kasirgalabs.etumulator.langtools;

import com.kasirgalabs.arm.ArmBaseVisitor;
import com.kasirgalabs.arm.ArmLexer;
import com.kasirgalabs.arm.ArmParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Linker extends ArmBaseVisitor<Void> {
    private final Map<String, Integer> definedBranches;
    private final Map<String, Data> definedData;
    private final Set<Integer> addressBook;
    private boolean secondPass;
    private char[][] code;

    public Linker() {
        definedBranches = new HashMap<>();
        definedData = new HashMap<>();
        addressBook = new HashSet<>();
    }

    @Override
    public Void visitB(ArmParser.BContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBeq(ArmParser.BeqContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBne(ArmParser.BneContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBcs(ArmParser.BcsContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBhs(ArmParser.BhsContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBcc(ArmParser.BccContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBlo(ArmParser.BloContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBmi(ArmParser.BmiContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBpl(ArmParser.BplContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBvs(ArmParser.BvsContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBvc(ArmParser.BvcContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBhi(ArmParser.BhiContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBls(ArmParser.BlsContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBge(ArmParser.BgeContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBlt(ArmParser.BltContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return visitChildren(ctx);
    }

    @Override
    public Void visitBgt(ArmParser.BgtContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBle(ArmParser.BleContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());

        return null;
    }

    @Override
    public Void visitBal(ArmParser.BalContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBl(ArmParser.BlContext ctx) {
        String label = ctx.LABEL().getText();
        if(!"uart_read".equalsIgnoreCase(label) && !"uart_write".equalsIgnoreCase(label)) {
            throw new UnsupportedInstructionError("bl instruction target only supported for "
                    + "\"uart_read\" an \"uart_write\"");
        }
        return null;
    }

    @Override
    public Void visitPcRelative(ArmParser.PcRelativeContext ctx) {
        if(!secondPass) {
            return null;
        }
        String label = ctx.LABEL().getText();
        if(!definedData.containsKey(label)) {
            throw new LabelError("\"" + label + "\" is not defined.");
        }
        int lineNumber = ctx.start.getLine() - 1;
        String temp = new String(code[lineNumber]);
        String address = Integer.toString(definedData.get(label).getAddress());
        temp = temp.replace(label, "#" + address);
        code[lineNumber] = temp.toCharArray();
        return null;
    }

    @Override
    public Void visitLabel(ArmParser.LabelContext ctx) {
        if(secondPass) {
            return null;
        }
        String label = ctx.LABEL().getText();
        if(definedData.containsKey(label) || definedData.containsKey(label)) {
            throw new LabelError("\"" + label + "\" is already defined.");
        }
        int address = ctx.start.getLine() - 1;
        definedBranches.put(label, address);
        return null;
    }

    @Override
    public Void visitData(ArmParser.DataContext ctx) {
        if(secondPass) {
            return null;
        }
        String label = ctx.LABEL().getText();
        if(definedBranches.containsKey(label) || definedData.containsKey(label)) {
            throw new LabelError("\"" + label + "\" is already defined.");
        }
        String asciz = ctx.asciz().STRING().getText().replaceAll("\"", "") + "\n";
        int address = generateAddress(asciz);
        definedData.put(label, new Data(asciz, address));
        return null;
    }

    public ExecutableCode link(String code) throws SyntaxError, LabelError,
            UnsupportedInstructionError {
        definedBranches.clear();
        definedData.clear();
        addressBook.clear();
        secondPass = false;
        this.code = parseCode(code);
        ANTLRInputStream in = new ANTLRInputStream(code);
        ArmLexer lexer = new ArmLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArmParser parser = new ArmParser(tokens);
        ArmParser.ProgContext program = parser.prog();
        if(parser.getNumberOfSyntaxErrors() > 0) {
            throw new SyntaxError("You have error(s) in your code.");
        }
        visit(program);
        secondPass = true;
        visit(program);
        List<Data> temp = new ArrayList<>(definedData.size());
        definedData.forEach((label, data) -> {
            temp.add(data);
        });
        return new ExecutableCode(this.code, temp);
    }

    private char[][] parseCode(String code) {
        String[] parts = code.split("\\n");
        char[][] instructions = new char[parts.length][];
        for(int i = 0; i < instructions.length; i++) {
            instructions[i] = (parts[i] + "\n").toCharArray();
        }
        return instructions;
    }

    private void replaceLabelAddress(ParserRuleContext ctx, TerminalNode terminalNode) {
        if(!secondPass) {
            return;
        }
        String label = terminalNode.getText();
        if(!definedBranches.containsKey(label)) {
            throw new LabelError("\"" + label + "\" is not defined.");
        }
        int lineNumber = ctx.start.getLine() - 1;
        String temp = new String(code[lineNumber]);
        String address = Integer.toString(definedBranches.get(label));
        temp = temp.replace(label, address);
        code[lineNumber] = temp.toCharArray();
    }

    private int generateAddress(String data) {
        boolean addressNotFound = true;
        Random rand = new Random();
        int address = 0;
        while(addressNotFound) {
            address = rand.nextInt(Integer.MAX_VALUE - data.length());
            for(int i = 0; i < data.length(); i++) {
                if(addressBook.contains(address + i)) {
                    break;
                }
                if(i == data.length() - 1) {
                    addressNotFound = false;
                }
            }
        }
        return address;
    }
}
