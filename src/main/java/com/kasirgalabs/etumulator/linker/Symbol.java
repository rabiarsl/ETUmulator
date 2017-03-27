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

public class Symbol {
    private final String name;
    private final int address;

    public Symbol(String name, int address) {
        this.name = name;
        this.address = address;
    }

    public Symbol(String name) {
        this.name = name;
        this.address = -1;
    }

    public String getName() {
        return name;
    }

    public int getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(this == obj) {
            return true;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        Symbol symbol = (Symbol) obj;
        if(hashCode() != symbol.hashCode()) {
            return false;
        }
        return name.equals(symbol.getName());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public String toString() {
        return name;
    }
}
