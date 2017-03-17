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
package com.kasirgalabs.etumulator.processor.instruction;

import com.kasirgalabs.etumulator.processor.CPUStack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CPUStackTest {
    private final CPUStack stack;
    private boolean success;

    public CPUStackTest() {
        stack = new CPUStack();
    }

    /**
     * Test of push and pop methods, of class CPUStack.
     */
    @Test
    public void testPushAndPop() {
        stack.push(1234);
        assertEquals("Push and pop result is wrong.", 1234, stack.pop().intValue());
    }

    /**
     * Test of addObserver and notifyObservers methods, of class CPUStack.
     */
    @Test
    public void testAddObserverAndNotifyObservers() {
        stack.addObserver((Class<?> clazz) -> {
            if(clazz.equals(CPUStack.class)) {
                success = true;
            }
        });
        stack.notifyObservers();
        assertTrue("Observer did not get notified.", success);
    }
}
