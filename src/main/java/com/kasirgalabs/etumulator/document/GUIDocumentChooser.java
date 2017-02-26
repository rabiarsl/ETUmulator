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

import com.kasirgalabs.etumulator.patterns.Registry;
import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class GUIDocumentChooser implements DocumentChooser {
    private final FileChooser fileChooser;
    private Window window;

    public GUIDocumentChooser() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Tilki");
        window = null;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    @Override
    public void createDocument() {
        File file = fileChooser.showSaveDialog(window);
        if(file != null) {
            Document document = Registry.get(Document.class);
            document.setTargetFile(file);
            document.clear();
        }
    }

    @Override
    public void openDocument() throws IOException {
        File file = fileChooser.showOpenDialog(window);
        if(file != null) {
            Document document = Registry.get(Document.class);
            document.readFile(file);
            document.setTargetFile(file);
        }
    }

    @Override
    public File chooseDocument() {
        return fileChooser.showOpenDialog(window);
    }
}
