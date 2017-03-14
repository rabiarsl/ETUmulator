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
package com.kasirgalabs.etumulator.processor.branch;

import static org.junit.Assert.assertEquals;

import com.kasirgalabs.etumulator.InstructionTester;
import org.junit.Test;

public class BccAndBloInstructionsTest extends InstructionTester {
    /**
     * Test of exitBcc and exitBlo methods, of class Processor.
     */
    @Test
    public void testExitBccAndExitBlo() {
        cpsr.setCarry(true);
        String code = "mov r0, #4\n"
                + "bcc target\n"
                + "mov r0, #1\n"
                + "target:\n";
        runTestCode(code);
        assertEquals("Branch instrucbtion does not work properly.", 1, registerFile.getValue("r0"));

        cpsr.setCarry(false);
        code = "mov r0, #4\n"
                + "blo target\n"
                + "mov r0, #1\n"
                + "target:\n";
        runTestCode(code);
        assertEquals("Branch instruction does not work properly.", 4, registerFile.getValue("r0"));
    }
}
