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
    private RegisterFile registerFile;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.setItems(FXCollections.observableArrayList(
                new String[]{"Binary", "Decimal", "HEX", "ASCII"}));
        property.setCellValueFactory(new PropertyValueFactory<>("property"));
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        for(int i = 0; i < RegisterFile.NUM_OF_REGS; i++) {
            String registerNumber = "r" + Integer.toString(i);
            DATA.add(i, new NavigatorRow(registerNumber, "?"));
        }
        property.setComparator(new NavigatorRowComparator());
        table.setItems(DATA);
        registerFile = Registry.get(RegisterFile.class);
        registerFile.addObserver(this);
    }

    @Override
    public void update() {
        for(int i = 0; i < DATA.size(); i++) {
            NavigatorRow navigatorRow = DATA.remove(i);
            String registerName = navigatorRow.getProperty().substring(1);
            String registerValue = Integer.toString(registerFile.getValue(registerName));
            DATA.add(i, new NavigatorRow(registerName, registerValue));
        }
    }

    @FXML
    private void typeOnAction(ActionEvent event) {
        NavigatorRow.setType(type.getSelectionModel().getSelectedIndex());
        update();
    }
}
