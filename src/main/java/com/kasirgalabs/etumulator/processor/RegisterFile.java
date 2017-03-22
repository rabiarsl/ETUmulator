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

@Singleton
public class RegisterFile implements Observable {
    private final Map<String, Integer> registers;
    private final List<Observer> observers;

    public RegisterFile() {
        registers = new HashMap<>(13);
        observers = new ArrayList<>();
        for(int i = 0; i < 13; i++) {
            registers.put("r" + Integer.toString(i), 0);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach((observer) -> {
            observer.update(RegisterFile.class);
        });
    }

    public int getValue(String registerName) {
        return registers.get(registerName);
    }

    public void setValue(String registerName, int value) {
        registers.replace(registerName, value);
        notifyObservers();
    }

    public void reset() {
        registers.clear();
        for(int i = 0; i < 13; i++) {
            registers.put("r" + Integer.toString(i), 0);
        }
    }
}
