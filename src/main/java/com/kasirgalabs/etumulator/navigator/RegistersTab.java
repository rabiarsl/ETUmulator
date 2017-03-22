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

import com.google.inject.Inject;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import java.net.URL;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RegistersTab implements Initializable, Observer {
    @FXML
    private TableView<RegisterRow> table;
    @FXML
    private TableColumn<RegisterRow, String> name;
    @FXML
    private TableColumn<RegisterRow, String> value;
    @Inject
    private RegisterFile registerFile;
    @Inject
    private Navigator navigator;
    private final ObservableList<RegisterRow> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerFile.addObserver(this);
        navigator.addObserver(this);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        Map<String, Integer> registers = registerFile.getAll();
        registers.keySet().forEach((key) -> {
            RegisterRow registerRow = new RegisterRow(key, Integer.toString(registers.get(key)));
            data.add(registerRow);
        });
        table.setItems(data);
    }

    @Override
    public void update(Observable o, Object arg) {
        RegisterRow.setValueType(Navigator.getValueType());
        for(int i = 0; i < data.size(); i++) {
            RegisterRow registerRow = data.get(i);
            String newValue = Integer.toString(registerFile.getValue(registerRow.getName()));
            registerRow.setValue(newValue);
        }
        table.refresh();
    }

    public static class RegisterRow {
        private static int valueType;
        private final String name;
        private String value;

        private RegisterRow(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public static void setValueType(int type) {
            valueType = type;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }
}
