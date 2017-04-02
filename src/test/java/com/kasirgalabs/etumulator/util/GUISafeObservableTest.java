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

import static org.junit.Assert.assertEquals;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.Test;

public class GUISafeObservableTest implements Observer {
    private CountDownLatch latch;
    private String expResult;
    private final GUISafeObservable observable;

    public GUISafeObservableTest() {
        observable = new GUISafeObservable();
    }

    /**
     * Test of notifyObservers method, of class GUISafeObservable.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testNotifyObservers_Object() throws InterruptedException {
        assert !Platform.isFxApplicationThread();
        new JFXPanel();
        if(observable.countObservers() == 0) {
            observable.addObserver(this);
        }

        expResult = "test0";
        latch = new CountDownLatch(1);
        observable.notifyObservers(expResult);
        latch.await(5, TimeUnit.SECONDS);

        latch = new CountDownLatch(1);
        expResult = "test1";
        observable.notifyObservers(expResult);
        latch.await(5, TimeUnit.SECONDS);

        latch = new CountDownLatch(1);
        expResult = "test2";
        observable.notifyObservers(expResult);
        latch.await(5, TimeUnit.SECONDS);

    }

    /**
     * Test of notifyObservers method, of class GUISafeObservable.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testNotifyObservers() throws InterruptedException {
        assert !Platform.isFxApplicationThread();
        new JFXPanel();
        if(observable.countObservers() == 0) {
            observable.addObserver(this);
        }

        latch = new CountDownLatch(1);
        observable.notifyObservers();
        latch.await(5, TimeUnit.SECONDS);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg != null) {
            assertEquals("Observable result is wrong.", expResult, arg);
        }
        latch.countDown();
    }
}
