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
package com.kasirgalabs.etumulator.navigator;

import com.kasirgalabs.etumulator.JavaFXThread;
import com.kasirgalabs.etumulator.document.BaseDocumentTest;
import com.kasirgalabs.etumulator.processor.Memory;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MemoryTabTest {
    private static Memory memory;
    private static Navigator navigator;
    private static MemoryTab memoryTab;
    @Rule
    public final JavaFXThread javaFXThread = new JavaFXThread();

    @Before
    public void setUp() throws IOException {
        memory = new Memory();
        navigator = new Navigator();
        memoryTab = new MemoryTab(memory, navigator);
        ClassLoader classLoader = BaseDocumentTest.class.getClassLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/MemoryTab.fxml"));
        fxmlLoader.setControllerFactory((Class<?> param) -> {
            return memoryTab;
        });
        fxmlLoader.load();
    }

    /**
     * Test of update method, of class MemoryTab.
     */
    @Test
    public void testUpdate() {
        final int RANDOM = (int) (Math.random() * Integer.MAX_VALUE);
        memory.set(RANDOM, (byte) 0);
        memory.set(RANDOM, (byte) 1);
        memory.notifyObservers(RANDOM + 1);
        navigator.notifyObservers();
    }
}
