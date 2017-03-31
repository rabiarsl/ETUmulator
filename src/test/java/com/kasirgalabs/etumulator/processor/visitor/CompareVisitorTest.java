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
import com.kasirgalabs.etumulator.langtools.LinkerAndLoader;
import com.kasirgalabs.etumulator.processor.BaseProcessor;
import com.kasirgalabs.etumulator.processor.CPSR;
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.ProcessorUnits;
import org.junit.Test;

public class CompareVisitorTest {
    private final LinkerAndLoader linkerAndLoader;
    private final CPSR cpsr;
    private final Processor processor;

    public CompareVisitorTest() {
        ProcessorUnits processorUnits = new ProcessorUnits();
        linkerAndLoader = new LinkerAndLoader(processorUnits.getMemory());
        cpsr = processorUnits.getCPSR();
        processor = new BaseProcessor(processorUnits);
    }

    /**
     * Test of visitCmp method, of class CompareVisitor.
     */
    @Test
    public void testVisitCmp() {
        String code = "cmp r2, #0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        code = "cmp r2, 8\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        code = "mov r0, #0xf0\n"
                + "cmp r0, 0xf0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        code = "ldr r1, =#0x80000000\n"
                + "ldr r2, =0xffffffff\n"
                + "cmp r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());
    }

    /**
     * Test of visitCmn method, of class CompareVisitor.
     */
    @Test
    public void testVisitCmn() {
        String code = "cmn r1,  #0\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        code = "cmn r2, 8\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        code = "ldr r1, =#0x80000000\n"
                + "ldr r2, =0xffffffff\n"
                + "cmn r1, r2\n";
        processor.run(linkerAndLoader.linkAndLoad(code));
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", true, cpsr.isOverflow());
    }
}
