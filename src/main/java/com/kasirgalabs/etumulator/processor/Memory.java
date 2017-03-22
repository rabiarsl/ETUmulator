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
import com.kasirgalabs.etumulator.pattern.Observable;
import com.kasirgalabs.etumulator.pattern.Observer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Singleton
public class Memory implements Observable {
    private Map<Integer, Byte> memory;
    private final List<Observer> observers;

    public Memory() {
        memory = new HashMap<>();
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach((observer) -> {
            observer.update(Memory.class);
        });
    }

    public Byte get(int address) {
        if(memory.containsKey(address)) {
            return memory.get(address);
        }
        return (byte) (Math.random() * Byte.MAX_VALUE);
    }

    public void set(int address, byte value) {
        memory.put(address, value);
        notifyObservers();
    }

    public boolean isAddressEmpty(int address) {
        return !memory.containsKey(address);
    }

    public Set<Entry<Integer, Byte>> entrySet() {
        return memory.entrySet();
    }

    public Map<Integer, Byte> getAll() {
        return memory;
    }
}
