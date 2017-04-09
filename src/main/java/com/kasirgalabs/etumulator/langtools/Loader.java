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
package com.kasirgalabs.etumulator.langtools;

import com.kasirgalabs.etumulator.processor.Memory;
import java.util.List;

public class Loader {
    private final Memory memory;

    public Loader(Memory memory) {
        this.memory = memory;
    }

    public void load(ExecutableCode executablecode) {
        List<Data> data = executablecode.getData();
        data.forEach(item -> {
            String value = item.getValue();
            int address = item.getAddress();
            for(int i = 0; i < value.length(); i++) {
                memory.set(address + i, (byte) value.charAt(i));
            }
        });
    }
}
