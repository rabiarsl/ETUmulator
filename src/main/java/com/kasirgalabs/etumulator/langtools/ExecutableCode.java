/*
 * Copyright (C) 2017 Kasirgalabs
 *
 * This code is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this code.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kasirgalabs.etumulator.langtools;

import java.util.ArrayList;
import java.util.List;

public class ExecutableCode {
    private final char[][] code;
    private final List<Data> data;

    public ExecutableCode(char[][] code, List<Data> data) {
        this.code = new char[code.length][];
        for(int i = 0; i < code.length; i++) {
            this.code[i] = new char[code[i].length];
            System.arraycopy(code[i], 0, this.code[i], 0, code[i].length);
        }
        this.data = new ArrayList<>(data.size());
        for(int i = 0; i < data.size(); i++) {
            this.data.add(new Data(data.get(i)));
        }
    }

    public char[][] getCode() {
        char[][] temp = new char[code.length][];
        for(int i = 0; i < code.length; i++) {
            temp[i] = new char[code[i].length];
            System.arraycopy(code[i], 0, temp[i], 0, code[i].length);
        }
        return temp;
    }

    public List<Data> getData() {
        List<Data> temp = new ArrayList<>(data.size());
        for(int i = 0; i < data.size(); i++) {
            temp.add(new Data(data.get(i)));
        }
        return temp;
    }
}
