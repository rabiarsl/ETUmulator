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
package com.kasirgalabs.etumulator.processor;

import com.kasirgalabs.etumulator.linker.Label;
import java.util.ArrayList;
import java.util.List;

public class InstructionUnit {
    private char[][] instructions;
    private List<Label> labels;
    private int pc;

    public InstructionUnit() {
        pc = 0;
    }

    public void loadLabels(List<Label> labels) {
        this.labels = new ArrayList<>(labels.size());
        labels.forEach((label) -> {
            this.labels.add(new Label(label));
        });
    }

    public char[] fetchNext() {
        char[] instruction = instructions[pc];
        pc++;
        return instruction;
    }

    public char[] jumpToLabel(String label) {
        pc = labels.get(labels.indexOf(new Label(label))).getAddress();
        return instructions[pc];
    }

    public boolean hasNext() {
        return pc < instructions.length;
    }

    public void loadInstructions(char[][] instructions) {
        this.instructions = new char[instructions.length][];
        for(int i = 0; i < instructions.length; i++) {
            this.instructions[i] = new char[instructions[i].length];
            System.arraycopy(instructions[i], 0, this.instructions[i], 0, instructions[i].length);
        }
        pc = 0;
    }
}
