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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RegisterFileTest {
    private final RegisterFile registerFile;
    private boolean success;

    public RegisterFileTest() {
        registerFile = new RegisterFile();
    }

    /**
     * Test of getValue method, of class RegisterFile.
     */
    @Test
    public void testGetValue() {
        assertEquals("Default register value is wrong.", 0, registerFile.getValue("r0"));
    }

    /**
     * Test of update method, of class RegisterFile.
     */
    @Test
    public void testUpdate() {
        registerFile.update("r0", 5);
        assertEquals("Register value is wrong.", 5, registerFile.getValue("r0"));
    }

    /**
     * Test of addObserver and notifyObservers methods, of class RegisterFile.
     */
    @Test
    public void testAddObserverAndNotifyObservers() {
        registerFile.addObserver((Class<?> clazz) -> {
            if(clazz.equals(RegisterFile.class)) {
                success = true;
            }
        });
        registerFile.notifyObservers();
        assertTrue("Observer did not get notified.", success);
    }
}
