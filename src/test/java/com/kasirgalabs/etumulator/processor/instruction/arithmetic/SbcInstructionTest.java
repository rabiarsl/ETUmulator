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
package com.kasirgalabs.etumulator.processor.instruction.arithmetic;

import static org.junit.Assert.assertEquals;

import com.kasirgalabs.etumulator.InstructionTester;
import org.junit.Test;

public class SbcInstructionTest extends InstructionTester {
    /**
     * Test of exitSbc and exitSbcs method, of class Processor.
     */
    @Test
    public void testExitSbcAndExitSbcs() {
        cpsr.setCarry(true);
        String code = "sbc r1, r2, r3\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", 0, registerFile.getValue("r1"));

        cpsr.setCarry(false);
        code = "sbc r0, r1, 0\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", -1, registerFile.getValue("r0"));

        cpsr.setCarry(true);
        code = "sbc r0, r1, 4\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", -4, registerFile.getValue("r0"));

        cpsr.setCarry(false);
        code = "mov r1, #1\n"
                + "mov r2, #2\n"
                + "sbc r0, r1, r2\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", -2, registerFile.getValue("r0"));

        cpsr.setCarry(false);
        code = "mov r0, #1\n"
                + "sbc r0, r0, r0\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", -1, registerFile.getValue("r0"));

        cpsr.setCarry(true);
        code = "ldr r1, =#0x80000000\n"
                + "sbcs r0, r1, #1\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", Integer.MAX_VALUE, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", true, cpsr.isOverflow());

        cpsr.setCarry(false);
        code = "ldr r1, =#0x80000000\n"
                + "sbcs r0, r1, #1\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", Integer.MAX_VALUE - 1, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", true, cpsr.isOverflow());

        cpsr.setCarry(true);
        code = "sbcs r0, r1, #0\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", 0, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        cpsr.setCarry(false);
        code = "ldr r1, =#0x80000001\n"
                + "sbcs r0, r1, #1\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", Integer.MAX_VALUE, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", true, cpsr.isOverflow());
    }
}
