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
import com.kasirgalabs.etumulator.processor.Stack;
import com.kasirgalabs.etumulator.util.Observer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StackTab implements Initializable, Observer {
    @FXML
    private TableView<NavigatorRow> table;
    @FXML
    private TableColumn<NavigatorRow, String> property;
    @FXML
    private TableColumn<NavigatorRow, String> value;
    private final Stack stack;
    private final Navigator navigator;
    private final ObservableList<NavigatorRow> data;

    @Inject
    public StackTab(Stack stack, Navigator navigator) {
        this.stack = stack;
        this.navigator = navigator;
        data = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stack.addObserver(this);
        navigator.addObserver(this);
        property.setCellValueFactory(new PropertyValueFactory<>("property"));
        property.setComparator(new NavigatorRowComparator());
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        table.setItems(data);
    }

    @Override
    public void update(Class<?> clazz, Object arg) {
        if(clazz.equals(Stack.class)) {
            String operation = (String) arg;
            switch(operation) {
                case "push":
                    data.add(new NavigatorRow(data.size(), stack.peek()));
                    break;
                case "pop":
                    for(int i = 0; i < data.size(); i++) {
                        NavigatorRow navigatorRow = data.get(i);
                        if(navigatorRow.getProperty().equals(Integer.toString(data.size() - 1))) {
                            data.remove(i);
                            break;
                        }
                    }
                    break;
                default:
                    data.clear();
                    break;
            }
        }
        table.refresh();
    }
}
