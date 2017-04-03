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
package com.kasirgalabs.etumulator.console;

import static org.junit.Assert.assertEquals;

import com.kasirgalabs.etumulator.JavaFXThread;
import com.kasirgalabs.etumulator.document.BaseDocumentTest;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.etumulator.processor.UART;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BaseConsoleTest {
    private static BaseConsole baseConsole;
    private static String mockInput;
    @Rule
    public final JavaFXThread javaFXThread = new JavaFXThread();
    private RegisterFile registerFile;
    private UART uart;

    @Before
    public void setUp() throws IOException {
        assert Platform.isFxApplicationThread();

        registerFile = new RegisterFile();
        uart = new UART(registerFile);
        BaseConsoleTest.baseConsole = new BaseConsole(uart, new MockSemaphore(0));
        ClassLoader classLoader = BaseDocumentTest.class.getClassLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/Console.fxml"));
        fxmlLoader.setControllerFactory((Class<?> param) -> {
            return baseConsole;
        });
        fxmlLoader.load();
        baseConsole = fxmlLoader.getController();
    }

    /**
     * Test of update method, of class BaseConsole.
     */
    @Test
    public void testUpdate() throws InterruptedException {
        BaseConsoleTest.mockInput = "a";
        baseConsole.update(UART.class, null);
        uart.read();
        assertEquals("BaseConsole does not work properly.", mockInput.charAt(0), registerFile
                .getValue("r0"));

        baseConsole.clear();
        BaseConsoleTest.mockInput = "b";
        baseConsole.update(UART.class, mockInput.charAt(0));
        assertEquals("BaseConsole does not work properly.", mockInput, baseConsole.getText());

        BaseConsoleTest.mockInput = "c";
        baseConsole.clear();
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_TYPED, mockInput, null, KeyCode.ESCAPE,
                false, false, false, false);
        baseConsole.fireEvent(keyEvent);
        assertEquals("BaseConsole does not work properly.", "", baseConsole.getText());
    }

    /**
     * Test of write method, of class BaseConsole.
     */
    @Test
    public void testWrite() {
        baseConsole.clear();
        baseConsole.write('x');
        assertEquals("BaseConsole does not work properly.", "x", baseConsole.getText());
    }

    /**
     * Test of read method, of class BaseConsole.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testRead() throws InterruptedException {
        BaseConsoleTest.mockInput = "t";

        baseConsole.clear();
        baseConsole.read();
        assertEquals("BaseConsole does not work properly.", mockInput.charAt(0),
                baseConsole.read());
    }

    /**
     * Test of replaceSelection method, of class BaseConsole.
     */
    @Test
    public void testReplaceSelection() {
        baseConsole.clear();
        baseConsole.replaceSelection("TEST");
        assertEquals("BaseConsole does not work properly.", "TEST", baseConsole.getText());
    }

    private static class MockSemaphore extends Semaphore {
        private MockSemaphore(int permits) {
            super(permits);
        }

        @Override
        public void acquire() {
            KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_TYPED, mockInput, null, KeyCode.ESCAPE,
                    false, false, false, false);
            baseConsole.fireEvent(keyEvent);
        }

        @Override
        public void release() {

        }
    }
}
