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

public class SubsInstructionTest extends InstructionTester {
    /**
     * Test of exitSubs method, of class Processor.
     */
    @Test
    public void testExitSubs() {
        String code = "subs r0, r1, r2\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", 0, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        code = "subs r0, r1, #1\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", -1, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        code = "ldr r1, =0x80000000\n"
                + "mov r2, #1\n"
                + "subs r0, r1, r2\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", Integer.MAX_VALUE, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", true, cpsr.isOverflow());

        code = "mov r1, #0xf\n"
                + "subs r0, r0, r1\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", -0xf, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());
    }
}
