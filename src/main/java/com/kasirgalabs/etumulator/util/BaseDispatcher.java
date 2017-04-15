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
package com.kasirgalabs.etumulator.util;

import java.util.ArrayList;
import java.util.List;

public class BaseDispatcher implements Dispatcher {
    private final List<Observer> observers;

    public BaseDispatcher() {
        observers = new ArrayList<>(10);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(Class<?> clazz, Object arg) {
        for(int i = 0; i < observers.size(); i++) {
            observers.get(i).update(clazz, arg);
        }
    }

    @Override
    public void notifyObservers(Class<?> clazz) {
        notifyObservers(clazz, null);
    }
}
