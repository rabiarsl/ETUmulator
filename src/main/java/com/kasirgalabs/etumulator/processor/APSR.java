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
import com.kasirgalabs.etumulator.util.BaseDispatcher;
import com.kasirgalabs.etumulator.util.Dispatcher;
import com.kasirgalabs.etumulator.util.Observable;
import com.kasirgalabs.etumulator.util.Observer;

@Singleton
public class APSR implements Observable {
    private boolean negative;
    private boolean zero;
    private boolean carry;
    private boolean overflow;
    private final Dispatcher dispatcher;

    public APSR() {
        this.dispatcher = new BaseDispatcher();
    }

    public APSR(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void addObserver(Observer listener) {
        dispatcher.addObserver(listener);
    }

    public boolean isNegative() {
        return negative;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
        dispatcher.notifyObservers(APSR.class);
    }

    public boolean isZero() {
        return zero;
    }

    public void setZero(boolean zero) {
        this.zero = zero;
        dispatcher.notifyObservers(APSR.class);
    }

    public boolean isCarry() {
        return carry;
    }

    public void setCarry(boolean carry) {
        this.carry = carry;
        dispatcher.notifyObservers(APSR.class);
    }

    public boolean isOverflow() {
        return overflow;
    }

    public void setOverflow(boolean overflow) {
        this.overflow = overflow;
        dispatcher.notifyObservers(APSR.class);
    }

    public int updateNZ(int result) {
        negative = result < 0;
        zero = result == 0;
        dispatcher.notifyObservers(APSR.class);
        return result;
    }

    public void reset() {
        negative = false;
        zero = false;
        carry = false;
        overflow = false;
        dispatcher.notifyObservers(APSR.class);
    }
}
