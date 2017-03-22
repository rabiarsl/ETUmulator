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

import com.kasirgalabs.arm.ArmBaseVisitor;
import com.kasirgalabs.arm.ArmLexer;
import com.kasirgalabs.arm.ArmParser;
import com.kasirgalabs.etumulator.processor.Memory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Linker extends ArmBaseVisitor<Void> {
    private final Memory memory;
    private final List<String> targetSymbols;
    private final List<Symbol> definedSymbols;
    private final List<Symbol> dataSymbols;

    public Linker(Memory memory) {
        this.memory = memory;
        targetSymbols = new ArrayList<>();
        definedSymbols = new ArrayList<>();
        dataSymbols = new ArrayList<>();
    }

    @Override
    public Void visitB(ArmParser.BContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBeq(ArmParser.BeqContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBne(ArmParser.BneContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBcs(ArmParser.BcsContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBhs(ArmParser.BhsContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBcc(ArmParser.BccContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBlo(ArmParser.BloContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBmi(ArmParser.BmiContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBpl(ArmParser.BplContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBvs(ArmParser.BvsContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBvc(ArmParser.BvcContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBhi(ArmParser.BhiContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBls(ArmParser.BlsContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBge(ArmParser.BgeContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBlt(ArmParser.BltContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitBgt(ArmParser.BgtContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBle(ArmParser.BleContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBal(ArmParser.BalContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBl(ArmParser.BlContext ctx) {
        targetSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitLabel(ArmParser.LabelContext ctx) {
        Symbol symbol = new Symbol(ctx.LABEL().getText(), ctx.start.getLine() - 1);
        definedSymbols.add(symbol);
        return null;
    }

    @Override
    public Void visitData(ArmParser.DataContext ctx) {
        String name = ctx.label().LABEL().getText();
        String asciz = ctx.asciz().STRING().getText().replaceAll("\"", "");
        int address = loadDataIntoMemory(asciz + "\n");
        Symbol symbol = new Symbol(name, address);
        dataSymbols.add(symbol);
        return visitChildren(ctx);
    }

    public Set<Symbol> link(String code) {
        definedSymbols.clear();
        targetSymbols.clear();

        inspectCode(code);
        char[][] instructions = parseInstructions(code);
        Set<Symbol> resolvedSymbols = resolveSymbols(instructions);
        resolvedSymbols.addAll(dataSymbols);
        return resolvedSymbols;
    }

    private void inspectCode(String code) {
        ANTLRInputStream in = new ANTLRInputStream(code);
        ArmLexer lexer = new ArmLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArmParser parser = new ArmParser(tokens);
        ArmParser.ProgContext tree = parser.prog();
        visit(tree);
    }

    private char[][] parseInstructions(String code) {
        String[] parts = code.split("\\n");
        char[][] instructions = new char[parts.length][];
        for(int i = 0; i < instructions.length; i++) {
            instructions[i] = (parts[i] + "\n").toCharArray();
        }
        return instructions;
    }

    private Set<Symbol> resolveSymbols(char[][] instructions) {
        Set<Symbol> resolvedSymbols = new HashSet<>();
        targetSymbols.forEach((targetSymbol) -> {
            definedSymbols.forEach((definedSymbol) -> {
                if(definedSymbol.getName().equals(targetSymbol)) {
                    resolvedSymbols.add(definedSymbol);
                }
            });
        });
        return resolvedSymbols;
    }

    private int resolveAddress(String targetSymbol, char[][] instructions) {
        int address = 0;
        while(address < instructions.length) {
            if(new String(instructions[address]).contains(targetSymbol)) {
                return address;
            }
            address++;
        }
        return address;
    }

    private int loadDataIntoMemory(String asciz) {
        boolean addressNotFound = true;
        Random rand = new Random();
        int address = 0;
        while(addressNotFound) {
            address = rand.nextInt(Integer.MAX_VALUE - asciz.length());
            for(int i = 0; i < asciz.length(); i++) {
                if(!memory.isAddressEmpty(address + i)) {
                    break;
                }
                memory.set(address + i, (byte) asciz.charAt(i));
                if(i == asciz.length() - 1) {
                    addressNotFound = false;
                }
            }
        }
        return address;
    }
}
