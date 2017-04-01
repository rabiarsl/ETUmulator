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

import java.util.Observable;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

public class GUISafeObservable extends Observable {
    private static final Logger LOGGER = Logger.getLogger(GUISafeObservable.class.getName());

    @Override
    public void notifyObservers(Object object) {
        final CountDownLatch latch = new CountDownLatch(1);
        setChanged();
        try {
            Platform.runLater(() -> {
                super.notifyObservers(object);
                latch.countDown();
            });
            try {
                if(!Platform.isFxApplicationThread()) {
                    latch.await();
                }
            }
            catch(InterruptedException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                System.exit(-1);
            }
            return;
        }
        catch(IllegalStateException ex) {
            super.notifyObservers(object);
        }
    }

    @Override
    public void notifyObservers() {
        notifyObservers(null);
    }
}
