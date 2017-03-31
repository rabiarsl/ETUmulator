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
import com.kasirgalabs.etumulator.processor.CPSR;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class CPSRStatus implements Initializable, Observer {
    @FXML
    private TextField negative;
    @FXML
    private TextField zero;
    @FXML
    private TextField carry;
    @FXML
    private TextField overflow;
    private final CPSR cpsr;

    @Inject
    public CPSRStatus(CPSR cpsr) {
        this.cpsr = cpsr;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cpsr.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        int n = cpsr.isNegative() ? 0 : 1;
        negative.setText(Integer.toString(n));
        int z = cpsr.isZero() ? 0 : 1;
        zero.setText(Integer.toString(z));
        int c = cpsr.isCarry() ? 0 : 1;
        carry.setText(Integer.toString(c));
        int v = cpsr.isOverflow() ? 0 : 1;
        overflow.setText(Integer.toString(v));
    }
}
