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

import com.kasirgalabs.etumulator.linker.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryUnit {
    private Map<Integer, Byte> memory;
    private List<Data> data;

    public MemoryUnit() {
        memory = new HashMap<>();
    }

    public void loadData(List<Data> data) {
        this.data = new ArrayList<>(data.size());
        data.forEach((item) -> {
            this.data.add(new Data(item));
        });
    }

    public int getLabelAddress(String label) {
        int index = data.indexOf(new Data(label));
        return data.get(index).getLabel().getAddress();
    }

    public Byte getData(int address) {
        return memory.get(address);
    }

    public void setData(int address, Byte data) {
        memory.put(address, data);
    }
}
