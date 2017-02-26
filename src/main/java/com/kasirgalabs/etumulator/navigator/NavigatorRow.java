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
    private static int type;
    private final SimpleStringProperty property;
    private final SimpleStringProperty value;

    public NavigatorRow(String property, String value) {
        switch(type) {
            case NavigatorRowType.BINARY:
                this.property = new SimpleStringProperty(property);
                this.value = new SimpleStringProperty(StringUtils.convertBinary(value));
                break;
            case NavigatorRowType.DECIMAL:
                this.property = new SimpleStringProperty(property);
                this.value = new SimpleStringProperty(value);
                break;
            case NavigatorRowType.HEX:
                this.property = new SimpleStringProperty(property);
                this.value = new SimpleStringProperty(StringUtils.convertHex(value));
                break;
            default:
                this.property = new SimpleStringProperty(property);
                this.value = new SimpleStringProperty(StringUtils.convertAscii(value));
        }
    }

    public static void setType(int type) {
        NavigatorRow.type = type;
    }

    public String getProperty() {
        return property.get();
    }

    public String getValue() {
        return value.get();
    }
}
