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
import java.util.LinkedList;

@Singleton
public class Stack implements Observable {
    private final LinkedList<Integer> list = new LinkedList<>();
    private final Dispatcher dispatcher;

    public Stack() {
        this.dispatcher = new BaseDispatcher();
    }

    @Inject
    public Stack(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void addObserver(Observer listener) {
        dispatcher.addObserver(listener);
    }

    public void push(Integer item) {
        list.push(item);
        dispatcher.notifyObservers(Stack.class, "push");
    }

    public int pop() {
        if(list.isEmpty()) {
            return (int) (Math.random() * Integer.MAX_VALUE);
        }
        int result = list.pop();
        dispatcher.notifyObservers(Stack.class, "pop");
        return result;
    }

    public int peek() {
        return list.peek();
    }
}
