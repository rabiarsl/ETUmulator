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

import com.kasirgalabs.etumulator.linker.Linker;
import com.kasirgalabs.etumulator.processor.BaseProcessor;
import com.kasirgalabs.etumulator.processor.CPSR;
import com.kasirgalabs.etumulator.processor.Memory;
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.etumulator.processor.Stack;
import org.junit.Test;

public class SingleDataMemoryVisitorTest {
    private final RegisterFile registerFile;
    private final Memory memory;
    private final Processor processor;
    private final Linker linker;

    public SingleDataMemoryVisitorTest() {
        registerFile = new RegisterFile();
        memory = new Memory();
        processor = new BaseProcessor(registerFile,
                new CPSR(),
                new Stack(),
                memory);
        linker = new Linker(memory);
    }

    /**
     * Test of visitLdr method, of class SingleDataMemoryVisitor.
     */
    @Test
    public void testVisitLdr() {

    }
}
