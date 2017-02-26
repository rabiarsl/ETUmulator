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
package com.kasirgalabs.etumulator.registers;

import com.kasirgalabs.etumulator.Registry;
import org.antlr.v4.runtime.tree.TerminalNode;

public final class RegisterUtils {

    private RegisterUtils() {
    }

    public static int getRegisterNumber(TerminalNode register) {
        String name = register.toString();
        String number = name.substring(1, name.length());
        return Integer.parseInt(number);
    }

    public static String getRegisterValue(int registerNumber) {
        RegisterFile registerFile = Registry.get(RegisterFile.class);
        Register register = registerFile.getRegister(registerNumber);
        return register.getValue();
    }
}
