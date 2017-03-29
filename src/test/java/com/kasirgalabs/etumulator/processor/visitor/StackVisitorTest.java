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
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.ProcessorUnits;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.etumulator.processor.Stack;
import org.junit.Test;

public class StackVisitorTest {
    private final RegisterFile registerFile;
    private final Stack stack;
    private final Processor processor;

    public StackVisitorTest() {
        ProcessorUnits processorUnits = new ProcessorUnits();
        registerFile = processorUnits.getRegisterFile();
        stack = processorUnits.getStack();
        processor = new BaseProcessor(processorUnits);
    }

    /**
     * Test of visitPush method, of class StackVisitor.
     */
    @Test
    public void testVisitPush() {
        String code = "mov r0, #4\n"
                + "ldr r1, =0xffffffff\n"
                + "push {r0,r1}\n";
        processor.run(code, null);
        assertEquals("Push result is wrong.", 0xffff_ffff, stack.pop());
        assertEquals("Push result is wrong.", 4, stack.pop());
    }

    /**
     * Test of visitPop method, of class StackVisitor.
     */
    @Test
    public void testVisitPop() {
        String code = "mov r0, #4\n"
                + "ldr r1, =0xffffffff\n"
                + "push {r0,r1}\n"
                + "pop {r1, r0}\n";
        processor.run(code, null);
        assertEquals("Pop result is wrong.", 4, registerFile.getValue("r0"));
        assertEquals("Pop result is wrong.", 0xffff_ffff, registerFile.getValue("r1"));
    }
}
