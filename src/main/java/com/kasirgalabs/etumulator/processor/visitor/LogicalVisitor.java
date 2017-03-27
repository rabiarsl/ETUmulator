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

public class LogicalVisitor extends ArmBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final CPSR cpsr;
    private final RegisterVisitor registerVisitor;
    private final Operand2Visitor operand2Visitor;

    public LogicalVisitor(RegisterFile registerFile, CPSR cpsr) {
        this.registerFile = registerFile;
        this.cpsr = cpsr;
        registerVisitor = new RegisterVisitor();
        operand2Visitor = new Operand2Visitor(registerFile);
    }

    @Override
    public Void visitTst(ArmParser.TstContext ctx) {
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        cpsr.updateNZ(left & right);
        return null;
    }

    @Override
    public Void visitTeq(ArmParser.TeqContext ctx) {
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        cpsr.updateNZ(left ^ right);
        return null;
    }

    @Override
    public Void visitAnd(ArmParser.AndContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, left & right);
        return null;
    }

    @Override
    public Void visitAnds(ArmParser.AndsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, cpsr.updateNZ(left & right));
        return null;
    }

    @Override
    public Void visitEor(ArmParser.EorContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, left ^ right);
        return null;
    }

    @Override
    public Void visitEors(ArmParser.EorsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, cpsr.updateNZ(left ^ right));
        return null;
    }

    @Override
    public Void visitOrr(ArmParser.OrrContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, left | right);
        return null;
    }

    @Override
    public Void visitOrrs(ArmParser.OrrsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, cpsr.updateNZ(left | right));
        return null;
    }

    @Override
    public Void visitOrn(ArmParser.OrnContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, left | ~right);
        return null;
    }

    @Override
    public Void visitOrns(ArmParser.OrnsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, cpsr.updateNZ(left | ~right));
        return null;
    }

    @Override
    public Void visitBic(ArmParser.BicContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, left & ~right);
        return null;
    }

    @Override
    public Void visitBics(ArmParser.BicsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, cpsr.updateNZ(left & ~right));
        return null;
    }
}
