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
import java.util.concurrent.CountDownLatch;

@Singleton
public class UART implements Observable {
    private RegisterFile registerFile;
    private char input;
    private final Dispatcher dispatcher;
    private CountDownLatch latch;

    public UART(RegisterFile registerFile) {
        this.registerFile = registerFile;
        this.dispatcher = new BaseDispatcher();
    }

    @Inject
    public UART(RegisterFile registerFile, Dispatcher dispatcher) {
        this.registerFile = registerFile;
        this.dispatcher = dispatcher;
    }

    public void setRegisterFile(RegisterFile registerFile) {
        this.registerFile = registerFile;
    }

    @Override
    public void addObserver(Observer observer) {
        dispatcher.addObserver(observer);
    }

    public void read() throws InterruptedException {
        latch = new CountDownLatch(1);
        dispatcher.notifyObservers(UART.class, "read");
        latch.await();
        registerFile.setValue("r0", input);
    }

    public void write() {
        dispatcher.notifyObservers(UART.class, (char) registerFile.getValue("r0"));
    }

    public void feed(char input) {
        this.input = input;
        latch.countDown();
    }
}
