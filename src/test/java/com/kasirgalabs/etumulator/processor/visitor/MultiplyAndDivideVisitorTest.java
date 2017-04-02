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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.kasirgalabs.etumulator.langtools.LinkerAndLoader;
import com.kasirgalabs.etumulator.processor.BaseProcessor;
import com.kasirgalabs.etumulator.processor.BaseProcessorUnits;
import com.kasirgalabs.etumulator.processor.CPSR;
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.ProcessorUnits;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import org.junit.Test;

public class MultiplyAndDivideVisitorTest {
    private final LinkerAndLoader linkerAndLoader;
    private final RegisterFile registerFile;
    private final CPSR cpsr;
    private final Processor processor;

    public MultiplyAndDivideVisitorTest() {
        ProcessorUnits processorUnits = new BaseProcessorUnits();
        linkerAndLoader = new LinkerAndLoader(processorUnits.getMemory());
        registerFile = processorUnits.getRegisterFile();
        cpsr = processorUnits.getCPSR();
        processor = new BaseProcessor(processorUnits);
    }

    /**
     * Test of visitMul method, of class MultiplyAndDivideVisitor.
     */
    @Test
    public void testVisitMul() {
        String code = "mul r0, r1, r3\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Multiply result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, 4\n"
                + "mov r2, 8\n"
                + "mul r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Multiply result is wrong.", 32, registerFile.getValue("r0"));
    }

    /**
     * Test of visitMuls method, of class MultiplyAndDivideVisitor.
     */
    @Test
    public void testVisitMuls() {
        String code = "muls r0, r1, r3\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Multiply result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertTrue("Zero flag is wrong.", cpsr.isZero());

        code = "mov r1, 4\n"
                + "mov r2, 8\n"
                + "muls r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Multiply result is wrong.", 32, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());
    }

    /**
     * Test of visitMla method, of class MultiplyAndDivideVisitor.
     */
    @Test
    public void testVisitMla() {
        String code = "mla r0, r1, r3, r4\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Multiply result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, 4\n"
                + "mov r2, 8\n"
                + "mov r3, #2\n"
                + "mla r0, r1, r2, r3\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Multiply result is wrong.", 34, registerFile.getValue("r0"));
    }

    /**
     * Test of visitMlas method, of class MultiplyAndDivideVisitor.
     */
    @Test
    public void testVisitMlas() {
        String code = "mlas r0, r1, r3, r4\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Multiply result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertTrue("Zero flag is wrong.", cpsr.isZero());

        code = "mov r1, 4\n"
                + "mov r2, 8\n"
                + "mov r3, #2\n"
                + "mlas r0, r1, r2, r3\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Multiply result is wrong.", 34, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());
    }

    /**
     * Test of visitMls method, of class MultiplyAndDivideVisitor.
     */
    @Test
    public void testVisitMls() {
        String code = "mls r0, r1, r3, r4\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Multiply result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, 4\n"
                + "mov r2, 8\n"
                + "mov r3, #2\n"
                + "mls r0, r1, r2, r3\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Multiply result is wrong.", -30, registerFile.getValue("r0"));
    }

    /**
     * Test of visitSdiv method, of class MultiplyAndDivideVisitor.
     */
    @Test
    public void testVisitSdiv() {
        String code = "ldr r0, =50\n"
                + "mov r1, 2\n"
                + "sdiv r0, r0, r1\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Multiply result is wrong.", 25, registerFile.getValue("r0"));

        code = "ldr r0, =0xfffffffe\n"
                + "ldr r1, =0xffffffff\n"
                + "sdiv r0, r0, r1\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Multiply result is wrong.", 2, registerFile.getValue("r0"));
    }

    /**
     * Test of visitUdiv method, of class MultiplyAndDivideVisitor.
     */
    @Test
    public void testVisitUdiv() {
        String code = "ldr r0, =50\n"
                + "ldr r1, =0xffffffff\n"
                + "udiv r0, r0, r1\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Multiply result is wrong.", Integer.divideUnsigned(50, 0xffff_ffff),
                registerFile
                        .getValue("r0"));

        code = "ldr r0, =0xffffffff\n"
                + "mov r1, 1\n"
                + "udiv r0, r0, r1\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Multiply result is wrong.", 0xffff_ffff, registerFile.getValue("r0"));
    }
}
