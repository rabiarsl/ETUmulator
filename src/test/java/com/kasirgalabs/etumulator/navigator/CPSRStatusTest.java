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
import com.kasirgalabs.etumulator.processor.CPSR;
import com.kasirgalabs.etumulator.util.GUISafeDispatcher;
import java.io.IOException;
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

public class CPSRStatusTest {
    private CPSR cpsr;
    private CPSRStatus cpsrStatus;

    public CPSRStatusTest() throws IOException, InterruptedException, ExecutionException,
            TimeoutException {
        assert !Platform.isFxApplicationThread();
        new JFXPanel();

        FutureTask<Void> futureTask = new FutureTask<>(() -> {
            cpsr = new CPSR(new GUISafeDispatcher());
            cpsrStatus = new CPSRStatus(cpsr);
            ClassLoader classLoader = BaseDocumentTest.class.getClassLoader();
            FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/CPSRStatus.fxml"));
            fxmlLoader.setControllerFactory((Class<?> param) -> {
                return cpsrStatus;
            });
            fxmlLoader.load();
            return null;
        });
        Platform.runLater(futureTask);
        futureTask.get(5, TimeUnit.SECONDS);
    }

    /**
     * Test of update method, of class CPSRStatus.
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
            cpsr.setNegative(true);
            cpsr.setNegative(false);
            cpsr.setZero(true);
            cpsr.setZero(false);
            cpsr.setCarry(true);
            cpsr.setCarry(false);
            cpsr.setOverflow(true);
            cpsr.setOverflow(false);
            return null;
        });
        future.get(5, TimeUnit.SECONDS);
    }
}