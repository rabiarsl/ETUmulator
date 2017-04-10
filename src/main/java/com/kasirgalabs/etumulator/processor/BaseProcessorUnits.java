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

public class BaseProcessorUnits implements ProcessorUnits {
    private final RegisterFile registerFile;
    private final CPSR cpsr;
    private final Stack stack;
    private final Memory memory;
    private final UART uart;
    private final PC pc;
    private final LR lr;

    public BaseProcessorUnits() {
        registerFile = new RegisterFile();
        cpsr = new CPSR();
        stack = new Stack();
        memory = new Memory();
        uart = new UART(registerFile);
        pc = new PC();
        lr = new LR();
    }

    public BaseProcessorUnits(RegisterFile registerFile, CPSR cpsr, Stack stack, Memory memory,
            UART uart, PC pc, LR lr) {
        this.registerFile = registerFile;
        this.cpsr = cpsr;
        this.stack = stack;
        this.memory = memory;
        this.uart = uart;
        this.pc = pc;
        this.lr = lr;
    }

    @Override
    public RegisterFile getRegisterFile() {
        return registerFile;
    }

    @Override
    public CPSR getCPSR() {
        return cpsr;
    }

    @Override
    public Stack getStack() {
        return stack;
    }

    @Override
    public Memory getMemory() {
        return memory;
    }

    @Override
    public UART getUART() {
        return uart;
    }

    @Override
    public PC getPC() {
        return pc;
    }

    @Override
    public LR getLR() {
        return lr;
    }
}
