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
package com.kasirgalabs.etumulator.langtools;

public class Data {
    private final String data;
    private final int address;

    public Data(Data data) {
        this.data = data.getValue();
        this.address = data.getAddress();
    }

    public Data(String data, int address) {
        this.data = data;
        this.address = address;
    }

    public String getValue() {
        return data;
    }

    public Integer getAddress() {
        return address;
    }
}
