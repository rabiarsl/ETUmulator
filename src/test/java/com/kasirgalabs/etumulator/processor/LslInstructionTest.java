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

public class LslInstructionTest extends InstructionTester {
    /**
     * Test of exitLsl method, of class Processor.
     */
    @Test
    public void exitLsl() {
        String code = "mov r1, 1\n"
                + "mov r2, #1\n"
                + "lsl r0, r1, r2\n";
        runTestCode(code);
        assertEquals("Shift result is wrong.", 2, registerFile.getValue("r0"));

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "lsl r0, r1, r2\n";
        runTestCode(code);
        assertEquals("Shift result is wrong.", 0xfffffffe, registerFile.getValue("r0"));
    }
}
