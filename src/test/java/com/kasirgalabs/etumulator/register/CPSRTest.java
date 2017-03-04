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
    /**
     * Test of updateNZ method, of class CPSR.
     */
    @Test
    public void testUpdateNZ() {
        CPSR.updateNZ(1);
        assertEquals("Negative flag is wrong.", false, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", false, CPSR.isZero());

        CPSR.updateNZ(0);
        assertEquals("Negative flag is wrong.", false, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", true, CPSR.isZero());

        CPSR.updateNZ(-1);
        assertEquals("Negative flag is wrong.", true, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", false, CPSR.isZero());

        CPSR.updateNZ(Integer.MAX_VALUE);
        assertEquals("Negative flag is wrong.", false, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", false, CPSR.isZero());

        CPSR.updateNZ(Integer.MIN_VALUE);
        assertEquals("Negative flag is wrong.", true, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", false, CPSR.isZero());
    }

    /**
     * Test of additionUpdateNZV method, of class CPSR.
     */
    @Test
    public void testAdditionUpdateNZV() {
        CPSR.additionUpdateNZV(1, 1);
        assertEquals("Negative flag is wrong.", false, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", false, CPSR.isZero());
        assertEquals("Overflow flag is wrong.", false, CPSR.isOverflow());

        CPSR.additionUpdateNZV(0, 0);
        assertEquals("Negative flag is wrong.", false, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", true, CPSR.isZero());
        assertEquals("Overflow flag is wrong.", false, CPSR.isOverflow());

        CPSR.additionUpdateNZV(-1, -1);
        assertEquals("Negative flag is wrong.", true, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", false, CPSR.isZero());
        assertEquals("Overflow flag is wrong.", false, CPSR.isOverflow());

        CPSR.additionUpdateNZV(Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals("Negative flag is wrong.", true, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", false, CPSR.isZero());
        assertEquals("Overflow flag is wrong.", true, CPSR.isOverflow());

        CPSR.additionUpdateNZV(Integer.MIN_VALUE, Integer.MIN_VALUE);
        assertEquals("Negative flag is wrong.", false, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", true, CPSR.isZero());
        assertEquals("Overflow flag is wrong.", true, CPSR.isOverflow());

        CPSR.additionUpdateNZV(Integer.MAX_VALUE, Integer.MIN_VALUE);
        assertEquals("Negative flag is wrong.", true, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", false, CPSR.isZero());
        assertEquals("Overflow flag is wrong.", false, CPSR.isOverflow());
    }

    /**
     * Test of subtractionUpdateNZV method, of class CPSR.
     */
    @Test
    public void testSubtractionUpdateNZV() {
        CPSR.subtractionUpdateNZV(1, 1);
        assertEquals("Negative flag is wrong.", false, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", true, CPSR.isZero());
        assertEquals("Overflow flag is wrong.", false, CPSR.isOverflow());

        CPSR.subtractionUpdateNZV(0, 0);
        assertEquals("Negative flag is wrong.", false, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", true, CPSR.isZero());
        assertEquals("Overflow flag is wrong.", false, CPSR.isOverflow());

        CPSR.subtractionUpdateNZV(-1, -1);
        assertEquals("Negative flag is wrong.", false, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", true, CPSR.isZero());
        assertEquals("Overflow flag is wrong.", false, CPSR.isOverflow());

        CPSR.subtractionUpdateNZV(Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals("Negative flag is wrong.", false, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", true, CPSR.isZero());
        assertEquals("Overflow flag is wrong.", false, CPSR.isOverflow());

        CPSR.subtractionUpdateNZV(Integer.MIN_VALUE, Integer.MIN_VALUE);
        assertEquals("Negative flag is wrong.", false, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", true, CPSR.isZero());
        assertEquals("Overflow flag is wrong.", false, CPSR.isOverflow());

        CPSR.subtractionUpdateNZV(Integer.MAX_VALUE, Integer.MIN_VALUE);
        assertEquals("Negative flag is wrong.", true, CPSR.isNegative());
        assertEquals("Zero flag is wrong.", false, CPSR.isZero());
        assertEquals("Overflow flag is wrong.", true, CPSR.isOverflow());
    }
}
