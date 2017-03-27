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

import static org.junit.Assert.assertEquals;
import com.kasirgalabs.etumulator.JavaFXThread;
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

public class BaseDocumentTest {
    @Rule
    public JavaFXThread javaFXThread = new JavaFXThread();
    private Document document;
    private File mockFile;

    @Before
    public void setUp() throws IOException {
        ClassLoader classLoader = BaseDocumentTest.class.getClassLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/Document.fxml"));
        fxmlLoader.load();
        document = fxmlLoader.getController();
        mockFile = new File("DocumentControllerTest.txt");
    }

    @After
    public void tearDown() {
        mockFile.delete();
    }

    /**
     * Test of setTargetFile and getTargetFile methods, of class BaseDocument.
     */
    @Test
    public void testSetTargetFileandGetTargetFile() {
        File expResult = new File("testSetTargetFileandGetTargetFile");
        document.setTargetFile(expResult);
        assertEquals("File is not same.", expResult, document.getTargetFile());
    }

    /**
     * Test of saveDocument method, of class BaseDocument.
     *
     * @throws IOException If reading or writing to mock file produces an error.
     */
    @Test
    public void testSaveDocument() throws IOException {
        String expResult = "testReadFileAndGetText";
        try(PrintWriter writer = new PrintWriter(mockFile)) {
            writer.println(expResult);
        }
        readFileIntoDocument(mockFile);
        File targetFile = new File("DocumentControllerTestTarget.txt");
        document.setTargetFile(targetFile);
        document.saveDocument();
        String result;
        try(BufferedReader bf = new BufferedReader(new FileReader(targetFile))) {
            result = bf.readLine();
        }
        catch(IOException ex) {
            throw ex;
        }
        targetFile.delete();
        assertEquals("Saved text is not same.", expResult, result);
    }

    /**
     * Test of getText and setText methods, of class BaseDocument.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testGetTextAndSetText() throws IOException {
        document.setText("TEST");
        String result = document.getText();
        assertEquals("Text is not empty.", "TEST", result);
    }

    /**
     * Test of clear method, of class DocumentController.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testClear() throws IOException {
        String text = "TEST";
        document.setText(text);
        document.clear();
        String result = document.getText();
        assertEquals("Text is not empty.", "", result);
    }

    private void readFileIntoDocument(File file) throws IOException {
        StringBuilder text = new StringBuilder();
        try(BufferedReader bf = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = bf.readLine()) != null) {
                text.append(line).append("\n");
            }
        }
        catch(IOException ex) {
            throw ex;
        }
        document.setText(text.toString());
    }
}
