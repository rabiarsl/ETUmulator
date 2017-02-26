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

import com.kasirgalabs.etumulator.patterns.Observer;
import com.kasirgalabs.etumulator.patterns.Registry;
import com.kasirgalabs.etumulator.registers.RegisterFile;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class NavigatorController implements Initializable, Observer {
    @FXML
    private TableColumn<NavigatorRow, String> leftColumn;
    @FXML
    private TableColumn<NavigatorRow, String> rightColumn;
    @FXML
    private TableView<NavigatorRow> tableView;
    private final ObservableList<NavigatorRow> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        leftColumn.setCellValueFactory(new PropertyValueFactory<>("property"));
        rightColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        tableView.setItems(data);
        update();
        Registry.get(RegisterFile.class).addObserver(this);
    }

    @Override
    public void update() {
        data.clear();
        RegisterFile registerFile = Registry.get(RegisterFile.class);
        for(int i = 0; i < RegisterFile.NUM_OF_REGS; i++) {
            String registerNumber = "r" + Integer.toString(i);
            String registerValue = Integer.toString(registerFile.getValue(i));
            data.add(new NavigatorRow(registerNumber, registerValue));
        }
    }
}
