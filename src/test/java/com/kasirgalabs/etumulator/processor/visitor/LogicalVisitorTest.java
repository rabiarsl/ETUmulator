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

public class LogicalVisitorTest {
    private final LinkerAndLoader linkerAndLoader;
    private final RegisterFile registerFile;
    private final CPSR cpsr;
    private final Processor processor;

    public LogicalVisitorTest() {
        ProcessorUnits processorUnits = new BaseProcessorUnits();
        linkerAndLoader = new LinkerAndLoader(processorUnits.getMemory());
        registerFile = processorUnits.getRegisterFile();
        cpsr = processorUnits.getCPSR();
        processor = new BaseProcessor(processorUnits);
    }

    /**
     * Test of visitTst method, of class LogicalVisitor.
     */
    @Test
    public void testVisitTst() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "tst r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertTrue("Zero flag is wrong.", cpsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "tst r1, r1\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertTrue("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());
    }

    /**
     * Test of visitTeq method, of class LogicalVisitor.
     */
    @Test
    public void testVisitTeq() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "teq r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "teq r1, r1\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertTrue("Zero flag is wrong.", cpsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "teq r1, 0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertTrue("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());
    }

    /**
     * Test of visitAnd method, of class LogicalVisitor.
     */
    @Test
    public void testVisitAnd() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "and r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("AND result is wrong.", 0, registerFile.getValue("r0"));

        code = "ldr r1, =0xffffffff\n"
                + "and r0, r1, 0xf\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("AND result is wrong.", 0xf, registerFile.getValue("r0"));
    }

    /**
     * Test of visitAnds method, of class LogicalVisitor.
     */
    @Test
    public void testVisitAnds() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "ands r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("AND result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertTrue("Zero flag is wrong.", cpsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "ands r0, r1, r1\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("AND result is wrong.", -1, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());
    }

    /**
     * Test of visitEor method, of class LogicalVisitor.
     */
    @Test
    public void testVisitEor() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "eor r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("XOR result is wrong.", 1, registerFile.getValue("r0"));

        code = "ldr r1, =0xffffffff\n"
                + "eor r0, r1, r1\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("XOR result is wrong.", 0, registerFile.getValue("r0"));
    }

    /**
     * Test of visitEors method, of class LogicalVisitor.
     */
    @Test
    public void testVisitEors() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "eors r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("XOR result is wrong.", 1, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "eors r0, r1, r1\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("XOR result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertTrue("Zero flag is wrong.", cpsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "eors r0, r1, 0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("XOR result is wrong.", -1, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());
    }

    /**
     * Test of visitOrr method, of class LogicalVisitor.
     */
    @Test
    public void testVisitOrr() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "orr r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("OR result is wrong.", 1, registerFile.getValue("r0"));

        code = "ldr r1, =0xffffffff\n"
                + "orr r0, r1, 0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("OR result is wrong.", -1, registerFile.getValue("r0"));

        code = "ldr r1, =0x0f0f0f0f\n"
                + "ldr r2, = 0xf0f0f0f0\n"
                + "orr r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("OR result is wrong.", -1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitOrrs method, of class LogicalVisitor.
     */
    @Test
    public void testVisitOrrs() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "orrs r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("OR result is wrong.", 1, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "orrs r0, r1, 0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("OR result is wrong.", -1, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());

        code = "ldr r1, =0x0f0f0f0f\n"
                + "ldr r2, = 0xf0f0f0f0\n"
                + "orrs r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("OR result is wrong.", -1, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());

        code = "mov r1, 0\n"
                + "mov r2, 0\n"
                + "orrs r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("OR result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertTrue("Zero flag is wrong.", cpsr.isZero());
    }

    /**
     * Test of visitOrn method, of class LogicalVisitor.
     */
    @Test
    public void testVisitOrn() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "orn r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("OR result is wrong.", -2, registerFile.getValue("r0"));

        code = "ldr r1, =0xffffffff\n"
                + "orn r0, r1, 0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("OR result is wrong.", 0xffff_ffff | ~0, registerFile.getValue("r0"));

        code = "ldr r1, =0x0f0f0f0f\n"
                + "ldr r2, = 0xf0f0f0f0\n"
                + "orn r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("OR result is wrong.", 0x0f0f_0f0f, registerFile.getValue("r0"));
    }

    /**
     * Test of visitOrns method, of class LogicalVisitor.
     */
    @Test
    public void testVisitOrns() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "orns r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("OR result is wrong.", -2, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "orns r0, r1, r1\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("OR result is wrong.", 0xffff_ffff | ~0xffff_ffff, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "orns r0, r1, 0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("OR result is wrong.", -1, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());

        code = "mov r1, #0\n"
                + "orns r0, r1, 0xffffffff\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("OR result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertTrue("Zero flag is wrong.", cpsr.isZero());
    }

    /**
     * Test of visitBic method, of class LogicalVisitor.
     */
    @Test
    public void testVisitBic() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "bic r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("AND result is wrong.", 0, registerFile.getValue("r0"));

        code = "ldr r1, =0xffffffff\n"
                + "bic r0, r1, 0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("AND result is wrong.", 0xffff_ffff & ~0, registerFile.getValue("r0"));

        code = "ldr r1, =0x0f0f0f0f\n"
                + "ldr r2, = 0xf0f0f0f0\n"
                + "bic r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("AND result is wrong.", 0x0f0f_0f0f & ~0xf0f0_f0f0, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBics method, of class LogicalVisitor.
     */
    @Test
    public void testVisitBics() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "bics r0, r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("AND result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertTrue("Zero flag is wrong.", cpsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "bics r0, r1, r1\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("AND result is wrong.", 0xffff_ffff & ~0xffff_ffff, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertTrue("Zero flag is wrong.", cpsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "bics r0, r1, 0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("AND result is wrong.", 0xffff_ffff | ~0, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());

        code = "bics r0, r1, 0xffffffff\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("AND result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertTrue("Zero flag is wrong.", cpsr.isZero());
    }
}
