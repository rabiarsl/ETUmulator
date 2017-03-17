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
package com.kasirgalabs.etumulator.processor.instruction.branch;

import static org.junit.Assert.assertEquals;

import com.kasirgalabs.etumulator.InstructionTester;
import org.junit.Test;

public class BltInstructionTest extends InstructionTester {
    /**
     * Test of exitBlt method, of class Processor.
     */
    @Test
    public void testExitBlt() {
        String code = "mov r0, #2\n"
                + "mov r1, #1\n"
                + "cmp r1, r0\n"
                + "blt target\n"
                + "mov r0, #1\n"
                + "target:\n";
        runTestCode(code);
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));

        code = "mov r0, #0\n"
                + "cmp r0, #0\n"
                + "blt target\n"
                + "mov r0, #2\n"
                + "target:\n";
        runTestCode(code);
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));
    }
}
