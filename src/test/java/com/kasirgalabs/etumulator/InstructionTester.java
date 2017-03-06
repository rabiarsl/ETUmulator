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

import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.register.CPSR;
import com.kasirgalabs.etumulator.register.RegisterFile;

public class InstructionTester {
    protected final RegisterFile registerFile;
    protected final CPSR cpsr;
    private final Processor processor;

    public InstructionTester() {
        registerFile = new RegisterFile();
        cpsr = new CPSR();
        processor = new Processor(registerFile, cpsr);
    }

    protected final void runTestCode(char[] code, boolean reset) {
        registerFile.reset();
        if(reset) {
            cpsr.reset();
        }
        processor.run(code);
    }
}
