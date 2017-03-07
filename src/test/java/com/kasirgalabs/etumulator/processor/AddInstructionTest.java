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

public class AddInstructionTest extends InstructionTester {
    /**
     * Test of exitAdd method, of class Processor.
     */
    @Test
    public void testExitAdd() {
        char[] code = ("add r1, r2, r3\n").toCharArray();
        runTestCode(code);
        assertEquals("Addition result is wrong.", registerFile.getValue(1), 0);

        code = ("add r1, r2, #64\n").toCharArray();
        runTestCode(code);
        assertEquals("Addition result is wrong.", registerFile.getValue(1), 64);

        code = ("add r1, r2, 0x0\n").toCharArray();
        runTestCode(code);
        assertEquals("Addition result is wrong.", registerFile.getValue(1), 0);

        code = ("add r1, r2, #0xff\n"
                + "add r1, r1, 0xff\n").toCharArray();
        runTestCode(code);
        assertEquals("Addition result is wrong.", registerFile.getValue(1), 510);
    }
}
