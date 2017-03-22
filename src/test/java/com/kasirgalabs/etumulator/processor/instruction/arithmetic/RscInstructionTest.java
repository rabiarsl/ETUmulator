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

public class RscInstructionTest extends InstructionTester {
    /**
     * Test of exitRsc and exitRscs method, of class Processor.
     */
    @Test
    public void testExitRscAndExitRscs() {
        cpsr.setCarry(true);
        String code = "rsc r1, r2, r3\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", 0, registerFile.getValue("r1"));

        cpsr.setCarry(false);
        code = "rsc r0, r1, 0\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", -1, registerFile.getValue("r0"));

        cpsr.setCarry(true);
        code = "rsc r0, r1, 4\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", 4, registerFile.getValue("r0"));

        cpsr.setCarry(false);
        code = "mov r1, #1\n"
                + "mov r2, #2\n"
                + "rsc r0, r2, r1\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", -2, registerFile.getValue("r0"));

        cpsr.setCarry(false);
        code = "mov r0, #1\n"
                + "rsc r0, r0, r0\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", -1, registerFile.getValue("r0"));

        cpsr.setCarry(true);
        code = "mov r2, #1\n"
                + "ldr r1, =#0x80000000\n"
                + "rscs r0, r2, r1\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", Integer.MAX_VALUE, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", true, cpsr.isOverflow());

        cpsr.setCarry(false);
        code = "mov r2, #1\n"
                + "ldr r1, =#0x80000000\n"
                + "rscs r0, r2, r1\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", Integer.MAX_VALUE - 1, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", true, cpsr.isOverflow());

        cpsr.setCarry(true);
        code = "rscs r0, r1, #0\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", 0, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        cpsr.setCarry(false);
        code = "mov r2, #1\n"
                + "ldr r1, =#0x80000001\n"
                + "rscs r0, r2, r1\n";
        runTestCode(code);
        assertEquals("Subtraction result is wrong.", Integer.MAX_VALUE, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", true, cpsr.isOverflow());
    }
}
