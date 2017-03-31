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
import com.kasirgalabs.etumulator.processor.Memory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class LinkerAndLoader extends ArmBaseVisitor<Void> {
    private final Memory memory;
    private final List<String> targetBranchSymbols;
    private final List<Symbol> definedBranchSymbols;
    private final List<String> targetDataSymbols;
    private final List<Symbol> definedDataSymbols;

    public LinkerAndLoader(Memory memory) {
        this.memory = memory;
        targetBranchSymbols = new ArrayList<>();
        definedBranchSymbols = new ArrayList<>();
        targetDataSymbols = new ArrayList<>();
        definedDataSymbols = new ArrayList<>();
    }

    @Override
    public Void visitB(ArmParser.BContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBeq(ArmParser.BeqContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBne(ArmParser.BneContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBcs(ArmParser.BcsContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBhs(ArmParser.BhsContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBcc(ArmParser.BccContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBlo(ArmParser.BloContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBmi(ArmParser.BmiContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBpl(ArmParser.BplContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBvs(ArmParser.BvsContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBvc(ArmParser.BvcContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBhi(ArmParser.BhiContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBls(ArmParser.BlsContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBge(ArmParser.BgeContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBlt(ArmParser.BltContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitBgt(ArmParser.BgtContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBle(ArmParser.BleContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBal(ArmParser.BalContext ctx) {
        targetBranchSymbols.add(ctx.LABEL().getText());
        return null;
    }

    @Override
    public Void visitBl(ArmParser.BlContext ctx) {
        return null;
    }

    @Override
    public Void visitLabel(ArmParser.LabelContext ctx) {
        Symbol symbol = new Symbol(ctx.LABEL().getText(), ctx.start.getLine() - 1);
        if(definedBranchSymbols.contains(symbol) || definedDataSymbols.contains(symbol)) {
            throw new LabelError("\"" + symbol + "\" is already defined.");
        }
        definedBranchSymbols.add(symbol);
        return null;
    }

    @Override
    public Void visitData(ArmParser.DataContext ctx) {
        String name = ctx.LABEL().getText();
        String asciz = ctx.asciz().STRING().getText().replaceAll("\"", "");
        int address = loadDataIntoMemory(asciz + "\n");
        Symbol symbol = new Symbol(name, address);
        if(definedBranchSymbols.contains(symbol) || definedDataSymbols.contains(symbol)) {
            throw new LabelError("\"" + symbol + "\" is already defined.");
        }
        definedDataSymbols.add(symbol);
        return null;
    }

    @Override
    public Void visitPcRelative(ArmParser.PcRelativeContext ctx) {
        targetDataSymbols.add(ctx.LABEL().getText());
        return null;
    }

    public ExecutableCode linkAndLoad(String code) {
        targetBranchSymbols.clear();
        definedBranchSymbols.clear();
        targetDataSymbols.clear();
        definedDataSymbols.clear();

        inspectCode(code);
        Set<Symbol> resolvedBranchSymbols
                = resolveSymbols(definedBranchSymbols, targetBranchSymbols);
        Set<Symbol> resolvedDataSymbols = resolveSymbols(definedDataSymbols, targetDataSymbols);
        Set<Symbol> temp = mergeSets(resolvedBranchSymbols, resolvedDataSymbols);
        return new ExecutableCode(code, temp);
    }

    private void inspectCode(String code) {
        ANTLRInputStream in = new ANTLRInputStream(code);
        ArmLexer lexer = new ArmLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArmParser parser = new ArmParser(tokens);
        ArmParser.ProgContext tree = parser.prog();
        visit(tree);
    }

    private Set<Symbol> resolveSymbols(List<Symbol> definedSymbols, List<String> targetSymbols) {
        final int NOT_USED = 0;
        Set<Symbol> resolvedSymbols = new HashSet<>();
        for(String targetSymbol : targetSymbols) {
            Symbol temp = new Symbol(targetSymbol, NOT_USED);
            if(definedSymbols.contains(temp)) {
                int index = definedSymbols.indexOf(temp);
                Symbol symbol = definedSymbols.get(index);
                resolvedSymbols.add(symbol);
                continue;
            }
            throw new LabelError("\"" + temp + "\" is not defined.");
        }
        return resolvedSymbols;
    }

    private Set<Symbol> mergeSets(Set<Symbol> resolvedBranchSymbols, Set<Symbol> resolvedDataSymbols) {
        Set<Symbol> temp = new HashSet<>();
        resolvedBranchSymbols.forEach((symbol) -> {
            temp.add(symbol);
        });
        resolvedDataSymbols.forEach((symbol) -> {
            temp.add(symbol);
        });
        return temp;
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
