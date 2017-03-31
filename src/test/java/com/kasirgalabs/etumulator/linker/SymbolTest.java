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
package com.kasirgalabs.etumulator.linkerAndLoader;

import com.kasirgalabs.etumulator.langtools.Symbol;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SymbolTest {
    /**
     * Test of equals method, of class Symbol.
     */
    @Test
    public void testEquals() {
        Symbol symbol = new Symbol("symbol", 0);
        Symbol symbol2 = new Symbol("symbol2", 1);
        Symbol symbol3 = new Symbol("symbol", 2);
        assertEquals("Symbol's equals method does not work properly.", false, symbol.equals(this));
        assertEquals("Symbol's equals method does not work properly.", false, symbol.equals(null));
        assertEquals("Symbol's equals method does not work properly.", true, symbol.equals(symbol));
        assertEquals("Symbol's equals method does not work properly.", false,
                symbol.equals(symbol2));
        assertEquals("Symbol's equals method does not work properly.", true,
                symbol.equals(symbol3));
    }
}
