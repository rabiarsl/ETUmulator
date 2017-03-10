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
import com.kasirgalabs.etumulator.processor.RegisterFile;

public class RdRegister {
    private final RegisterFile registerFile;
    private final String registerName;
    private int value;

    public RdRegister(RdContext ctx, RegisterFile registerFile) {
        this.registerFile = registerFile;
        registerName = ctx.getText();
        value = registerFile.getValue(registerName);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void update() {
        registerFile.update(registerName, value);
    }
}
