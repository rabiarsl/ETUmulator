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

import com.kasirgalabs.etumulator.register.CPSR;
import com.kasirgalabs.etumulator.register.RegisterFile;
import org.junit.Test;

public class AddsInstructionTest {
    /**
     * Test of exitAdds method, of class Processor.
     */
    @Test
    public void testExitAdds() {
        RegisterFile registerFile = new RegisterFile();
        Processor processor = new Processor(registerFile);

        registerFile.reset();
        char[] code = ("adds r1, r2, #0\n").toCharArray();
        processor.run(code);
        assertEquals("Addition result is wrong.", registerFile.getValue(1), 0);

        registerFile.reset();
        code = ("adds r1, r2, 8\n").toCharArray();
        processor.run(code);
        assertEquals("Addition result is wrong.", registerFile.getValue(1), 8);

        registerFile.reset();
        code = ("add r0, r0, #0xf0\n"
                + "adds r0, r0, 0xf0\n").toCharArray();
        processor.run(code);
        assertEquals("Addition result is wrong.", registerFile.getValue(0), 0x1e0);

        registerFile.reset();
        code = ("ldr r1, =#0x80000000\n"
                + "ldr r2, =0xffffffff\n"
                + "adds r0, r1, r2\n").toCharArray();
        processor.run(code);
        assertEquals("Addition result is wrong.", registerFile.getValue(0), Integer.MAX_VALUE);
        assertEquals("Negative flag is wrong.", false, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", false, CPSR.isZero());
        assertEquals("Overflow flag is wrong.", true, CPSR.isOverflow());
    }
}
