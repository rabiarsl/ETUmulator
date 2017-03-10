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
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CPUStack extends Stack<Integer> implements Observable {
    private final List<Observer> observers;

    public CPUStack() {
        observers = new ArrayList<>();
    }

    @Override
    public Integer push(Integer item) {
        super.push(item);
        notifyObservers();
        return item;
    }

    @Override
    public Integer pop() {
        int item = super.pop();
        notifyObservers();
        return item;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach((observer) -> {
            observer.update(CPUStack.class);
        });
    }
}
