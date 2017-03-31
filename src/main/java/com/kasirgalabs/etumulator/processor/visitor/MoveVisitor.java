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

public class MoveVisitor extends ArmBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final CPSR cpsr;
    private final RegisterVisitor registerVisitor;
    private final Operand2Visitor operand2Visitor;
    private final NumberVisitor numberVisitor;

    public MoveVisitor(RegisterFile registerFile, CPSR cpsr) {
        this.registerFile = registerFile;
        this.cpsr = cpsr;
        registerVisitor = new RegisterVisitor();
        operand2Visitor = new Operand2Visitor(registerFile);
        numberVisitor = new NumberVisitor();
    }

    @Override
    public Void visitMov(ArmParser.MovContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value;
        if(ctx.imm16() != null) {
            value = numberVisitor.visit(ctx.imm16());
        }
        else {
            value = operand2Visitor.visit(ctx.operand2());
        }
        registerFile.setValue(destRegister, value);
        return null;
    }

    @Override
    public Void visitMovs(ArmParser.MovsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = operand2Visitor.visit(ctx.operand2());
        cpsr.updateNZ(value);
        registerFile.setValue(destRegister, value);
        return null;
    }

    @Override
    public Void visitMvn(ArmParser.MvnContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = 0xffff_ffff ^ operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, value);
        return null;
    }

    @Override
    public Void visitMvns(ArmParser.MvnsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = 0xffff_ffff ^ operand2Visitor.visit(ctx.operand2());
        cpsr.updateNZ(value);
        registerFile.setValue(destRegister, value);
        return null;
    }

    @Override
    public Void visitMovt(ArmParser.MovtContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = registerFile.getValue(destRegister);
        value &= 0x0000_ffff;
        value |= numberVisitor.visit(ctx.imm16()) << 16;
        registerFile.setValue(destRegister, value);
        return null;
    }
}
