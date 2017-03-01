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
package com.kasirgalabs.etumulator.register;

import com.kasirgalabs.arm.ArmParser.RdContext;

public class RdRegister implements Register {
    private long value;
    private final int registerNumber;
    private final RegisterFile registerFile;

    public RdRegister(RdContext ctx, RegisterFile registerFile) {
        this.registerFile = registerFile;
        registerNumber = RegisterUtils.parseRegisterNumber(ctx.REGISTER());
        this.value = registerFile.getValue(registerNumber);
    }

    @Override
    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }

    public int getRegisterNumber() {
        return registerNumber;
    }

    public void update() {
        registerFile.update(registerNumber, value);
    }
}
