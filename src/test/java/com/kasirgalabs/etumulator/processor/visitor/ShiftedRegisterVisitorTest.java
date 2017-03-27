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
package com.kasirgalabs.etumulator.processor.visitor;

import static org.junit.Assert.assertEquals;
import com.kasirgalabs.etumulator.processor.BaseProcessor;
import com.kasirgalabs.etumulator.processor.CPSR;
import com.kasirgalabs.etumulator.processor.Memory;
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.etumulator.processor.Stack;
import org.junit.Test;

public class ShiftedRegisterVisitorTest {
    private final RegisterFile registerFile;
    private final Processor processor;

    public ShiftedRegisterVisitorTest() {
        registerFile = new RegisterFile();
        processor = new BaseProcessor(registerFile,
                new CPSR(),
                new Stack(),
                new Memory());
    }

    /**
     * Test of visitRegisterShiftedByRegister method, of class ShiftedRegisterVisitor.
     */
    @Test
    public void testVisitRegisterShiftedByRegister() {
        String code = "mov r1, #1\n"
                + "mov r2, #1\n"
                + "mov r3, #1\n"
                + "add r0, r1, r2, lsl r3\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 3, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "mov r2, #1\n"
                + "mov r3, #1\n"
                + "add r0, r1, r2, lsr r3\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 1, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "ldr r2, =#0xffffffff\n"
                + "mov r3, #1\n"
                + "add r0, r1, r2, asr r3\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "ldr r2, =#0xffffffff\n"
                + "mov r3, #1\n"
                + "add r0, r1, r2, ror r3\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "mov r2, #1\n"
                + "mov r3, #1\n"
                + "add r0, r1, r2, ror r3\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", Integer.MIN_VALUE + 1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitRegisterShiftedByConstant method, of class ShiftedRegisterVisitor.
     */
    @Test
    public void testVisitRegisterShiftedByConstant() {
        String code = "mov r1, #1\n"
                + "mov r2, #1\n"
                + "add r0, r1, r2, lsl 1\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 3, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "mov r2, #1\n"
                + "add r0, r1, r2, lsr #1\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 1, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "ldr r2, =#0xffffffff\n"
                + "add r0, r1, r2, asr #0x1\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "ldr r2, =#0xffffffff\n"
                + "add r0, r1, r2, ror 0x1\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "mov r2, #1\n"
                + "mov r3, #1\n"
                + "add r0, r1, r2, ror 1\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", Integer.MIN_VALUE + 1, registerFile.getValue("r0"));
    }
}
