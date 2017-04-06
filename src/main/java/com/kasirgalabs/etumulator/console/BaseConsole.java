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
import com.kasirgalabs.etumulator.util.Observer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class BaseConsole extends TextArea implements Initializable, Console, Observer {
    private final String userName;
    private boolean readEnable;
    private char readChar;
    @FXML
    private VBox vbox;
    private final UART uart;

    @Inject
    public BaseConsole(UART uart) {
        userName = System.getProperty("user.name");
        this.uart = uart;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readEnable = false;
        setText(userName + "@ETUmulator: ");
        uart.addObserver(this);
        vbox.getChildren().add(this);
        wrapTextProperty().set(true);
    }

    @Override
    public void update(Class<?> clazz, Object arg) {
        if(arg.equals("read")) {
            readEnable = true;
            return;
        }
        write((char) arg);
    }

    @Override
    public void write(char data) {
        readChar = data;
        if(data == '\n') {
            super.replaceText(getText().length(), getText().length(),
                    "\n" + userName + "@ETUmulator: ");
        }
        else {
            super.replaceText(getText().length(), getText().length(), Character.toString(data));
        }
    }

    @Override
    public char read() {
        readEnable = true;
        return readChar;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if(!readEnable || text.isEmpty()) {
            return;
        }
        uart.feed(text.charAt(0));
        readEnable = false;
    }
}