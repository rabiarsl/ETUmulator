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

import com.kasirgalabs.arm.ProcessorBaseVisitor;
import com.kasirgalabs.arm.ProcessorParser;
import com.kasirgalabs.etumulator.processor.RegisterFile;

public class ShiftedRegisterVisitor extends ProcessorBaseVisitor<Integer> {
    private static final int ASR = 0;
    private static final int LSL = 1;
    private static final int LSR = 2;
    private static final int ROR = 3;
    private final RegisterFile registerFile;
    private final RegisterVisitor registerVisitor;
    private final NumberVisitor numberVisitor;

    public ShiftedRegisterVisitor(RegisterFile registerFile) {
        this.registerFile = registerFile;
        registerVisitor = new RegisterVisitor();
        numberVisitor = new NumberVisitor();
    }

    @Override
    public Integer visitRegisterShiftedByRegister(
            ProcessorParser.RegisterShiftedByRegisterContext ctx) {
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int shiftOption = visitShiftOption(ctx.shiftOption());
        int shiftAmount = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        return shift(value, shiftOption, shiftAmount);
    }

    @Override
    public Integer visitRegisterShiftedByConstant(
            ProcessorParser.RegisterShiftedByConstantContext ctx) {
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int shiftOption = visitShiftOption(ctx.shiftOption());
        int shiftAmount = numberVisitor.visit(ctx.number());
        return shift(value, shiftOption, shiftAmount);
    }

    @Override
    public Integer visitShiftOption(ProcessorParser.ShiftOptionContext ctx) {
        switch(ctx.getText()) {
            case "asr":
                return ASR;
            case "lsl":
                return LSL;
            case "lsr":
                return LSR;
            default:
                return ROR;
        }
    }

    private int shift(int value, int shiftOption, int shiftAmount) {
        switch(shiftOption) {
            case ASR:
                return value >> shiftAmount;
            case LSL:
                return value << shiftAmount;
            case LSR:
                return value >>> shiftAmount;
            default:
                return Integer.rotateRight(value, shiftAmount);
        }
    }
}
