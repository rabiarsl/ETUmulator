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

import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class LdrAddressVisitor extends ProcessorBaseVisitor<Integer> {
    private final RegisterFile registerFile;
    private final RegisterVisitor registerVisitor;
    private final NumberVisitor numberVisitor;

    public LdrAddressVisitor(RegisterFile registerFile) {
        this.registerFile = registerFile;
        registerVisitor = new RegisterVisitor();
        numberVisitor = new NumberVisitor();
    }

    @Override
    public Integer visitLdrAddress(ProcessorParser.LdrAddressContext ctx) {
        if(ctx.immediateOffset() != null) {
            return visitImmediateOffset(ctx.immediateOffset());
        }
        else if(ctx.postIndexedImmediate() != null) {
            return visit(ctx.postIndexedImmediate());
        }
        else if(ctx.registerOffset() != null) {
            return visit(ctx.registerOffset());
        }
        else {
            return visit(ctx.postIndexedRegister());
        }
    }

    @Override
    public Integer visitImmediateOffset(ProcessorParser.ImmediateOffsetContext ctx) {
        int value = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        if(ctx.offset() != null) {
            value += numberVisitor.visit(ctx.offset());
        }
        return value;
    }

    @Override
    public Integer visitPostIndexedImmediate(ProcessorParser.PostIndexedImmediateContext ctx) {
        String srcRegister = registerVisitor.visit(ctx.rn());
        int value = registerFile.getValue(srcRegister);
        int offset = numberVisitor.visit(ctx.offset());
        registerFile.setValue(srcRegister, value + offset);
        return value;
    }

    @Override
    public Integer visitRegisterOffset(ProcessorParser.RegisterOffsetContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rn());
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        if(ctx.opsh() != null) {
            int shiftAmount = numberVisitor.visit(ctx.opsh().sh().number());
            value <<= shiftAmount;
        }
        return registerFile.getValue(destRegister) + value;
    }

    @Override
    public Integer visitPostIndexedRegister(ProcessorParser.PostIndexedRegisterContext ctx) {
        String srcRegister = registerVisitor.visit(ctx.rn());
        int value = registerFile.getValue(srcRegister);
        int offset = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        if(ctx.opsh() != null) {
            int shiftAmount = numberVisitor.visit(ctx.opsh().sh().number());
            offset <<= shiftAmount;
        }
        registerFile.setValue(srcRegister, value + offset);
        return value;
    }
}
