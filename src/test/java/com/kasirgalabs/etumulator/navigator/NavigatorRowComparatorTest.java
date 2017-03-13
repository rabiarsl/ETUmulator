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
        assertEquals("Compare result is wrong.", instance.compare("1", "10"), -1);
        assertEquals("Compare result is wrong.", instance.compare("1", "11"), -1);
        assertEquals("Compare result is wrong.", instance.compare("r1", "r10"), -1);
        assertEquals("Compare result is wrong.", instance.compare("r1", "r11"), -1);
        assertEquals("Compare result is wrong.", instance.compare("r1", "r0"), 1);
        assertEquals("Compare result is wrong.", instance.compare("2", "1"), 1);
        assertEquals("Compare result is wrong.", instance.compare("0", "123"), -1);
        assertEquals("Compare result is wrong.", instance.compare("0xffffffff", "0x1"), -1);
        assertEquals("Compare result is wrong.", instance.compare("0x0", "0x1"), -1);
        assertEquals("Compare result is wrong.", instance.compare("0x6", "0x5"), 1);
        assertEquals("Compare result is wrong.", instance.compare(
                "00000000000000000000000000000000",
                "00000000000000000000000000000001"), -1);
        assertEquals("Compare result is wrong.", instance.compare(
                "11111111111111111111111111111111",
                "11111111111111111111111111111110"), 1);
    }
}
