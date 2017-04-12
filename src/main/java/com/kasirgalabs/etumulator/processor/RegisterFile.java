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

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kasirgalabs.etumulator.util.BaseDispatcher;
import com.kasirgalabs.etumulator.util.Dispatcher;
import com.kasirgalabs.etumulator.util.Observable;
import com.kasirgalabs.etumulator.util.Observer;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class RegisterFile implements Observable {
    private final Map<String, Integer> registers = new HashMap<>(14);
    private final Dispatcher dispatcher;

    public RegisterFile() {
        for(int i = 0; i < 13; i++) {
            registers.put("r" + Integer.toString(i), 0);
        }
        this.dispatcher = new BaseDispatcher();
    }

    @Inject
    public RegisterFile(Dispatcher dispatcher) {
        for(int i = 0; i < 13; i++) {
            registers.put("r" + Integer.toString(i), 0);
        }
        this.dispatcher = dispatcher;
    }

    @Override
    public void addObserver(Observer listener) {
        dispatcher.addObserver(listener);
    }

    public void setValue(String registerName, int value) {
        registers.replace(registerName, value);
        dispatcher.notifyObservers(RegisterFile.class, registerName);
    }

    public int getValue(String registerName) {
        return registers.get(registerName);
    }

    public void reset() {
        registers.clear();
        for(int i = 0; i < 13; i++) {
            registers.put("r" + Integer.toString(i), 0);
        }
        dispatcher.notifyObservers(Stack.class, "clear");
    }
}
