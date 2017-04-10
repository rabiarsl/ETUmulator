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

@Singleton
public class PC implements Observable {
    private int pc;
    private final Dispatcher dispatcher;

    public PC() {
        dispatcher = new BaseDispatcher();
    }

    @Inject
    public PC(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void addObserver(Observer listener) {
        dispatcher.addObserver(listener);
    }

    public void increment() {
        pc++;
        dispatcher.notifyObservers(PC.class);
    }

    public void setValue(int value) {
        pc = value;
        dispatcher.notifyObservers(PC.class);
    }

    public int getValue() {
        return pc;
    }

    public void reset() {
        pc = 0;
        dispatcher.notifyObservers(PC.class);
    }
}
