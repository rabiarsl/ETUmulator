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

public class Operand2Visitor extends ProcessorBaseVisitor<Integer> {
    private final RegisterFile registerFile;
    private final RegisterVisitor registerVisitor;
    private final NumberVisitor numberVisitor;
    private final ShiftedRegisterVisitor shiftedRegister;

    public Operand2Visitor(RegisterFile registerFile) {
        this.registerFile = registerFile;
        registerVisitor = new RegisterVisitor();
        numberVisitor = new NumberVisitor();
        shiftedRegister = new ShiftedRegisterVisitor(registerFile);
    }

    @Override
    public Integer visitOperand2(ProcessorParser.Operand2Context ctx) {
        if(ctx.rm() != null) {
            return registerFile.getValue(registerVisitor.visit(ctx.rm()));
        }
        else if(ctx.registerShiftedByRegister() != null) {
            return shiftedRegister.visit(ctx.registerShiftedByRegister());
        }
        else if(ctx.registerShiftedByConstant() != null) {
            return shiftedRegister.visit(ctx.registerShiftedByConstant());
        }
        return numberVisitor.visit(ctx.imm8m());
    }
}
