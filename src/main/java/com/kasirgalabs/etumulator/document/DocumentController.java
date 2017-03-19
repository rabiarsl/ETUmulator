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

import com.google.inject.Singleton;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

@Singleton
public class DocumentController implements Initializable, Document {
    private static final String DEFAULT_NAME = "untitled";
    @FXML
    private Label label;
    @FXML
    private TextArea textArea;
    private File targetFile;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        targetFile = new File(DEFAULT_NAME);
        label.setText(DEFAULT_NAME);
    }

    @Override
    public String getText() {
        return textArea.getText();
    }

    @Override
    public void setTargetFile(File targetFile) {
        label.setText(targetFile.getName());
        this.targetFile = targetFile;
    }

    @Override
    public File getTargetFile() {
        return targetFile;
    }

    @Override
    public void saveDocument() throws IOException {
        String text = textArea.getText();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {
            bw.write(text);
        } catch(IOException ex) {
            throw ex;
        }
    }

    @Override
    public void clear() {
        textArea.clear();
    }

    @Override
    public void setText(String text) {
        textArea.setText(text);
    }
}
