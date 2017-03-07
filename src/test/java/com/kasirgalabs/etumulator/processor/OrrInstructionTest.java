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

import static org.junit.Assert.assertEquals;

import com.kasirgalabs.etumulator.InstructionTester;
import org.junit.Test;

public class OrrInstructionTest extends InstructionTester {
    /**
     * Test of exitOrr method, of class Processor.
     */
    @Test
    public void testExitOrr() {
        char[] code = ("mov r1, #0\n"
                + "mov r2, #1\n"
                + "orr r0, r1, r2\n").toCharArray();
        runTestCode(code, true);
        assertEquals("OR result is wrong.", registerFile.getValue(0), 1);

        code = ("ldr r1, =0xffffffff\n"
                + "orr r0, r1, 0\n").toCharArray();
        runTestCode(code, true);
        assertEquals("OR result is wrong.", registerFile.getValue(0), -1);

        code = ("ldr r1, =0x0f0f0f0f\n"
                + "ldr r2, = 0xf0f0f0f0\n"
                + "orr r0, r1, r2\n").toCharArray();
        runTestCode(code, true);
        assertEquals("OR result is wrong.", registerFile.getValue(0), -1);
    }
}
