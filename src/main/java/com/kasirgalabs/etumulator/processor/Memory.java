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

import com.google.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

@Singleton
public class Memory extends Observable {
    private final Map<Integer, Byte> memory;

    public Memory() {
        memory = new HashMap<>();
    }

    public Byte get(int address) {
        if(memory.containsKey(address)) {
            return memory.get(address);
        }
        return (byte) (Math.random() * Byte.MAX_VALUE);
    }

    public void set(int address, byte value) {
        memory.put(address, value);
        setChanged();
        notifyObservers(address);
    }

    public boolean isAddressEmpty(int address) {
        return !memory.containsKey(address);
    }
}
