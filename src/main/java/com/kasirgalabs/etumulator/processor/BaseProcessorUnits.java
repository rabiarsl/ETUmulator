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
    private RegisterFile registerFile;
    private APSR apsr;
    private Stack stack;
    private Memory memory;
    private UART uart;
    private PC pc;
    private LR lr;

    public BaseProcessorUnits() {
        registerFile = new RegisterFile();
        apsr = new APSR();
        stack = new Stack();
        memory = new Memory();
        uart = new UART(registerFile);
        pc = new PC();
        lr = new LR();
    }

    public BaseProcessorUnits(RegisterFile registerFile, APSR apsr, Stack stack, Memory memory,
            UART uart, PC pc, LR lr) {
        this.registerFile = registerFile;
        this.apsr = apsr;
        this.stack = stack;
        this.memory = memory;
        this.uart = uart;
        this.pc = pc;
        this.lr = lr;
    }

    @Override
    public void setRegisterFile(RegisterFile registerFile) {
        this.registerFile = registerFile;
    }

    @Override
    public RegisterFile getRegisterFile() {
        return registerFile;
    }

    @Override
    public APSR getAPSR() {
        return apsr;
    }

    @Override
    public void setAPSR(APSR apsr) {
        this.apsr = apsr;
    }

    @Override
    public void setStack(Stack stack) {
        this.stack = stack;
    }

    @Override
    public Stack getStack() {
        return stack;
    }

    @Override
    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    @Override
    public Memory getMemory() {
        return memory;
    }

    @Override
    public void setUART(UART uart) {
        this.uart = uart;
    }

    @Override
    public UART getUART() {
        return uart;
    }

    @Override
    public void setPC(PC pc) {
        this.pc = pc;
    }

    @Override
    public PC getPC() {
        return pc;
    }

    @Override
    public void setLR(LR lr) {
        this.lr = lr;
    }

    @Override
    public LR getLR() {
        return lr;
    }

    @Override
    public void reset() {
        registerFile.reset();
        apsr.reset();
        stack.reset();
        memory.reset();
        pc.reset();
        lr.reset();
    }
}
