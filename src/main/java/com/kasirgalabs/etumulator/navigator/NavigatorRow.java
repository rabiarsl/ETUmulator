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

import com.kasirgalabs.etumulator.string.StringUtils;
import javafx.beans.property.SimpleStringProperty;

public class NavigatorRow {
    private static int rowType = -1;
    private static int rowContent = -1;
    private SimpleStringProperty property;
    private SimpleStringProperty value;

    public NavigatorRow(String property, String value) {
        this.property = new SimpleStringProperty(property);
        switch(rowType) {
            case NavigatorRowType.BINARY:
                this.value = new SimpleStringProperty(StringUtils.toBinaryString(value));
                break;
            case NavigatorRowType.DECIMAL:
                this.value = new SimpleStringProperty(value);
                break;
            case NavigatorRowType.HEX:
                this.value = new SimpleStringProperty(StringUtils.toHexString(value));
                break;
            case NavigatorRowType.ASCII:
                this.value = new SimpleStringProperty(StringUtils.toAsciiString(value));
                break;
            default:
                this.property = new SimpleStringProperty(property);
                this.value = new SimpleStringProperty(value);
        }
    }

    public static void setRowType(int rowType) {
        NavigatorRow.rowType = rowType;
    }

    public static void setRowContent(int rowContent) {
        NavigatorRow.rowContent = rowContent;
    }

    public static int getRowContent() {
        return rowContent;
    }

    public String getProperty() {
        return property.get();
    }

    public String getValue() {
        return value.get();
    }

    public void setValue(String value) {
        this.value = new SimpleStringProperty(value);
    }
}
