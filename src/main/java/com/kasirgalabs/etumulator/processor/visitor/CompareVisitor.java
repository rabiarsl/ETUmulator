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
package com.kasirgalabs.etumulator.processor.visitor;

import com.kasirgalabs.arm.ArmBaseVisitor;
import com.kasirgalabs.arm.ArmParser;
import com.kasirgalabs.etumulator.processor.CPSR;
import com.kasirgalabs.etumulator.processor.RegisterFile;

public class CompareVisitor extends ArmBaseVisitor<Void> {

    private final RegisterFile registerFile;
    private final CPSR cpsr;
    private final RegisterVisitor registerVisitor;
    private final Operand2Visitor operand2Visitor;

    public CompareVisitor(RegisterFile registerFile, CPSR cpsr) {
        this.registerFile = registerFile;
        this.cpsr = cpsr;
        registerVisitor = new RegisterVisitor();
        operand2Visitor = new Operand2Visitor(registerFile);
    }

    @Override
    public Void visitCmp(ArmParser.CmpContext ctx) {
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        addUpdateCPSR(left, -right);
        return null;
    }

    @Override
    public Void visitCmn(ArmParser.CmnContext ctx) {
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        addUpdateCPSR(left, right);
        return null;
    }

    private int addUpdateCPSR(int left, int right) {
        int result;
        boolean overflow;
        try {
            result = Math.addExact(left, right);
            overflow = false;
        } catch(ArithmeticException e) {
            overflow = true;
            result = left + right;
        }
        cpsr.setOverflow(overflow);
        cpsr.updateNZ(result);
        return result;
    }
}
