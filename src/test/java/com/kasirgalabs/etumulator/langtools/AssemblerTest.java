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
package com.kasirgalabs.etumulator.langtools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.kasirgalabs.etumulator.langtools.Linker.ExecutableCode;
import com.kasirgalabs.etumulator.processor.Memory;
import java.util.List;
import org.junit.Test;

public class AssemblerTest {
    /**
     * Test of assemble method, of class Assembler.
     */
    @Test
    public void testAssemble() {
        Memory memory = new Memory();
        Assembler assembler = new Assembler(memory);
        ExecutableCode executableCode;
        String expData;
        String code = "ldr r0, =label\n";
        try {
            assembler.assemble(code);
            fail("Assembler did not throw LabelError, there is an undefined label.");
        } catch(LabelError ex) {
        }

        code = "ldr r0, =label\n"
                + "label: .asciz \"data\"\n"
                + "label:\n";
        try {
            assembler.assemble(code);
            fail("Assembler did not throw LabelError, duplicate labels.");
        } catch(LabelError ex) {
        }

        code = "ldr r0, =label\n"
                + "label: .asciz \"data\"\n"
                + "label: .asciz \"duplicate\"\n";
        try {
            assembler.assemble(code);
            fail("Assembler did not throw LabelError, duplicate labels.");
        } catch(LabelError ex) {
        }

        code = "INTENTIONAL_SYNTAX_ERROR";
        try {
            assembler.assemble(code);
            fail("Assembler did not throw SyntaxError.");
        } catch(SyntaxError ex) {
        }

        code = "b label\n";
        try {
            assembler.assemble(code);
            fail("Assembler did not throw LabelError, there is an undefined label.");
        } catch(LabelError ex) {
        }

        // No branch target given.
        // The given label is defines a data not branch target.
        code = "b label\n"
                + "label: .asciz \"DATA LABEL\"\n";
        try {
            assembler.assemble(code);
            fail("Assembler did not throw LabelError, there is an undefined label.");
        } catch(LabelError ex) {
        }

        code = "b label\n"
                + "label: .asciz \"DATA LABEL\"\n"
                + "label:\n";
        try {
            assembler.assemble(code);
            fail("Assembler did not throw LabelError, duplicate labels.");
        } catch(LabelError ex) {
        }

        code = "b label\n"
                + "label: .asciz \"DATA LABEL\"\n"
                + "label:\n";
        try {
            assembler.assemble(code);
            fail("Assembler did not throw LabelError, duplicate labels.");
        } catch(LabelError ex) {
        }

        code = "ldr r0, =label\n"
                + "label: .asciz \"DATA\"\n";
        executableCode = assembler.assemble(code);
        expData = "DATA\n";
        assertEquals("Assembler did not resolve data properly.", expData,
                executableCode.getData().get(0).getValue());

        code = "ldr r0, =label\n"
                + "label: .asciz \"DATA\"\n"
                + "label: .asciz \"DATA\"\n"
                + "label: .asciz \"DATA\"\n";
        try {
            assembler.assemble(code);
            fail("Assembler did not throw LabelError, duplicate labels.");
        } catch(LabelError ex) {
        }

        code = "ldr r0, =label0\n"
                + "label0: .asciz \"DATA0\"\n"
                + "label1: .asciz \"DATA1\"\n"
                + "label2: .asciz \"DATA2\"\n";
        executableCode = assembler.assemble(code);
        List<Data> dataResult = executableCode.getData();
        expData = "DATA0\n";
        assertEquals("Assembler did not resolve data properly.", expData,
                dataResult.get(0).getValue());
        expData = "DATA1\n";
        assertEquals("Assembler did not resolve data properly.", expData,
                dataResult.get(1).getValue());
        expData = "DATA2\n";
        assertEquals("Assembler did not resolve data properly.", expData,
                dataResult.get(2).getValue());

        code = "bl unsupported\n";
        try {
            assembler.assemble(code);
            fail("Assembler did not throw UnsupportedInstructionError.");
        } catch(UnsupportedInstructionError ex) {
        }
    }
}
