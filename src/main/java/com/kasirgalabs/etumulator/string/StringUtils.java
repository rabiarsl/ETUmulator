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
package com.kasirgalabs.etumulator.string;

public final class StringUtils {
    private static final int BIT_WIDTH = 32;

    private StringUtils() {
    }

    public static String toBinaryString(int value) {
        StringBuilder binaryString = new StringBuilder(32);
        int missingBits = BIT_WIDTH - Integer.toBinaryString(value).length();
        for(int i = 0; i < missingBits; i++) {
            binaryString.append("0");
        }
        binaryString.append(Integer.toBinaryString(value));
        return binaryString.toString();
    }

    public static String toHexString(int value) {
        StringBuilder hexString = new StringBuilder(32);
        hexString.append("0x");
        int missingBits = BIT_WIDTH / 4 - Integer.toHexString(value).length();
        for(int i = 0; i < missingBits; i++) {
            hexString.append("0");
        }
        hexString.append(Integer.toHexString(value));
        return hexString.toString();
    }

    public static String toAsciiString(int value) {
        return AsciiTable.getAscii((char) value);
    }
}
