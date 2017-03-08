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

import com.kasirgalabs.arm.ArmParser;

public class Shift {
    public static final int LSL = 0;
    public static final int LSR = 1;
    public static final int ASR = 2;
    public static final int ROR = 3;
    private int option;
    private int value;

    public Shift(ArmParser.ShiftOptionContext option) {
        switch(option.getText().toLowerCase()) {
            case "lsl":
                this.option = LSL;
                break;
            case "lsr":
                this.option = LSR;
                break;
            case "asr":
                this.option = ASR;
                break;
            case "ror":
                this.option = ROR;
                break;
        }
    }

    public int getOption() {
        return option;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
