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
package com.kasirgalabs.etumulator.navigator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NavigatorRowComparatorTest {
    /**
     * Test of compare method, of class NavigatorRowComparator.
     */
    @Test
    public void testCompare() {
        NavigatorRowComparator instance = new NavigatorRowComparator();
        assertEquals("Compare result is wrong.", -1, instance.compare("1", "10"));
        assertEquals("Compare result is wrong.", -1, instance.compare("1", "11"));
        assertEquals("Compare result is wrong.", -1, instance.compare("r1", "r10"));
        assertEquals("Compare result is wrong.", -1, instance.compare("r1", "r11"));
        assertEquals("Compare result is wrong.", 1, instance.compare("r1", "r0"));
        assertEquals("Compare result is wrong.", 1, instance.compare("2", "1"));
        assertEquals("Compare result is wrong.", -1, instance.compare("0", "123"));
        assertEquals("Compare result is wrong.", -1, instance.compare("0xffffffff", "0x1"));
        assertEquals("Compare result is wrong.", -1, instance.compare("0x0", "0x1"));
        assertEquals("Compare result is wrong.", 1, instance.compare("0x6", "0x5"));
        assertEquals("Compare result is wrong.", -1, instance.compare(
                "00000000000000000000000000000000",
                "00000000000000000000000000000001"));
        assertEquals("Compare result is wrong.", 1, instance.compare(
                "11111111111111111111111111111111",
                "11111111111111111111111111111110"));
        assertEquals("Compare result is wrong.", -1, instance.compare("PC", "r13"));
        assertEquals("Compare result is wrong.", -1, instance.compare("r13", "PC"));
    }
}
