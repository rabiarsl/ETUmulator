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
import com.kasirgalabs.etumulator.JavaFXThread;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javax.swing.SwingUtilities;
import org.junit.Rule;
import org.junit.Test;

public class GUISafeObservableTest {
    private static String expResult;
    @Rule
    public final JavaFXThread javaFXThread = new JavaFXThread();
    private final GUISafeObservable observable;

    public GUISafeObservableTest() {
        observable = new GUISafeObservable();
        observable.addObserver(new TestObserver());
    }

    private static String getExpectedResult() {
        return expResult;
    }

    /**
     * Test of notifyObservers method, of class GUISafeObservable.
     */
    @Test
    public void testNotifyObservers_Object() {
        expResult = "test0";
        observable.notifyObservers(expResult);

        SwingUtilities.invokeLater(() -> {
            expResult = "test1";
            observable.notifyObservers(expResult);
        });

        Platform.runLater(() -> {
            expResult = "test2";
            observable.notifyObservers(expResult);
        });
    }

    /**
     * Test of notifyObservers method, of class GUISafeObservable.
     */
    @Test
    public void testNotifyObservers() {
        observable.notifyObservers();
    }

    private static class TestObserver implements Observer {
        @Override
        public void update(Observable o, Object arg) {
            if(arg != null) {
                assertEquals("Observable result is wrong.", getExpectedResult(), arg);
            }
        }
    }
}
