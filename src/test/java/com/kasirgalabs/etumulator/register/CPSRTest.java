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
package com.kasirgalabs.etumulator.register;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CPSRTest {
    private final CPSR cpsr;

    public CPSRTest() {
        cpsr = new CPSR();
    }

    /**
     * Test of updateNZ method, of class CPSR.
     */
    @Test
    public void testUpdateNZ() {
        cpsr.reset();
        cpsr.updateNZ(1);
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());

        cpsr.reset();
        cpsr.updateNZ(0);
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());

        cpsr.reset();
        cpsr.updateNZ(-1);
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());

        cpsr.reset();
        cpsr.updateNZ(Integer.MAX_VALUE);
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());

        cpsr.reset();
        cpsr.updateNZ(Integer.MIN_VALUE);
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
    }

    /**
     * Test of additionUpdateNZV method, of class CPSR.
     */
    @Test
    public void testAdditionUpdateNZV() {
        cpsr.reset();
        cpsr.additionUpdateNZV(1, 1);
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        cpsr.reset();
        cpsr.additionUpdateNZV(0, 0);
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        cpsr.reset();
        cpsr.additionUpdateNZV(-1, -1);
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        cpsr.reset();
        cpsr.additionUpdateNZV(Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", true, cpsr.isOverflow());

        cpsr.reset();
        cpsr.additionUpdateNZV(Integer.MIN_VALUE, Integer.MIN_VALUE);
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", true, cpsr.isOverflow());

        cpsr.reset();
        cpsr.additionUpdateNZV(Integer.MAX_VALUE, Integer.MIN_VALUE);
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());
    }

    /**
     * Test of subtractionUpdateNZV method, of class CPSR.
     */
    @Test
    public void testSubtractionUpdateNZV() {
        cpsr.reset();
        cpsr.subtractionUpdateNZV(1, 1);
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        cpsr.reset();
        cpsr.subtractionUpdateNZV(0, 0);
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        cpsr.reset();
        cpsr.subtractionUpdateNZV(-1, -1);
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        cpsr.reset();
        cpsr.subtractionUpdateNZV(Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        cpsr.reset();
        cpsr.subtractionUpdateNZV(Integer.MIN_VALUE, Integer.MIN_VALUE);
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", false, cpsr.isOverflow());

        cpsr.reset();
        cpsr.subtractionUpdateNZV(Integer.MAX_VALUE, Integer.MIN_VALUE);
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", true, cpsr.isOverflow());
    }
}
