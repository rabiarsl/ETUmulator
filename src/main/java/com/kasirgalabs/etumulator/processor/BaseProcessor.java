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
package com.kasirgalabs.etumulator.processor;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kasirgalabs.arm.ArmBaseVisitor;
import com.kasirgalabs.arm.ArmLexer;
import com.kasirgalabs.arm.ArmParser;
import com.kasirgalabs.etumulator.linker.Symbol;
import com.kasirgalabs.etumulator.processor.visitor.ArithmeticVisitor;
import com.kasirgalabs.etumulator.processor.visitor.BranchVisitor;
import com.kasirgalabs.etumulator.processor.visitor.CompareVisitor;
import com.kasirgalabs.etumulator.processor.visitor.LogicalVisitor;
import com.kasirgalabs.etumulator.processor.visitor.MoveVisitor;
import com.kasirgalabs.etumulator.processor.visitor.MultiplyAndDivideVisitor;
import com.kasirgalabs.etumulator.processor.visitor.ShiftVisitor;
import com.kasirgalabs.etumulator.processor.visitor.SingleDataMemoryVisitor;
import com.kasirgalabs.etumulator.processor.visitor.StackVisitor;
import java.util.Set;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

@Singleton
public class BaseProcessor extends ArmBaseVisitor<Void> implements Processor {
    private final ArithmeticVisitor arithmeticVisitor;
    private final MultiplyAndDivideVisitor multiplyAndDivideVisitor;
    private final MoveVisitor moveVisitor;
    private final ShiftVisitor shiftVisitor;
    private final CompareVisitor compareVisitor;
    private final LogicalVisitor logicalVisitor;
    private final BranchVisitor branchVisitor;
    private final SingleDataMemoryVisitor singleDataMemoryVisitor;
    private final StackVisitor stackVisitor;
    private int pc;

    @Inject
    public BaseProcessor(ProcessorUnits processorUnits) {
        arithmeticVisitor
                = new ArithmeticVisitor(processorUnits.getRegisterFile(), processorUnits.getCPSR());
        multiplyAndDivideVisitor = new MultiplyAndDivideVisitor(processorUnits
                .getRegisterFile(), processorUnits.getCPSR());
        moveVisitor = new MoveVisitor(processorUnits
                .getRegisterFile(), processorUnits.getCPSR());
        shiftVisitor = new ShiftVisitor(processorUnits
                .getRegisterFile(), processorUnits.getCPSR());
        compareVisitor = new CompareVisitor(processorUnits
                .getRegisterFile(), processorUnits.getCPSR());
        logicalVisitor = new LogicalVisitor(processorUnits
                .getRegisterFile(), processorUnits.getCPSR());
        branchVisitor = new BranchVisitor(processorUnits.getCPSR(), processorUnits.getUART());
        singleDataMemoryVisitor = new SingleDataMemoryVisitor(processorUnits
                .getRegisterFile(), processorUnits.getMemory());
        stackVisitor = new StackVisitor(processorUnits
                .getRegisterFile(), processorUnits.getStack());
        pc = 0;
    }

    @Override
    public Void visitArithmetic(ArmParser.ArithmeticContext ctx) {
        return arithmeticVisitor.visit(ctx);
    }

    @Override
    public Void visitMultiplyAndDivide(ArmParser.MultiplyAndDivideContext ctx) {
        return multiplyAndDivideVisitor.visit(ctx);
    }

    @Override
    public Void visitMove(ArmParser.MoveContext ctx) {
        return moveVisitor.visit(ctx);
    }

    @Override
    public Void visitShift(ArmParser.ShiftContext ctx) {
        return shiftVisitor.visit(ctx);
    }

    @Override
    public Void visitCompare(ArmParser.CompareContext ctx) {
        return compareVisitor.visit(ctx);
    }

    @Override
    public Void visitLogical(ArmParser.LogicalContext ctx) {
        return logicalVisitor.visit(ctx);
    }

    @Override
    public Void visitBranch(ArmParser.BranchContext ctx) {
        Integer address = branchVisitor.visit(ctx);
        if(address != null) {
            pc = address;
        }
        return null;
    }

    @Override
    public Void visitSingleDataMemory(ArmParser.SingleDataMemoryContext ctx) {
        return singleDataMemoryVisitor.visit(ctx);
    }

    @Override
    public Void visitStack(ArmParser.StackContext ctx) {
        return stackVisitor.visit(ctx);
    }

    @Override
    public void run(String code, Set<Symbol> symbols) {
        pc = 0;
        branchVisitor.setSymbols(symbols);
        singleDataMemoryVisitor.setSymbols(symbols);
        final char[][] instructions = parseInstructions(code);
        while(pc < instructions.length) {
            char[] instruction = instructions[pc];
            execute(instruction);
            pc++;
        }
    }

    @Override
    public void stop() {
    }

    private char[][] parseInstructions(String code) {
        String[] parts = code.split("\\n");
        char[][] instructions = new char[parts.length][];
        for(int i = 0; i < instructions.length; i++) {
            instructions[i] = (parts[i] + "\n").toCharArray();
        }
        return instructions;
    }

    private void execute(char[] instruction) {
        ANTLRInputStream in = new ANTLRInputStream(instruction, instruction.length);
        ArmLexer lexer = new ArmLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArmParser parser = new ArmParser(tokens);
        ArmParser.ProgContext tree = parser.prog();
        this.visit(tree);
    }
}
