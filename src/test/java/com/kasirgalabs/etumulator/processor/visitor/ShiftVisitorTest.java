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

import com.kasirgalabs.etumulator.processor.CPSR;
import com.kasirgalabs.etumulator.processor.Memory;
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.etumulator.processor.Stack;
import org.junit.Test;

public class ShiftVisitorTest {
    private final RegisterFile registerFile;
    private final CPSR cpsr;
    private final Processor processor;

    public ShiftVisitorTest() {
        registerFile = new RegisterFile();
        cpsr = new CPSR();
        processor = new Processor(registerFile,
                cpsr,
                new Stack(),
                new Memory());
    }

    /**
     * Test of visitAsr method, of class ShiftVisitor.
     */
    @Test
    public void testVisitAsr() {
        String code = "ldr r1, =#0x80000000\n"
                + "mov r2, #1\n"
                + "asr r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0x80000000 >> 1, registerFile.getValue("r0"));

        code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "asr r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, #2\n"
                + "asr r0, r1, #1\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitAsrs method, of class ShiftVisitor.
     */
    @Test
    public void testVisitAsrs() {
        String code = "mov r1, 1\n"
                + "mov r2, #1\n"
                + "asrs r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Carry flag is wrong.", true, cpsr.isCarry());

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "asrs r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0xffffffff, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Carry flag is wrong.", true, cpsr.isCarry());

        code = "ldr r1, =#0xffffffff\n"
                + "asrs r0, r1, #2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0xffffffff, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Carry flag is wrong.", true, cpsr.isCarry());
    }

    /**
     * Test of visitLsl method, of class ShiftVisitor.
     */
    @Test
    public void testVisitLsl() {
        String code = "mov r1, 1\n"
                + "mov r2, #1\n"
                + "lsl r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 2, registerFile.getValue("r0"));

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "lsl r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0xfffffffe, registerFile.getValue("r0"));

        code = "ldr r1, =#0xffffffff\n"
                + "lsl r0, r1, 1\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0xfffffffe, registerFile.getValue("r0"));
    }

    /**
     * Test of visitLsls method, of class ShiftVisitor.
     */
    @Test
    public void testVisitLsls() {
        String code = "mov r1, 1\n"
                + "mov r2, #1\n"
                + "lsls r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 2, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Carry flag is wrong.", false, cpsr.isCarry());

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "lsls r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0xffffffff << 1, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Carry flag is wrong.", true, cpsr.isCarry());

        code = "ldr r1, =#0xffffffff\n"
                + "lsls r0, r1, 2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0xffffffff << 2, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Carry flag is wrong.", true, cpsr.isCarry());
    }

    /**
     * Test of visitLsr method, of class ShiftVisitor.
     */
    @Test
    public void testVisitLsr() {
        String code = "mov r1, 1\n"
                + "mov r2, #1\n"
                + "lsr r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "lsr r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0x7fffffff, registerFile.getValue("r0"));

        code = "ldr r1, =#0xffffffff\n"
                + "lsr r0, r1, #4\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0xffffffff >>> 4, registerFile.getValue("r0"));
    }

    /**
     * Test of visitLsrs method, of class ShiftVisitor.
     */
    @Test
    public void testVisitLsrs() {
        String code = "mov r1, 1\n"
                + "mov r2, #1\n"
                + "lsrs r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Carry flag is wrong.", true, cpsr.isCarry());

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "lsrs r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0x7fffffff, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Carry flag is wrong.", true, cpsr.isCarry());

        code = "ldr r1, =#0xffffffff\n"
                + "lsrs r0, r1, #2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0xffffffff >>> 2, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Carry flag is wrong.", true, cpsr.isCarry());
    }

    /**
     * Test of visitRor method, of class ShiftVisitor.
     */
    @Test
    public void testVisitRor() {
        String code = "mov r1, 1\n"
                + "mov r2, #1\n"
                + "ror r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0x80000000, registerFile.getValue("r0"));

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "ror r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0xffffffff, registerFile.getValue("r0"));

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "ror r0, r1, #2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", Integer.rotateRight(0xffffffff, 2),
                registerFile.getValue("r0"));
    }

    /**
     * Test of visitRors method, of class ShiftVisitor.
     */
    @Test
    public void testVisitRors() {
        String code = "mov r1, 1\n"
                + "mov r2, #1\n"
                + "rors r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0x80000000, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Carry flag is wrong.", true, cpsr.isCarry());

        code = "mov r1, 0\n"
                + "mov r2, #4\n"
                + "rors r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Carry flag is wrong.", false, cpsr.isCarry());

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "rors r0, r1, r2\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0xffffffff, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Carry flag is wrong.", true, cpsr.isCarry());

        code = "ldr r1, =#0xfffff0ff\n"
                + "mov r2, #1\n"
                + "rors r0, r1, #3\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", Integer.rotateRight(0xfffff0ff, 3),
                registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Carry flag is wrong.", true, cpsr.isCarry());
    }

    /**
     * Test of visitRrx method, of class ShiftVisitor.
     */
    @Test
    public void testVisitRrx() {
        String code = "mov r1, 1\n"
                + "rrx r0, r1\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));
    }

    /**
     * Test of visitRrxs method, of class ShiftVisitor.
     */
    @Test
    public void testVisitRrxs() {
        cpsr.setCarry(true);
        String code = "ldr r1, =#0xffffffff\n"
                + "rrxs r0, r1\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0xffffffff, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Carry flag is wrong.", true, cpsr.isCarry());

        cpsr.setCarry(false);
        code = "ldr r1, =#0xffffffff\n"
                + "rrxs r0, r1\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", Integer.MAX_VALUE, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Carry flag is wrong.", true, cpsr.isCarry());

        cpsr.setCarry(false);
        code = "mov r1, 0\n"
                + "rrxs r0, r1\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Carry flag is wrong.", false, cpsr.isCarry());

        cpsr.setCarry(false);
        code = "mov r1, 1\n"
                + "rrxs r0, r1\n";
        processor.run(code, null);
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Carry flag is wrong.", true, cpsr.isCarry());
    }
}
