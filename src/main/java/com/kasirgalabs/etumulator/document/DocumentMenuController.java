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
package com.kasirgalabs.etumulator.document;

import com.google.inject.Inject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class DocumentMenuController implements Initializable {
    @Inject
    private Document document;
    private final FileChooser fileChooser;
    private Window window;

    public DocumentMenuController() {
        this.fileChooser = new FileChooser();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    @FXML
    private void newOnAction(ActionEvent event) {
        File file = fileChooser.showSaveDialog(window);
        if(file != null) {
            document.setTargetFile(file);
            document.clear();
        }
    }

    @FXML
    private void openOnAction(ActionEvent event) throws IOException {
        File file = fileChooser.showOpenDialog(window);
        if(file != null) {
            StringBuilder text = new StringBuilder();
            try(BufferedReader bf = new BufferedReader(new FileReader(file))) {
                String line;
                while((line = bf.readLine()) != null) {
                    text.append(line + "\n");
                }
            } catch(IOException ex) {
                throw ex;
            }
            document.setText(text.toString());
            document.setTargetFile(file);
        }
    }

    @FXML
    private void saveOnAction(ActionEvent event) throws IOException {
        document.saveDocument();
    }

    @FXML
    private void saveAsOnaction(ActionEvent event) throws IOException {
        File file = fileChooser.showSaveDialog(window);
        if(file == null) {
            return;
        }
        document.setTargetFile(file);
        document.saveDocument();
    }
}
