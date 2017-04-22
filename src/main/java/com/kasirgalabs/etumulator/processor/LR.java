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

/**
 * A link register is a special-purpose register which holds the address to return to when a
 * function call completes. When a BL instruction performs a subroutine call, LR is set to the
 * subroutine return address. To perform a subroutine return, copy LR back to the program counter.
 * This is done in the following way, after entering the subroutine with a BL instruction:<br>
 * On subroutine entry, store LR to the stack with an instruction of the form: PUSH {LR} and use
 * a matching instruction to return: POP {PC}
 * <p>
 * This class represents an observable object. After a state change on LR value, all of it's
 * observers will be notified through default {@link BaseDispatcher} or the Dispatcher initialized
 * during construction.
 *
 * @author rootg
 * @see PC
 */
@Singleton
public class LR implements Observable {
    private int lr;
    private final Dispatcher dispatcher;

    /**
     * Construct an LR with {@link BaseDispatcher}.
     *
     * @see BaseDispatcher
     */
    public LR() {
        dispatcher = new BaseDispatcher();
    }

    /**
     * Construct an LR with the given dispatcher as parameter.
     *
     * @param dispatcher The dispatcher which will be used to notify observers.
     *
     * @see Dispatcher
     */
    @Inject
    public LR(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * Adds an observer to the list of observers for this object.
     *
     * @param observer The observer to be deleted.
     *
     * @see Observer
     */
    @Override
    public void addObserver(Observer observer) {
        dispatcher.addObserver(observer);
    }

    /**
     * @param value The LR value to set.
     */
    public void setValue(int value) {
        lr = value;
        dispatcher.notifyObservers(LR.class);
    }

    /**
     * @return The value of LR.
     */
    public int getValue() {
        return lr;
    }

    /**
     * Sets LR value to zero.
     */
    public void reset() {
        lr = 0;
        dispatcher.notifyObservers(LR.class);
    }
}
