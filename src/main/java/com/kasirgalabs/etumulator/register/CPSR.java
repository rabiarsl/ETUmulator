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
package com.kasirgalabs.etumulator.register;

public final class CPSR {
    private static boolean overflow;
    private static boolean zero;
    private static boolean carry;
    private static boolean negative;

    private CPSR() {
    }

    public static boolean isOverflow() {
        return overflow;
    }

    public static void setOverflow(boolean overflow) {
        CPSR.overflow = overflow;
    }

    public static boolean isZero() {
        return zero;
    }

    public static void setZero(boolean zero) {
        CPSR.zero = zero;
    }

    public static boolean isCarry() {
        return carry;
    }

    public static void setCarry(boolean carry) {
        CPSR.carry = carry;
    }

    public static boolean isNegative() {
        return negative;
    }

    public static void setNegative(boolean negative) {
        CPSR.negative = negative;
    }

    public static void updateWithoutOverflow(long value) {
        negative = value < 0;
        zero = value == 0;
        carry = (value & (1L << 32)) != 0;
    }

    public static void updateWithOverflow(long value) {
        updateWithoutOverflow(value);
        overflow = false;
        if(value < 0 || value > RegisterUtils.MAX32) {
            overflow = true;
        }
    }
}
