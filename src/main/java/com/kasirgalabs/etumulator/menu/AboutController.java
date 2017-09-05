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
package com.kasirgalabs.etumulator.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class AboutController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(AboutController.class.getName());

    @FXML
    private Text applicationVersion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applicationVersion.setText(getClass().getPackage().getImplementationVersion());
    }

    @FXML
    private void etumulatorOnAction(ActionEvent event) throws IOException {
        new ProcessBuilder("x-www-browser", "https://kasirgalabs.github.io/ETUmulator/").start();
    }

    @FXML
    private void kasirgalabsOnAction(ActionEvent event) throws IOException {
        new ProcessBuilder("x-www-browser", "http://www.kasirgalabs.com/").start();
    }

    @FXML
    private void bugReportOnAction(ActionEvent event) throws IOException {
        new ProcessBuilder("x-www-browser", "https://github.com/kasirgalabs/ETUmulator/issues/new")
                .start();
    }

    @FXML
    private void getInvolvedOnAction(ActionEvent event) throws IOException {
        new ProcessBuilder("x-www-browser", "https://github.com/kasirgalabs/ETUmulator/wiki")
                .start();
    }

    @FXML
    private void githubOnAction(ActionEvent event) throws IOException {
        new ProcessBuilder("x-www-browser", "https://github.com/kasirgalabs/ETUmulator").start();
    }

    @FXML
    private void licenseOnAction(ActionEvent event) throws IOException {
        new ProcessBuilder("x-www-browser", "https://www.gnu.org/licenses/gpl-3.0.html").start();
    }
}
