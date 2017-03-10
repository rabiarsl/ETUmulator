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

import com.kasirgalabs.etumulator.pattern.Observable;
import com.kasirgalabs.etumulator.pattern.Observer;
import com.kasirgalabs.etumulator.register.IntegerRegister;
import com.kasirgalabs.etumulator.register.Register;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterFile implements Observable {
    public static final int NUM_OF_REGS = 12;
    private final Map<String, IntegerRegister> registers;
    private final List<Observer> observers;

    public RegisterFile() {
        registers = new HashMap<>(NUM_OF_REGS);
        observers = new ArrayList<>();
        for(int i = 0; i < NUM_OF_REGS; i++) {
            registers.put("r" + Integer.toString(i), new IntegerRegister());
        }
    }

    public int getValue(String registerName) {
        return registers.get(registerName).getValue();
    }

    public void update(String registerName, int value) {
        registers.get(registerName).setValue(value);
        notifyObservers();
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

    public void reset() {
        registers.values().forEach((object) -> {
            Register register = object;
            register.setValue(0);
        });
    }
}
