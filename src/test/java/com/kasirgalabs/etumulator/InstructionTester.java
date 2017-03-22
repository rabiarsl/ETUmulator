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
package com.kasirgalabs.etumulator;

import com.kasirgalabs.etumulator.linker.Linker;
import com.kasirgalabs.etumulator.processor.CPSR;
import com.kasirgalabs.etumulator.processor.Memory;
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.etumulator.processor.Stack;

public class InstructionTester {
    protected final RegisterFile registerFile;
    protected final CPSR cpsr;
    protected final Stack stack;
    protected final Memory memory;
    private final Processor processor;

    public InstructionTester() {
        registerFile = new RegisterFile();
        cpsr = new CPSR();
        stack = new Stack();
        memory = new Memory();
        processor = new Processor(registerFile, cpsr, stack, memory);
    }

    protected final void runTestCode(String code) {
        registerFile.reset();
        processor.run(code, new Linker(memory).link(code));
    }
}
