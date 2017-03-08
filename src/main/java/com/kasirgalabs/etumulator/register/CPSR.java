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

import com.kasirgalabs.etumulator.operand2.Shift;
import com.kasirgalabs.etumulator.processor.Shifter;

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

    public int updateNZ(int value) {
        negative = value < 0;
        zero = value == 0;
        return value;
    }

    public int additionUpdateNZV(int left, int right) {
        int reuslt = left + right;
        updateNZ(left + right);
        try {
            Math.addExact(left, right);
            overflow = false;
        } catch(ArithmeticException e) {
            overflow = true;
        }
        return reuslt;
    }

    public int subtractionUpdateNZV(int left, int right) {
        int reuslt = left - right;
        updateNZ(left - right);
        try {
            Math.subtractExact(left, right);
            overflow = false;
        } catch(ArithmeticException e) {
            overflow = true;
        }
        return reuslt;
    }

    public int shiftUpdateNZC(int value, int shiftAmount, int option) {
        carry = false;
        int result = Shifter.shift(value, shiftAmount - 1, option);
        if(option == Shift.LSL) {
            if(Integer.numberOfLeadingZeros(result) == 0) {
                carry = true;
            }
        }
        else {
            if(Integer.numberOfTrailingZeros(result) == 0) {
                carry = true;
            }
        }
        result = Shifter.shift(result, 1, option);
        return updateNZ(result);
    }

    public void reset() {
        negative = false;
        zero = false;
        carry = false;
        overflow = false;
    }
}
