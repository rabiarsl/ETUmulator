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
package com.kasirgalabs.etumulator.register;

import com.kasirgalabs.etumulator.pattern.Observable;
import com.kasirgalabs.etumulator.pattern.Observer;
import java.util.ArrayList;
import java.util.List;

public class RegisterFile implements Observable {
    public static final int NUM_OF_REGS = 15;
    private final IntegerRegister[] registers;
    private final List<Observer> observers;

    public RegisterFile() {
        observers = new ArrayList<>();
        registers = new IntegerRegister[NUM_OF_REGS];
        for(int i = 0; i < NUM_OF_REGS; i++) {
            registers[i] = new IntegerRegister();
        }
    }

    public long getValue(int registerNumber) {
        return registers[registerNumber].getValue();
    }

    public void update(int registerNumber, long value) {
        registers[registerNumber].setValue(value);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach((observer) -> {
            observer.update();
        });
    }

    public void reset() {
        for(int i = 0; i < NUM_OF_REGS; i++) {
            registers[i].reset();
        }
    }
}
