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

import static org.junit.Assert.assertEquals;

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
        String code = "ldr r0, =label\n"
                + "label: .asciz \"abc\"\n";
        processor.run(code, linker.link(code));
        int address = registerFile.getValue("r0");
        assertEquals("LDR operation does not work properly.", 'a', memory.get(address));
        assertEquals("LDR operation does not work properly.", 'b', memory.get(address + 1));
        assertEquals("LDR operation does not work properly.", 'c', memory.get(address + 2));
        assertEquals("LDR operation does not work properly.", '\n', memory.get(address + 3));

        code = "ldr r0, =0xffffffff\n";
        processor.run(code, linker.link(code));
        assertEquals("LDR operation does not work properly.", 0xffffffff,
                registerFile.getValue("r0"));

        code = "ldr r0, =label\n"
                + "ldr r1, [r0], #1\n"
                + "label: .asciz \"abc\"\n";
        processor.run(code, linker.link(code));
        assertEquals("LDR operation does not work properly.", 'a', registerFile.getValue("r1"));
        int value = memory.get(registerFile.getValue("r0"));
        assertEquals("LDR operation does not work properly.", 'b', value);

        code = "ldr r0, =label\n"
                + "ldr r1, [r0, #1]\n"
                + "label: .asciz \"abc\"\n";
        processor.run(code, linker.link(code));
        assertEquals("LDR operation does not work properly.", 'b', registerFile.getValue("r1"));

        code = "mov r1, #1\n"
                + "ldr r0, =label\n"
                + "ldr r1, [r0, r1, lsl #1]\n"
                + "label: .asciz \"abc\"\n";
        processor.run(code, linker.link(code));
        assertEquals("LDR operation does not work properly.", 'c', registerFile.getValue("r1"));

        code = "mov r1, #2\n"
                + "ldr r0, =label\n"
                + "ldr r1, [r0, r1]\n"
                + "label: .asciz \"abc\"\n";
        processor.run(code, linker.link(code));
        assertEquals("LDR operation does not work properly.", 'c', registerFile.getValue("r1"));

        code = "mov r1, #1\n"
                + "ldr r0, =label\n"
                + "ldr r1, [r0], r1\n"
                + "label: .asciz \"abc\"\n";
        processor.run(code, linker.link(code));
        assertEquals("LDR operation does not work properly.", 'a', registerFile.getValue("r1"));
        value = memory.get(registerFile.getValue("r0"));
        assertEquals("LDR operation does not work properly.", 'b', value);

        code = "mov r1, #1\n"
                + "ldr r0, =label\n"
                + "ldr r1, [r0], r1, lsl #1\n"
                + "label: .asciz \"abc\"\n";
        processor.run(code, linker.link(code));
        assertEquals("LDR operation does not work properly.", 'a', registerFile.getValue("r1"));
        value = memory.get(registerFile.getValue("r0"));
        assertEquals("LDR operation does not work properly.", 'c', value);
    }
}
