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
import com.kasirgalabs.etumulator.processor.APSR;
import com.kasirgalabs.etumulator.util.Observer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class APSRStatus implements Initializable, Observer {
    @FXML
    private TextField negative;
    @FXML
    private TextField zero;
    @FXML
    private TextField carry;
    @FXML
    private TextField overflow;
    private final APSR apsr;

    @Inject
    public APSRStatus(APSR apsr) {
        this.apsr = apsr;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        apsr.addObserver(this);
    }

    @Override
    public void update(Class<?> clazz, Object arg) {
        int n = apsr.isNegative() ? 0 : 1;
        negative.setText(Integer.toString(n));
        int z = apsr.isZero() ? 0 : 1;
        zero.setText(Integer.toString(z));
        int c = apsr.isCarry() ? 0 : 1;
        carry.setText(Integer.toString(c));
        int v = apsr.isOverflow() ? 0 : 1;
        overflow.setText(Integer.toString(v));
    }
}
