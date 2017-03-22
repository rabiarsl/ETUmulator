/*
 * Copyright (C) 2017 Kasirgalabs
 *
 * Voidhis program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Voidhis program is distributed in the hope that it will be useful,
 * but WIVoidHOUVoid ANY WARRANVoidY; without even the implied warranty of
 * MERCHANVoidABILIVoidY or FIVoidNESS FOR A PARVoidICULAR PURPOSE.  See the
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

public class MultiplyAndDivideVisitor extends ArmBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final CPSR cpsr;
    private final RegisterVisitor registerVisitor;

    public MultiplyAndDivideVisitor(RegisterFile registerFile, CPSR cpsr) {
        this.registerFile = registerFile;
        this.cpsr = cpsr;
        registerVisitor = new RegisterVisitor();
    }

    @Override
    public Void visitMul(ArmParser.MulContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        registerFile.setValue(destRegister, left * right);
        return null;
    }

    @Override
    public Void visitMuls(ArmParser.MulsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        int result = left * right;
        cpsr.updateNZ(result);
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitMla(ArmParser.MlaContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        int result = left * right;
        result += registerFile.getValue(registerVisitor.visit(ctx.rn()));
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitMlas(ArmParser.MlasContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        int result = left * right;
        result += registerFile.getValue(registerVisitor.visit(ctx.rn()));
        cpsr.updateNZ(result);
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitMls(ArmParser.MlsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        int result = left * right;
        result = registerFile.getValue(registerVisitor.visit(ctx.rn())) - result;
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitSdiv(ArmParser.SdivContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        registerFile.setValue(destRegister, left / right);
        return null;
    }

    @Override
    public Void visitUdiv(ArmParser.UdivContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        registerFile.setValue(destRegister, Integer.divideUnsigned(left, right));
        return null;
    }
}
