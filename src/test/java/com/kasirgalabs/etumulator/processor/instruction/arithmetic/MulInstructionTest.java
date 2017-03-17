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

public class MulInstructionTest extends InstructionTester {
    /**
     * Test of exitMul and exitMuls method, of class Processor.
     */
    @Test
    public void testExitMulAndExitMuls() {
        String code = "mul r0, r1, r3\n";
        runTestCode(code);
        assertEquals("Multiply result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, 4\n"
                + "mov r2, 8\n"
                + "mul r0, r1, r2\n";
        runTestCode(code);
        assertEquals("Multiply result is wrong.", 32, registerFile.getValue("r0"));

        code = "muls r0, r1, r3\n";
        runTestCode(code);
        assertEquals("Multiply result is wrong.", 0, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());

        code = "mov r1, 4\n"
                + "mov r2, 8\n"
                + "muls r0, r1, r2\n";
        runTestCode(code);
        assertEquals("Multiply result is wrong.", 32, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
    }
}