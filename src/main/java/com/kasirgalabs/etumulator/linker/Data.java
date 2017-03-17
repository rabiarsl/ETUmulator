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
package com.kasirgalabs.etumulator.linker;

import java.util.Objects;

public class Data {
    private final Label label;
    private String data;

    public Data(Label label) {
        this.label = label;
    }

    public Data(Data data) {
        this.label = data.getLabel();
        this.data = data.getData();
    }

    public Data(String label) {
        this.label = new Label(label);
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public Label getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(this == obj) {
            return true;
        }
        if(this.getClass() != obj.getClass()) {
            return false;
        }
        Data temp = (Data) obj;
        if(this.hashCode() != temp.hashCode()) {
            return false;
        }
        return this.label.getName().equals(temp.getLabel().getName());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.label.getName());
        return hash;
    }
}
