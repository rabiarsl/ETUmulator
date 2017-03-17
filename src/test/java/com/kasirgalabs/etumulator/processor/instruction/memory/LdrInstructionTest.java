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
package com.kasirgalabs.etumulator.processor.instruction.memory;

import static org.junit.Assert.assertEquals;

import com.kasirgalabs.etumulator.InstructionTester;
import org.junit.Test;

public class LdrInstructionTest extends InstructionTester {
    /**
     * Test of exitLdr method, of class Processor.
     */
    @Test
    public void testExitLdr() {
        String code = "ldr r0, =0xffffffff\n";
        runTestCode(code);
        assertEquals("Load result is wrong.", 0xffffffff, registerFile.getValue("r0"));

        code = "ldr r0, =#123\n";
        runTestCode(code);
        assertEquals("Load result is wrong.", 123, registerFile.getValue("r0"));

        code = "ldr r0, =#0x7fffffff\n";
        runTestCode(code);
        assertEquals("Load result is wrong.", Integer.MAX_VALUE, registerFile.getValue("r0"));

        code = "ldr r0, =label\n"
                + "label: .asciz \"DATA\"\n";
        runTestCode(code);
        assertEquals("Load result is wrong.", memoryUnit.getLabelAddress("label"), registerFile.getValue("r0"));
    }
}
