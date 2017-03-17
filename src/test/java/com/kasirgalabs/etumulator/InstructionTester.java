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
import com.kasirgalabs.etumulator.processor.CPUStack;
import com.kasirgalabs.etumulator.processor.InstructionUnit;
import com.kasirgalabs.etumulator.processor.MemoryUnit;
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.RegisterFile;

public class InstructionTester {
    protected final RegisterFile registerFile;
    protected final CPUStack stack;
    protected final CPSR cpsr;
    private final InstructionUnit instructionUnit;
    protected final MemoryUnit memoryUnit;
    private final Processor processor;

    public InstructionTester() {
        registerFile = new RegisterFile();
        stack = new CPUStack();
        cpsr = new CPSR();
        instructionUnit = new InstructionUnit();
        memoryUnit = new MemoryUnit();
        processor = new Processor(registerFile, stack, cpsr, instructionUnit, memoryUnit);
    }

    protected final void runTestCode(String code) {
        registerFile.reset();
        Linker linker = new Linker(instructionUnit, memoryUnit);
        linker.linkAndLoad(code);
        processor.run();
    }
}
