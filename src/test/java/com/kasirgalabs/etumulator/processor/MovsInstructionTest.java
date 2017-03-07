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

public class MovsInstructionTest extends InstructionTester {
    /**
     * Test of exitMovs method, of class Processor.
     */
    @Test
    public void testExitMovs() {
        char[] code = ("movs r0, #0\n").toCharArray();
        runTestCode(code);
        assertEquals("Move result is wrong.", registerFile.getValue(0), 0);
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());

        code = ("mov r1, 8\n"
                + "movs r0, r1\n").toCharArray();
        runTestCode(code);
        assertEquals("Move result is wrong.", registerFile.getValue(0), 8);
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
    }
}
