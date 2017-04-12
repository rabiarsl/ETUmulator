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
package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.etumulator.processor.CPSR;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class MultiplyAndDivideVisitor extends ProcessorBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final CPSR cpsr;
    private final RegisterVisitor registerVisitor;

    public MultiplyAndDivideVisitor(RegisterFile registerFile, CPSR cpsr) {
        this.registerFile = registerFile;
        this.cpsr = cpsr;
        registerVisitor = new RegisterVisitor();
    }

    @Override
    public Void visitMul(ProcessorParser.MulContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        registerFile.setValue(destRegister, left * right);
        return null;
    }

    @Override
    public Void visitMuls(ProcessorParser.MulsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        int result = left * right;
        cpsr.updateNZ(result);
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitMla(ProcessorParser.MlaContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        int result = left * right;
        result += registerFile.getValue(registerVisitor.visit(ctx.rn()));
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitMlas(ProcessorParser.MlasContext ctx) {
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
    public Void visitMls(ProcessorParser.MlsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        int result = left * right;
        result = registerFile.getValue(registerVisitor.visit(ctx.rn())) - result;
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitSdiv(ProcessorParser.SdivContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        registerFile.setValue(destRegister, left / right);
        return null;
    }

    @Override
    public Void visitUdiv(ProcessorParser.UdivContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        registerFile.setValue(destRegister, Integer.divideUnsigned(left, right));
        return null;
    }
}
