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

public class NavigatorRow {
    public static final int DECIMAL = 0;
    public static final int HEX = 1;
    public static final int ASCII = 2;
    public static final int BINARY = 3;
    private static int valueType;
    private final String property;
    private String value;

    public NavigatorRow(String property, String value) {
        this.property = property;
        this.value = value;
    }

    public NavigatorRow(String property, int value) {
        this.property = property;
        this.value = Integer.toString(value);
    }

    public NavigatorRow(int property, int value) {
        this.property = Integer.toString(property);
        this.value = Integer.toString(value);
    }

    public static void setValueType(int type) {
        valueType = type;
    }

    public String getProperty() {
        return property;
    }

    public void setValue(int value) {
        this.value = Integer.toString(value);
    }

    public String getValue() {
        switch(valueType) {
            case BINARY:
                return StringUtils.toBinaryString(value);
            case DECIMAL:
                return value;
            case HEX:
                return StringUtils.toHexString(value);
            case ASCII:
                return StringUtils.toAsciiString(value);
            default:
                return value;
        }
    }

    public void setValue(String value) {
        this.value = value;
    }
}
