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

import static javafx.application.Application.launch;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.kasirgalabs.etumulator.document.DocumentMenuController;
import com.kasirgalabs.etumulator.pattern.Registry;
import com.kasirgalabs.etumulator.processor.CPSR;
import com.kasirgalabs.etumulator.processor.CPUStack;
import com.kasirgalabs.etumulator.processor.InstructionUnit;
import com.kasirgalabs.etumulator.processor.MemoryUnit;
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ETUmulator extends Application {
    @Inject
    private DocumentMenuController documentMenuController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Module module = new ETUmulatorModule();
        Injector injector = Guice.createInjector(module);

        Registry.put(RegisterFile.class, new RegisterFile());
        Registry.put(CPUStack.class, new CPUStack());
        Registry.put(CPSR.class, new CPSR());
        Registry.put(InstructionUnit.class, new InstructionUnit());
        Registry.put(MemoryUnit.class, new MemoryUnit());
        Registry.put(Processor.class, new Processor(
                Registry.get(RegisterFile.class),
                Registry.get(CPUStack.class),
                Registry.get(CPSR.class),
                Registry.get(InstructionUnit.class),
                Registry.get(MemoryUnit.class))
        );

        primaryStage.setTitle("ETUmulator");
        ClassLoader classLoader = ETUmulator.class.getClassLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/ETUmulator.fxml"));
        fxmlLoader.setControllerFactory(injector::getInstance);
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        injector.injectMembers(this);
        documentMenuController.setWindow(primaryStage.getOwner());
    }
}
