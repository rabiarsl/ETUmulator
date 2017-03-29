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
package com.kasirgalabs.etumulator.processor;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ProcessorUnits {
    private final RegisterFile registerFile;
    private final CPSR cpsr;
    private final Stack stack;
    private final Memory memory;
    private final UART uart;

    public ProcessorUnits() {
        registerFile = new RegisterFile();
        cpsr = new CPSR();
        stack = new Stack();
        memory = new Memory();
        uart = new UART(registerFile);
    }

    @Inject
    public ProcessorUnits(RegisterFile registerFile, CPSR cpsr, Stack stack, Memory memory,
            UART uart) {
        this.registerFile = registerFile;
        this.cpsr = cpsr;
        this.stack = stack;
        this.memory = memory;
        this.uart = uart;
    }

    public RegisterFile getRegisterFile() {
        return registerFile;
    }

    public CPSR getCPSR() {
        return cpsr;
    }

    public Stack getStack() {
        return stack;
    }

    public Memory getMemory() {
        return memory;
    }

    public UART getUART() {
        return uart;
    }
}
