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
package com.kasirgalabs.etumulator;

import com.kasirgalabs.etumulator.document.DocumentController;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.fxml.FXMLLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class DocumentControllerTest {
    private static DocumentController documentController;
    private static File mockFile;

    @Rule
    public JavaFXThread javaFXThread = new JavaFXThread();

    @Before
    public void setUp() throws IOException {
        ClassLoader classLoader = DocumentControllerTest.class.getClassLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/Document.fxml"));
        fxmlLoader.load();
        documentController = fxmlLoader.getController();
        mockFile = new File("DocumentControllerTest.txt");
    }

    @After
    public void tearDown() {
        mockFile.delete();
    }

    /**
     * Test of initialize method, of class DocumentController.
     *
     * @throws IOException Throws if an error occurs while loading "Document.fxml"
     */
    @Test
    public void testInitialize() throws IOException {
        ClassLoader classLoader = DocumentControllerTest.class.getClassLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/Document.fxml"));
        fxmlLoader.load();
    }

    /**
     * Test of readFile and getText methods, of class DocumentController.
     *
     * @throws IOException If reading or writing to mock file produces an error.
     */
    @Test
    public void testReadFileAndGetText() throws IOException {
        String expResult = "testReadFileAndGetText";
        try(PrintWriter writer = new PrintWriter(mockFile)) {
            writer.println(expResult);
        }
        documentController.readFile(mockFile);
        String result = documentController.getText();
        // We expect to get newline appended to expected result.
        assertEquals("Text is not same.", expResult + "\n", result);
    }

    /**
     * Test of setTargetFile and getTargetFile methods, of class DocumentController.
     */
    @Test
    public void testSetTargetFileandGetTargetFile() {
        String defaultFileName = "untitled";
        File result;
        result = documentController.getTargetFile();
        // Test getTargetFile without setting any target file.
        assertEquals("Default file name is not same.", defaultFileName, result.getName());
        File expResult = new File("testSetTargetFileandGetTargetFile");
        documentController.setTargetFile(expResult);
        result = documentController.getTargetFile();
        assertEquals("File is not same.", expResult, result);
    }

    /**
     * Test of saveDocument method, of class DocumentController.
     *
     * @throws IOException If reading or writing to mock file produces an error.
     */
    @Test
    public void testSaveDocument() throws IOException {
        String expResult = "testReadFileAndGetText";
        try(PrintWriter writer = new PrintWriter(mockFile)) {
            writer.println(expResult);
        }
        documentController.readFile(mockFile);
        File targetFile = new File("DocumentControllerTestTarget.txt");
        documentController.setTargetFile(targetFile);
        documentController.saveDocument();
        String result;
        try(BufferedReader bf = new BufferedReader(new FileReader(targetFile))) {
            result = bf.readLine();
        } catch(IOException ex) {
            throw ex;
        }
        targetFile.delete();
        assertEquals("Saved text is not same.", expResult, result);
    }

    /**
     * Test of getTextProperty method, of class DocumentController.
     */
    @Test
    public void testGetTextProperty() throws IOException {
        String result = documentController.getText();
        assertEquals("Text is not empty.", "", result);
    }

    /**
     * Test of clear method, of class DocumentController.
     *
     * @throws IOException If reading or writing to mock file produces an error.
     */
    @Test
    public void testClear() throws IOException {
        String text = "testReadFileAndGetText";
        try(PrintWriter writer = new PrintWriter(mockFile)) {
            writer.println(text);
        }
        documentController.readFile(mockFile);
        documentController.clear();
        String result = documentController.getText();
        assertEquals("Text is not empty.", "", result);
    }
}
