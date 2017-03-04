package com.kasirgalabs.etumulator.processor;

import static org.junit.Assert.assertEquals;

import com.kasirgalabs.etumulator.register.RegisterFile;
import org.junit.Test;

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
public class MovInstructionTest {
    /**
     * Test of exitMov method, of class Processor.
     */
    @Test
    public void testExitMov() {
        RegisterFile registerFile = new RegisterFile();
        Processor processor = new Processor(registerFile);

        registerFile.reset();
        char[] code = ("mov r0, #0\n").toCharArray();
        processor.run(code);
        assertEquals("Mov operation result is wrong.", registerFile.getValue(0), 0);

        registerFile.reset();
        code = ("mov r0, #4\n").toCharArray();
        processor.run(code);
        assertEquals("Mov operation result is wrong.", registerFile.getValue(0), 4);

        registerFile.reset();
        code = ("mov r0, 0xf\n").toCharArray();
        processor.run(code);
        assertEquals("Mov operation result is wrong.", registerFile.getValue(0), 0xf);

        registerFile.reset();
        code = ("mov r1, 8\n"
                + "mov r0, r1\n").toCharArray();
        processor.run(code);
        assertEquals("Mov operation result is wrong.", registerFile.getValue(0), 8);
    }
}