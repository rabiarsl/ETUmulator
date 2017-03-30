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
import java.util.Observable;
import java.util.Observer;
import org.junit.Test;

public class UARTTest implements Observer {
    private final RegisterFile registerFile;
    private final UART uart;
    private char mockInput;
    private char output;

    public UARTTest() {
        registerFile = new RegisterFile();
        uart = new UART(registerFile);
        uart.addObserver(this);
    }

    /**
     * Test of read method, of class UART.
     */
    @Test
    public void testRead() {
        mockInput = '5';
        uart.read();
        assertEquals("UART read result is wrong.", mockInput, registerFile.getValue("r0"));

        mockInput = 'A';
        uart.read();
        assertEquals("UART read result is wrong.", mockInput, registerFile.getValue("r0"));

        mockInput = '\n';
        uart.read();
        assertEquals("UART read result is wrong.", mockInput, registerFile.getValue("r0"));

        mockInput = '\0';
        uart.read();
        assertEquals("UART read result is wrong.", mockInput, registerFile.getValue("r0"));

        mockInput = '?';
        uart.read();
        assertEquals("UART read result is wrong.", mockInput, registerFile.getValue("r0"));

        mockInput = 'Z';
        uart.read();
        assertEquals("UART read result is wrong.", mockInput, registerFile.getValue("r0"));

        mockInput = Character.MAX_VALUE;
        uart.read();
        assertEquals("UART read result is wrong.", mockInput, registerFile.getValue("r0"));

        mockInput = Character.MIN_SURROGATE;
        uart.read();
        assertEquals("UART read result is wrong.", mockInput, registerFile.getValue("r0"));
    }

    /**
     * Test of write method, of class UART.
     */
    @Test
    public void testWrite() {
        mockInput = '5';
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);

        mockInput = 'A';
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);

        mockInput = '\n';
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);

        mockInput = '\0';
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);

        mockInput = '?';
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);

        mockInput = 'Z';
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);

        mockInput = Character.MAX_VALUE;
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);

        mockInput = Character.MIN_SURROGATE;
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg == null) {
            uart.feed(mockInput);
            return;
        }
        output = (char) arg;
    }
}
