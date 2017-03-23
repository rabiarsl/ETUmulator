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
import java.util.LinkedList;
import java.util.Observable;

@Singleton
public class Stack extends Observable {
    private final LinkedList<Integer> list;

    public Stack() {
        list = new LinkedList<>();
    }

    public void push(Integer item) {
        list.push(item);
        setChanged();
        notifyObservers("push");
    }

    public int pop() {
        int result = list.pop();
        setChanged();
        notifyObservers("pop");
        return result;
    }

    public int peek() {
        return list.peek();
    }
}
