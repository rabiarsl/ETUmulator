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

import com.kasirgalabs.etumulator.pattern.Observer;
import com.kasirgalabs.etumulator.pattern.Registry;
import com.kasirgalabs.etumulator.register.RegisterFile;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class NavigatorController implements Initializable, Observer {
    private static final ObservableList<NavigatorRow> DATA = FXCollections.observableArrayList();
    @FXML
    private TableView<NavigatorRow> table;
    @FXML
    private TableColumn<NavigatorRow, String> property;
    @FXML
    private TableColumn<NavigatorRow, String> value;
    @FXML
    private ComboBox<String> type;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.setItems(FXCollections.observableArrayList(
                new String[]{"Binary", "Decimal", "HEX", "ASCII"}));
        type.getSelectionModel().select(1);
        NavigatorRow.setType(type.getSelectionModel().getSelectedIndex());
        property.setCellValueFactory(new PropertyValueFactory<>("property"));
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        table.setItems(DATA);
        update();
        Registry.get(RegisterFile.class).addObserver(this);
    }

    @Override
    public void update() {
        DATA.clear();
        RegisterFile registerFile = Registry.get(RegisterFile.class);
        for(int i = 0; i < RegisterFile.NUM_OF_REGS; i++) {
            String registerNumber = "r" + Integer.toString(i);
            String registerValue = Long.toString(registerFile.getValue(i));
            DATA.add(new NavigatorRow(registerNumber, registerValue));
        }
    }

    @FXML
    private void typeOnAction(ActionEvent event) {
        NavigatorRow.setType(type.getSelectionModel().getSelectedIndex());
        update();
    }
}
