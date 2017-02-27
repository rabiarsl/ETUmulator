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
package com.kasirgalabs.etumulator.processor;

import com.kasirgalabs.arm.ArmBaseListener;
import com.kasirgalabs.arm.ArmParser;
import com.kasirgalabs.etumulator.operand2.Decimal;
import com.kasirgalabs.etumulator.operand2.Hex;
import com.kasirgalabs.etumulator.operand2.Imm8m;
import com.kasirgalabs.etumulator.operand2.Number;
import com.kasirgalabs.etumulator.operand2.Operand2;
import com.kasirgalabs.etumulator.register.RdRegister;
import com.kasirgalabs.etumulator.register.RmRegister;
import com.kasirgalabs.etumulator.register.RnRegister;

public class Processor extends ArmBaseListener {
    private RdRegister rdRegister;
    private RnRegister rnRegister;
    private Operand2 operand2;
    private Imm8m imm8m;
    private Number number;

    @Override
    public void exitAdd(ArmParser.AddContext ctx) {
        rdRegister.setValue(rnRegister.getValue() + operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitAnd(ArmParser.AndContext ctx) {
        rdRegister.setValue(rnRegister.getValue() & operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitOrr(ArmParser.OrrContext ctx) {
        rdRegister.setValue(rnRegister.getValue() | operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitEor(ArmParser.EorContext ctx) {
        rdRegister.setValue(rnRegister.getValue() ^ operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitOrn(ArmParser.OrnContext ctx) {
        rdRegister.setValue(rnRegister.getValue() | ~operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitBic(ArmParser.BicContext ctx) {
        rdRegister.setValue(rnRegister.getValue() & ~operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitMvn(ArmParser.MvnContext ctx) {
        rdRegister.setValue(0xffffffff ^ operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitRd(ArmParser.RdContext ctx) {
        rdRegister = new RdRegister(ctx);
    }

    @Override
    public void exitRn(ArmParser.RnContext ctx) {
        rnRegister = new RnRegister(ctx);
    }

    @Override
    public void exitRm(ArmParser.RmContext ctx) {
        operand2 = new RmRegister(ctx);
    }

    @Override
    public void exitImm8m(ArmParser.Imm8mContext ctx) {
        operand2 = imm8m;
    }

    @Override
    public void exitNumber(ArmParser.NumberContext ctx) {
        imm8m = number;
    }

    @Override
    public void exitDecimal(ArmParser.DecimalContext ctx) {
        number = new Decimal(ctx);
    }

    @Override
    public void exitHex(ArmParser.HexContext ctx) {
        number = new Hex(ctx);
    }

    @Override
    public void exitMov(ArmParser.MovContext ctx) {
        rdRegister.setValue(operand2.getValue());
        rdRegister.update();
    }
}
