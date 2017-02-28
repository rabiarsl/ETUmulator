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

public class UnsignedIntegerRegister implements Register {
    private static final long MAX_VALUE = 4294967295L;
    private static final long MASK = 0x00000000FFFFFFFFL;
    private long value;

    public UnsignedIntegerRegister() {
        value = 0;
    }

    public UnsignedIntegerRegister(long value) {
        this.value = toUnsinged32Bit(value);
    }

    @Override
    public void setValue(long value) {
        this.value = toUnsinged32Bit(value);
    }

    @Override
    public long getValue() {
        return value;
    }

    public void reset() {
        this.value = 0;
    }

    private long toUnsinged32Bit(long result) {
        long temp = result;
        if(temp < 0) {
            temp = -temp;
        }
        if(temp > MAX_VALUE) {
            temp &= MASK;
        }
        return temp;
    }
}
