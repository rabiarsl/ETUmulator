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
package com.kasirgalabs.etumulator.lang;

import static org.junit.Assert.fail;

import com.kasirgalabs.etumulator.processor.Memory;
import org.junit.Test;

public class AssemblerTest {
    /**
     * Test of assemble method, of class Assembler.
     */
    @Test
    public void testAssemble() throws Exception {
        Memory memory = new Memory();
        Assembler assembler = new Assembler(memory);
        String code = "INTENTIONAL_SYNTAX_ERROR";
        try {
            assembler.assemble(code);
            fail("Assembler did not throw SyntaxError.");
        } catch(SyntaxError ex) {
        }
    }
}
