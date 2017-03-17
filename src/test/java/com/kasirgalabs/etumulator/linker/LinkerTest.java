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

import com.kasirgalabs.etumulator.error.LabelError;
import com.kasirgalabs.etumulator.processor.InstructionUnit;
import com.kasirgalabs.etumulator.processor.MemoryUnit;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class LinkerTest {
    private static final List<Label> EXPECTED_BRANCH_LABELS = new ArrayList<>();
    private static final List<Data> EXPECTED_DATA = new ArrayList<>();

    private final Linker linker;

    public LinkerTest() {
        linker = new Linker(new LinkerTestInstructionUnit(), new LinkerTestMemoryUnit());
    }

    /**
     * Test of linkAndLoad method, of class Linker.
     */
    @Test
    public void testLinkAndLoad() {
        EXPECTED_BRANCH_LABELS.add(new Label("target"));
        linker.linkAndLoad("b target\n"
                + "// comment\n"
                + "mov r0, #0\n"
                + "target:\n");

        EXPECTED_BRANCH_LABELS.clear();
        EXPECTED_BRANCH_LABELS.add(new Label("target"));
        linker.linkAndLoad("target:\n"
                + "// comment\n"
                + "mov r0, #0\n"
                + "b target\n");

        EXPECTED_BRANCH_LABELS.clear();
        EXPECTED_BRANCH_LABELS.add(new Label("target"));
        linker.linkAndLoad("target:\n"
                + "// comment\n"
                + "mov r0, #0\n"
                + "b target\n"
                + "target2:\n");

        EXPECTED_BRANCH_LABELS.clear();
        EXPECTED_BRANCH_LABELS.add(new Label("target"));
        linker.linkAndLoad("target:\n"
                + "// comment\n"
                + "mov r0, #0\n"
                + "b target\n"
                + "target2:\n"
                + "test: .asciz \"asd\"\n");

        EXPECTED_BRANCH_LABELS.clear();
        EXPECTED_BRANCH_LABELS.add(new Label("target"));
        EXPECTED_DATA.clear();
        EXPECTED_DATA.add(new Data("test"));
        linker.linkAndLoad("ldr r1, =test\n"
                + "target:\n"
                + "// comment\n"
                + "mov r0, #0\n"
                + "b target\n"
                + "target2:\n"
                + "test: .asciz \"asd\"\n");

        EXPECTED_BRANCH_LABELS.clear();
        EXPECTED_DATA.clear();
        EXPECTED_DATA.add(new Data("test"));
        linker.linkAndLoad("ldr r1, =test\n"
                + "ldr r0, =test\n"
                + "test: .asciz \"asd\"\n"
                + "UNUSED_LABEL: .asciz \"xxx\"\n");

        EXPECTED_BRANCH_LABELS.clear();
        EXPECTED_DATA.clear();
        try {
            linker.linkAndLoad("beq NON_DEFINED_LABEL\n");
            fail("LabelError did not get thrown.");
        } catch(LabelError labelError) {
        }

        EXPECTED_BRANCH_LABELS.clear();
        EXPECTED_DATA.clear();
        try {
            linker.linkAndLoad("ldr r1, =NON_DEFINED_LABEL\n");
            fail("LabelError did not get thrown.");
        } catch(LabelError labelError) {
        }
    }

    private static class LinkerTestInstructionUnit extends InstructionUnit {
        @Override
        public void loadLabels(List<Label> labels) {
            assertEquals("Labels are not resolved correctly.", EXPECTED_BRANCH_LABELS, labels);
        }
    }

    private static class LinkerTestMemoryUnit extends MemoryUnit {
        @Override
        public void loadData(List<Data> data) {
            assertEquals("Data are not resolved correctly.", EXPECTED_DATA, data);
        }
    }
}
