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

public class CPSR {
    private boolean negative;
    private boolean zero;
    private boolean carry;
    private boolean overflow;

    public boolean isNegative() {
        return negative;
    }

    public boolean isZero() {
        return zero;
    }

    public void setCarry(boolean carry) {
        this.carry = carry;
    }

    public boolean isCarry() {
        return carry;
    }

    public boolean isOverflow() {
        return overflow;
    }

    public void updateNZ(int value) {
        negative = value < 0;
        zero = value == 0;
    }

    public void additionUpdateNZV(int left, int right) {
        updateNZ(left + right);
        try {
            Math.addExact(left, right);
            overflow = false;
        } catch(ArithmeticException e) {
            overflow = true;
        }
    }

    public void subtractionUpdateNZV(int left, int right) {
        updateNZ(left - right);
        try {
            Math.subtractExact(left, right);
            overflow = false;
        } catch(ArithmeticException e) {
            overflow = true;
        }
    }

    public void reset() {
        negative = false;
        zero = false;
        carry = false;
        overflow = false;
    }
}
