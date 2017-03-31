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

import java.util.HashSet;
import java.util.Set;

public class ExecutableCode {
    private final String code;
    private final Set<Symbol> symbols;

    public ExecutableCode(String code, Set<Symbol> symbols) {
        this.code = code;
        this.symbols = new HashSet<>(symbols.size());
        symbols.forEach((symbol) -> {
            this.symbols.add(new Symbol(symbol));
        });
    }

    public String getCode() {
        return code;
    }

    public final Set<Symbol> getSymbols() {
        Set<Symbol> temp = new HashSet<>(symbols.size());
        symbols.forEach((symbol) -> {
            temp.add(new Symbol(symbol));
        });
        return temp;
    }
}
