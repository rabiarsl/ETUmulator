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
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.etumulator.processor.UART;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BaseConsoleTest extends UART {
    @Rule
    public final JavaFXThread javaFXThread = new JavaFXThread();
    private Console console;
    private char mockInput;

    public BaseConsoleTest() {
        super(new RegisterFile());
    }

    @Before
    public void setUp() throws IOException {
        assert Platform.isFxApplicationThread();

        console = new BaseConsole(this);
        ClassLoader classLoader = BaseConsoleTest.class.getClassLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/Console.fxml"));
        fxmlLoader.setControllerFactory((Class<?> param) -> {
            return console;
        });
        fxmlLoader.load();
        console = fxmlLoader.getController();
    }

    /**
     * Test of write method, of class BaseConsole.
     */
    @Test
    public void testWrite() {
        console.write('a');
        String text = console.getText();
        assertEquals("BaseConsole does not work properly.", 'a', text.charAt(text.length() - 1));
    }

    /**
     * Test of read method, of class BaseConsole.
     */
    @Test
    public void testRead() {
        console.write('a');
        char output = console.read();
        assertEquals("BaseConsole does not work properly.", 'a', output);

        console.write('\n');
        output = console.read();
        assertEquals("BaseConsole does not work properly.", '\n', output);
    }

    /**
     * Test of update and replaceText methods, of class BaseConsole.
     */
    @Test
    public void testUpdateAndReplaceText() {
        BaseConsole baseConsole = (BaseConsole) console;
        baseConsole.update(UART.class, 'x');
        String text = baseConsole.getText();
        assertEquals("BaseConsole does not work properly.", 'x', text.charAt(text.length() - 1));

        mockInput = 'x';
        baseConsole.update(UART.class, "read");
        baseConsole.replaceText(baseConsole.getText().length(),
                baseConsole.getText().length(),
                "x");

        mockInput = '\0';
        baseConsole.replaceText(baseConsole.getText().length(),
                baseConsole.getText().length(),
                "t");
    }

    @Override
    public void feed(char data) {
        assertEquals("BaseConsole does not work properly.", mockInput, data);
    }
}
