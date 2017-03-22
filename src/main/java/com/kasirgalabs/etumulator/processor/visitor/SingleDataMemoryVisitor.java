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
import com.kasirgalabs.etumulator.processor.Memory;
import com.kasirgalabs.etumulator.processor.RegisterFile;

public class SingleDataMemoryVisitor extends ArmBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final Memory memory;
    private final RegisterVisitor registerVisitor;
    private final NumberVisitor numberVisitor;
    private final LdrAddressVisitor ldrAddressVisitor;

    public SingleDataMemoryVisitor(RegisterFile registerFile, Memory memory) {
        this.registerFile = registerFile;
        this.memory = memory;
        registerVisitor = new RegisterVisitor();
        this.numberVisitor = new NumberVisitor();
        this.ldrAddressVisitor = new LdrAddressVisitor(registerFile);
    }

    @Override
    public Void visitLdr(ArmParser.LdrContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        if(ctx.ASSIGN() != null) {
            registerFile.setValue(destRegister, numberVisitor.visit(ctx.number()));
        }
        else {
            int address = ldrAddressVisitor.visit(ctx.ldrAddress());
            int value = memory.get(address);
            registerFile.setValue(destRegister, value);
        }
        return null;
    }
}
