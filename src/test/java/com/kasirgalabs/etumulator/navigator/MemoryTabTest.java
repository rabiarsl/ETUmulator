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

import com.kasirgalabs.etumulator.document.BaseDocumentTest;
import com.kasirgalabs.etumulator.processor.Memory;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import org.junit.Test;

public class MemoryTabTest {
    private Memory memory;
    private Navigator navigator;
    private MemoryTab memoryTab;

    public MemoryTabTest() throws InterruptedException, ExecutionException, TimeoutException {
        assert !Platform.isFxApplicationThread();
        new JFXPanel();

        FutureTask<Void> futureTask = new FutureTask<>(() -> {
            memory = new Memory();
            navigator = new Navigator();
            memoryTab = new MemoryTab(memory, navigator);
            ClassLoader classLoader = BaseDocumentTest.class.getClassLoader();
            FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/MemoryTab.fxml"));
            fxmlLoader.setControllerFactory((Class<?> param) -> {
                return memoryTab;
            });
            fxmlLoader.load();
            return null;
        });
        Platform.runLater(futureTask);
        futureTask.get(5, TimeUnit.SECONDS);
    }

    /**
     * Test of update method, of class MemoryTab.
     *
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     * @throws java.util.concurrent.TimeoutException
     */
    @Test
    public void testUpdate() throws InterruptedException, ExecutionException, TimeoutException {
        assert !Platform.isFxApplicationThread();
        new JFXPanel();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Void> future = executor.submit(() -> {
            Random random = new Random();
            final int ADDRESS = random.nextInt(Integer.MAX_VALUE);
            memory.set(ADDRESS, (byte) 0);
            memory.set(ADDRESS, (byte) 1);
            memory.set(ADDRESS, (byte) 2);
            memory.set(ADDRESS, (byte) 3);
            memory.set(ADDRESS + 1, (byte) 1);
            memory.set(ADDRESS + 2, (byte) 1);
            memory.set(ADDRESS + 3, (byte) 1);
            memory.set(ADDRESS + 4, (byte) 1);
            memory.set(ADDRESS, (byte) 4);
            return null;
        });
        future.get(5, TimeUnit.SECONDS);
    }
}
