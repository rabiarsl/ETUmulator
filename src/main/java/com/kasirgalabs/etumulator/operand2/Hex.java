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
package com.kasirgalabs.etumulator.operand2;

import com.kasirgalabs.arm.ArmParser.HexContext;

public class Hex extends Number {
    public Hex(HexContext ctx) {
        String value = ctx.HEX().toString();
        if(value.contains("#")) {
            value = value.substring(1);
        }
        value = value.substring(2);
        super.setValue(Integer.parseInt(value, 16));
    }
}
