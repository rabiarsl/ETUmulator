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

import com.kasirgalabs.arm.ProcessorBaseVisitor;
import com.kasirgalabs.arm.ProcessorLexer;
import com.kasirgalabs.arm.ProcessorParser;
import com.kasirgalabs.etumulator.langtools.ExecutableCode;
import com.kasirgalabs.etumulator.processor.visitor.ArithmeticVisitor;
import com.kasirgalabs.etumulator.processor.visitor.BranchVisitor;
import com.kasirgalabs.etumulator.processor.visitor.CompareVisitor;
import com.kasirgalabs.etumulator.processor.visitor.LogicalVisitor;
import com.kasirgalabs.etumulator.processor.visitor.MoveVisitor;
import com.kasirgalabs.etumulator.processor.visitor.MultiplyAndDivideVisitor;
import com.kasirgalabs.etumulator.processor.visitor.ShiftVisitor;
import com.kasirgalabs.etumulator.processor.visitor.SingleDataMemoryVisitor;
import com.kasirgalabs.etumulator.processor.visitor.StackVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class BaseProcessor extends ProcessorBaseVisitor<Void> implements Processor {
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
    public Void visitArithmetic(ProcessorParser.ArithmeticContext ctx) {
        return arithmeticVisitor.visit(ctx);
    }

    @Override
    public Void visitMultiplyAndDivide(ProcessorParser.MultiplyAndDivideContext ctx) {
        return multiplyAndDivideVisitor.visit(ctx);
    }

    @Override
    public Void visitMove(ProcessorParser.MoveContext ctx) {
        return moveVisitor.visit(ctx);
    }

    @Override
    public Void visitShift(ProcessorParser.ShiftContext ctx) {
        return shiftVisitor.visit(ctx);
    }

    @Override
    public Void visitCompare(ProcessorParser.CompareContext ctx) {
        return compareVisitor.visit(ctx);
    }

    @Override
    public Void visitLogical(ProcessorParser.LogicalContext ctx) {
        return logicalVisitor.visit(ctx);
    }

    @Override
    public Void visitBranch(ProcessorParser.BranchContext ctx) {
        Integer address = branchVisitor.visit(ctx);
        if(address != null) {
            pc = address;
        }
        return null;
    }

    @Override
    public Void visitSingleDataMemory(ProcessorParser.SingleDataMemoryContext ctx) {
        return singleDataMemoryVisitor.visit(ctx);
    }

    @Override
    public Void visitStack(ProcessorParser.StackContext ctx) {
        return stackVisitor.visit(ctx);
    }

    @Override
    public void run(ExecutableCode executableCode) {
        pc = 0;
        final char[][] instructions = executableCode.getCode();
        while(pc < instructions.length) {
            char[] instruction = instructions[pc];
            execute(instruction);
            pc++;
        }
    }

    private void execute(char[] instruction) {
        ANTLRInputStream in = new ANTLRInputStream(instruction, instruction.length);
        ProcessorLexer lexer = new ProcessorLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ProcessorParser parser = new ProcessorParser(tokens);
        ProcessorParser.ProgContext tree = parser.prog();
        this.visit(tree);
    }
}
