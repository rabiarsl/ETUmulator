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
package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.etumulator.processor.CPSR;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class ArithmeticVisitor extends ProcessorBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final CPSR cpsr;
    private final RegisterVisitor registerVisitor;
    private final Operand2Visitor operand2Visitor;
    private final NumberVisitor numberVisitor;

    public ArithmeticVisitor(RegisterFile registerFile, CPSR cpsr) {
        this.registerFile = registerFile;
        this.cpsr = cpsr;
        registerVisitor = new RegisterVisitor();
        operand2Visitor = new Operand2Visitor(registerFile);
        numberVisitor = new NumberVisitor();
    }

    @Override
    public Void visitAdd(ProcessorParser.AddContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right;
        if(ctx.imm12() != null) {
            right = numberVisitor.visit(ctx.imm12());
        }
        else {
            right = operand2Visitor.visit(ctx.operand2());
        }
        registerFile.setValue(destRegister, left + right);
        return null;
    }

    @Override
    public Void visitAdds(ProcessorParser.AddsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = addUpdateCPSR(left, right);
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitAdc(ProcessorParser.AdcContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = left + right;
        if(cpsr.isCarry()) {
            result++;
        }
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitAdcs(ProcessorParser.AdcsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = addUpdateCPSR(left, right);
        if(cpsr.isCarry()) {
            if(!cpsr.isOverflow()) {
                result = addUpdateCPSR(result, 1);
            }
            else {
                result++;
            }
        }
        cpsr.updateNZ(result);
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitSub(ProcessorParser.SubContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right;
        if(ctx.imm12() != null) {
            right = numberVisitor.visit(ctx.imm12());
        }
        else {
            right = operand2Visitor.visit(ctx.operand2());
        }
        registerFile.setValue(destRegister, left - right);
        return null;
    }

    @Override
    public Void visitSubs(ProcessorParser.SubsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = addUpdateCPSR(left, -right);
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitSbc(ProcessorParser.SbcContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = left - right;
        if(!cpsr.isCarry()) {
            result--;
        }
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitSbcs(ProcessorParser.SbcsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = addUpdateCPSR(left, -right);
        if(!cpsr.isCarry()) {
            if(!cpsr.isOverflow()) {
                result = addUpdateCPSR(result, -1);
            }
            else {
                result--;
            }
        }
        cpsr.updateNZ(result);
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitRsb(ProcessorParser.RsbContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, right - left);
        return null;
    }

    @Override
    public Void visitRsbs(ProcessorParser.RsbsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = addUpdateCPSR(right, -left);
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitRsc(ProcessorParser.RscContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = right - left;
        if(!cpsr.isCarry()) {
            result--;
        }
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitRscs(ProcessorParser.RscsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = addUpdateCPSR(right, -left);
        if(!cpsr.isCarry()) {
            if(!cpsr.isOverflow()) {
                result = addUpdateCPSR(result, -1);
            }
            else {
                result--;
            }
        }
        cpsr.updateNZ(result);
        registerFile.setValue(destRegister, result);
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
