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

import com.kasirgalabs.etumulator.processor.InstructionUnit;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class LinkerTest extends InstructionUnit {
    private final Linker linker;
    private List<Label> expectedLabel;

    public LinkerTest() {
        linker = new Linker(this, null);
        expectedLabel = new ArrayList<Label>();
    }

    /**
     * Test of linkAndLoad method, of class Linker.
     */
    @Test
    public void testLinkAndLoad() {
        expectedLabel.add(new Label("target"));
        linker.linkAndLoad("b target\n"
                + "// comment\n"
                + "mov r0, #0\n"
                + "target:\n");

        expectedLabel.clear();
        expectedLabel.add(new Label("target"));
        linker.linkAndLoad("target:\n"
                + "// comment\n"
                + "mov r0, #0\n"
                + "b target\n");

        expectedLabel.clear();
        expectedLabel.add(new Label("target"));
        linker.linkAndLoad("target:\n"
                + "// comment\n"
                + "mov r0, #0\n"
                + "b target\n"
                + "target2:\n");

        expectedLabel.clear();
        expectedLabel.add(new Label("target"));
        linker.linkAndLoad("target:\n"
                + "// comment\n"
                + "mov r0, #0\n"
                + "b target\n"
                + "target2:\n"
                + "test: .asciz \"asd\"\n");
    }

    @Override
    public void loadLabels(List<Label> labels) {
        assertEquals("Labels are not resolved correctly.", expectedLabel, labels);
    }
}
