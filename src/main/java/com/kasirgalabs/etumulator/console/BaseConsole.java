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

import com.google.inject.Inject;
import com.kasirgalabs.etumulator.processor.UART;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class BaseConsole extends TextArea implements Initializable, Console, Observer {
    private static final Logger LOGGER = Logger.getLogger(BaseConsole.class.getName());
    private Semaphore semaphore;
    private volatile boolean readEnable;
    private volatile char readChar;
    @FXML
    private VBox vbox;
    private final UART uart;

    @Inject
    public BaseConsole(UART uart) {
        this.uart = uart;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uart.addObserver(this);
        vbox.getChildren().add(this);
        semaphore = new Semaphore(0);
        this.wrapTextProperty().set(true);
        addEventFilter(KeyEvent.ANY, (keyEvent) -> {
            if(keyEvent.getEventType().equals(KeyEvent.KEY_TYPED) && readEnable) {
                readChar = keyEvent.getCharacter().charAt(0);
                readEnable = false;
                semaphore.release();
                return;
            }
            keyEvent.consume();
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg == null) {
            uart.feed(read());
            return;
        }
        write((char) arg);
    }

    @Override
    public void write(char data) {
        appendText(Character.toString(data));
    }

    @Override
    public char read() {
        readEnable = true;
        try {
            semaphore.acquire();
        }
        catch(InterruptedException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return readChar;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        String current = getText();
        if(!current.substring(start).contains("\n")) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        String current = getText();
        int selectionStart = getSelection().getStart();
        if(!current.substring(selectionStart).contains("\n")) {
            super.replaceSelection(text);
        }
    }
}
