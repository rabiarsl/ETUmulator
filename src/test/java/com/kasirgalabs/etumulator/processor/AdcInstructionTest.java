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

public class AdcInstructionTest extends InstructionTester {
    /**
     * Test of exitAdc method, of class Processor.
     */
    @Test
    public void testExitAdc() {
        cpsr.setCarry(false);
        char[] code = ("adc r1, r2, r3\n").toCharArray();
        runTestCode(code);
        assertEquals("Addition result is wrong.", registerFile.getValue("r1"), 0);

        cpsr.setCarry(true);
        code = ("adc r0, r1, 0\n").toCharArray();
        runTestCode(code);
        assertEquals("Addition result is wrong.", registerFile.getValue("r0"), 1);

        cpsr.setCarry(false);
        code = ("adc r0, r1, 4\n").toCharArray();
        runTestCode(code);
        assertEquals("Addition result is wrong.", registerFile.getValue("r0"), 4);

        cpsr.setCarry(true);
        code = ("mov r1, #1\n"
                + "mov r2, #2\n"
                + "adc r0, r1, r2\n").toCharArray();
        runTestCode(code);
        assertEquals("Addition result is wrong.", registerFile.getValue("r0"), 4);

        cpsr.setCarry(true);
        code = ("mov r0, #1\n"
                + "adc r0, r0, r0\n").toCharArray();
        runTestCode(code);
        assertEquals("Addition result is wrong.", registerFile.getValue("r0"), 3);
    }
}
