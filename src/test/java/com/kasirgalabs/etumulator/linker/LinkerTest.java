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
package com.kasirgalabs.etumulator.linker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import com.kasirgalabs.etumulator.processor.Memory;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class LinkerTest {
    private static final int NOT_USED = 0;
    private final Memory memory;
    private final Linker linker;

    public LinkerTest() {
        memory = new Memory();
        linker = new Linker(memory);
    }

    /**
     * Test of link method, of class Linker.
     */
    @Test
    public void testLink() {
        final Set<Symbol> expectedSymbols = new HashSet<>();
        String code = "b label\n"
                + "nop\n"
                + "nop\n"
                + "nop\n"
                + "label:\n";
        expectedSymbols.add(new Symbol("label", NOT_USED));
        assertEquals("Linker does not work properly.", expectedSymbols, linker.link(code));

        expectedSymbols.clear();
        code = "b label0\n"
                + "beq label1\n"
                + "nop\n"
                + "nop\n"
                + "label0:\n"
                + "label1:\n";
        expectedSymbols.add(new Symbol("label0", NOT_USED));
        expectedSymbols.add(new Symbol("label1", NOT_USED));
        assertEquals("Linker does not work properly.", expectedSymbols, linker.link(code));

        expectedSymbols.clear();
        code = "b label\n"
                + "nop\n"
                + "nop\n"
                + "nop\n"
                + "label:\n"
                + "label: .asciz \"asd\"\n";
        try {
            linker.link(code);
            fail("Linker does not work properly.");
        }
        catch(LabelError le) {
        }

        expectedSymbols.clear();
        code = "b label\n"
                + "nop\n"
                + "nop\n"
                + "nop\n"
                + "label: .asciz \"asd\"\n";
        try {
            linker.link(code);
            fail("Linker does not work properly.");
        }
        catch(LabelError le) {
        }

        expectedSymbols.clear();
        code = "ldr r1, =abcde\n"
                + "nop\n"
                + "nop\n"
                + "nop\n"
                + "label: .asciz \"asd\"\n";
        try {
            linker.link(code);
            fail("Linker does not work properly.");
        }
        catch(LabelError le) {
        }

        expectedSymbols.clear();
        code = "ldr r1, =label\n"
                + "nop\n"
                + "nop\n"
                + "nop\n"
                + "label: .asciz \"asd\"\n";
        expectedSymbols.add(new Symbol("label", NOT_USED));
        assertEquals("Linker does not work properly.", expectedSymbols, linker.link(code));

        expectedSymbols.clear();
        code = "b abcde\n"
                + "nop\n"
                + "nop\n"
                + "nop\n"
                + "abcde: .asciz \"asd\"\n";
        try {
            linker.link(code);
            fail("Linker does not work properly.");
        }
        catch(LabelError le) {
        }

        expectedSymbols.clear();
        code = "ldr r1,= label\n"
                + "nop\n"
                + "nop\n"
                + "label: .asciz \"asd\"\n"
                + "label:\n";
        try {
            linker.link(code);
            fail("Linker does not work properly.");
        }
        catch(LabelError le) {
        }

        expectedSymbols.clear();
        code = "ldr r1,= label\n"
                + "nop\n"
                + "nop\n"
                + "label: .asciz \"asd\"\n"
                + "label: .asciz \"te\"\n";
        try {
            linker.link(code);
            fail("Linker does not work properly.");
        }
        catch(LabelError le) {
        }
    }
}
