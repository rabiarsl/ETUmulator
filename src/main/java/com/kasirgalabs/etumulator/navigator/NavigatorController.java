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
import com.kasirgalabs.etumulator.pattern.Observer;
import com.kasirgalabs.etumulator.processor.Memory;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.etumulator.processor.Stack;
import java.net.URL;
import java.util.List;
import java.util.Map;
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
    @Inject
    private RegisterFile registerFile;
    @Inject
    private Stack stack;
    @Inject
    private Memory memory;
    private int previousContent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.setItems(FXCollections.observableArrayList(
                new String[]{"Binary", "Decimal", "HEX", "ASCII"}));
        type.getSelectionModel().select(1);
        previousContent = NavigatorRowContent.REGISTERS;
        NavigatorRow.setRowContent(NavigatorRowContent.REGISTERS);
        NavigatorRow.setRowType(NavigatorRowType.DECIMAL);

        registerFile.addObserver(this);
        for(int i = 0; i < 13; i++) {
            String registerName = "r" + Integer.toString(i);
            String registerValue = Integer.toString(registerFile.getValue(registerName));
            DATA.add(i, new NavigatorRow(registerName, registerValue));
        }

        property.setCellValueFactory(new PropertyValueFactory<>("property"));
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        property.setComparator(new NavigatorRowComparator());
        table.setItems(DATA);

        stack.addObserver(this);

        memory.addObserver(this);
    }

    @Override
    public void update(Class<?> clazz) {
        if(clazz.equals(RegisterFile.class)
                && NavigatorRow.getRowContent() == NavigatorRowContent.REGISTERS) {
            updateRegisterContent();
        }
        else if(clazz.equals(Stack.class)
                && NavigatorRow.getRowContent() == NavigatorRowContent.STACK) {
            updateStackContent();
        }
        else if(clazz.equals(Memory.class)
                && NavigatorRow.getRowContent() == NavigatorRowContent.RAM) {
            updateRamContent();
        }
    }

    @FXML
    private void registersButtonOnAction(ActionEvent event) {
        previousContent = NavigatorRow.getRowContent();
        NavigatorRow.setRowContent(NavigatorRowContent.REGISTERS);
        updateRegisterContent();
    }

    @FXML
    private void stackButtonOnAction(ActionEvent event) {
        previousContent = NavigatorRow.getRowContent();
        NavigatorRow.setRowContent(NavigatorRowContent.STACK);
        updateStackContent();
    }

    @FXML
    private void ramButtonOnAction(ActionEvent event) {
        previousContent = NavigatorRow.getRowContent();
        NavigatorRow.setRowContent(NavigatorRowContent.RAM);
        updateRamContent();
    }

    @FXML
    private void typeOnAction(ActionEvent event) {
        previousContent = NavigatorRow.getRowContent();
        int rowType = type.getSelectionModel().getSelectedIndex();
        NavigatorRow.setRowType(rowType);
        switch(NavigatorRow.getRowContent()) {
            case NavigatorRowContent.REGISTERS:
                updateRegisterContent();
                break;
            case NavigatorRowContent.STACK:
                updateStackContent();
                break;
            case NavigatorRowContent.RAM:
                updateRamContent();
                break;
            default:
                break;
        }
    }

    private void updateRegisterContent() {
        property.setText("Name");
        if(previousContent == NavigatorRow.getRowContent()) {
            for(int i = 0; i < DATA.size(); i++) {
                NavigatorRow navigatorRow = DATA.get(i);
                DATA.remove(navigatorRow);
                String registerName = navigatorRow.getProperty();
                String registerValue = Integer.toString(registerFile.getValue(registerName));
                DATA.add(i, new NavigatorRow(registerName, registerValue));
            }
            return;
        }
        DATA.clear();
        for(int i = 0; i < 13; i++) {
            String registerName = "r" + Integer.toString(i);
            String registerValue = Integer.toString(registerFile.getValue(registerName));
            DATA.add(i, new NavigatorRow(registerName, registerValue));
        }
    }

    private void updateStackContent() {
        property.setText("Number");
        if(previousContent == NavigatorRow.getRowContent()) {
            List<Integer> temp = stack.getAll();
            for(int i = 0; i < DATA.size(); i++) {
                NavigatorRow navigatorRow = DATA.get(i);
                DATA.remove(navigatorRow);
                int index = Integer.parseInt(navigatorRow.getProperty());
                String number = Integer.toString(temp.get(index));
                DATA.add(i, new NavigatorRow(navigatorRow.getProperty(), number));
            }
            for(int i = DATA.size(); i < temp.size(); i++) {
                String number = Integer.toString(temp.get(i));
                DATA.add(i, new NavigatorRow(Integer.toString(i), number));
            }
            return;
        }
        DATA.clear();
        List<Integer> temp = stack.getAll();
        for(int i = 0; i < temp.size(); i++) {
            String index = Integer.toString(i);
            String number = Integer.toString(temp.get(i));
            DATA.add(i, new NavigatorRow(index, number));
        }
    }

    private void updateRamContent() {
        property.setText("Address");
        Map<Integer, Byte> temp = memory.getAll();
        if(previousContent == NavigatorRow.getRowContent()) {
            for(int i = 0; i < DATA.size(); i++) {
                NavigatorRow navigatorRow = DATA.get(i);
                DATA.remove(navigatorRow);
                int address = Integer.parseInt(navigatorRow.getProperty());
                String number = Byte.toString(temp.get(address));
                temp.remove(address);
                DATA.add(i, new NavigatorRow(navigatorRow.getProperty(), number));
            }
        }
        else {
            DATA.clear();
        }
        temp.keySet().forEach((address) -> {
            String number = Integer.toString(temp.get(address));
            DATA.add(new NavigatorRow(Integer.toString(address), number));
        });
    }
}
