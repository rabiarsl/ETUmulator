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
    private StringUtils() {
    }

    public static String convertBinary(String decimal) {
        int temp = Integer.parseInt(decimal);
        String binaryString = Integer.toBinaryString(temp);
        int missingBits = 32 - binaryString.length();
        for(int i = 0; i < missingBits; i++) {
            binaryString = "0" + binaryString;
        }
        return binaryString;
    }

    public static String convertHex(String decimal) {
        int temp = Integer.parseInt(decimal);
        String hexString = Long.toHexString(temp);
        int missingBits = 8 - hexString.length();
        for(int i = 0; i < missingBits; i++) {
            hexString = "0" + hexString;
        }
        return "0x" + hexString;
    }

    public static String convertAscii(String decimal) {
        return AsciiTable.getAscii(Integer.parseInt(decimal));
    }
}
