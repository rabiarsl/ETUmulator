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

public class MoveVisitorTest {
    private final LinkerAndLoader linkerAndLoader;
    private final RegisterFile registerFile;
    private final CPSR cpsr;
    private final Processor processor;

    public MoveVisitorTest() {
        ProcessorUnits processorUnits = new BaseProcessorUnits();
        linkerAndLoader = new LinkerAndLoader(processorUnits.getMemory());
        registerFile = processorUnits.getRegisterFile();
        cpsr = processorUnits.getCPSR();
        processor = new BaseProcessor(processorUnits);
    }

    /**
     * Test of visitMov method, of class MoveVisitor.
     */
    @Test
    public void testVisitMov() {
        String code = "mov r0, #0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Move result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r0, #4\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Move result is wrong.", 4, registerFile.getValue("r0"));

        code = "mov r0, 0xf\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Move result is wrong.", 0xf, registerFile.getValue("r0"));

        code = "mov r1, 8\n"
                + "mov r0, r1\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Move result is wrong.", 8, registerFile.getValue("r0"));
    }

    /**
     * Test of visitMovs method, of class MoveVisitor.
     */
    @Test
    public void testVisitMovs() {
        String code = "movs r0, #0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Move result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertTrue("Zero flag is wrong.", cpsr.isZero());

        code = "mov r1, 8\n"
                + "movs r0, r1\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Move result is wrong.", 8, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());
    }

    /**
     * Test of visitMvn method, of class MoveVisitor.
     */
    @Test
    public void testVisitMvn() {
        String code = "mvn r0, #0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Move result is wrong.", ~0, registerFile.getValue("r0"));

        code = "mvn r0, #4\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Move result is wrong.", ~4, registerFile.getValue("r0"));

        code = "mvn r0, 0xf\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Move result is wrong.", ~0xf, registerFile.getValue("r0"));

        code = "mov r1, 8\n"
                + "mvn r0, r1\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Move result is wrong.", ~8, registerFile.getValue("r0"));
    }

    /**
     * Test of visitMvns method, of class MoveVisitor.
     */
    @Test
    public void testVisitMvns() {
        String code = "mvns r0, #0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Move result is wrong.", ~0, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());

        code = "mov r1, 8\n"
                + "mvns r0, r1\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Move result is wrong.", ~8, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", cpsr.isNegative());
        assertFalse("Zero flag is wrong.", cpsr.isZero());
    }

    /**
     * Test of visitMovt method, of class MoveVisitor.
     */
    @Test
    public void testVisitMovt() {
        String code = "mov r0, #1\n"
                + "movt r0, #0x0000ffff\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Move result is wrong.", (0x0000_ffff << 16) + 1, registerFile.getValue("r0"));

        code = "ldr r0, =#0xffffffff\n"
                + "movt r0, #0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Move result is wrong.", 0x0000_ffff, registerFile.getValue("r0"));
    }
}
