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
package com.kasirgalabs.etumulator.listener;

import com.kasirgalabs.arm.ArmBaseListener;
import com.kasirgalabs.arm.ArmParser;
import com.kasirgalabs.etumulator.patterns.Registry;
import com.kasirgalabs.etumulator.operand2.Decimal;
import com.kasirgalabs.etumulator.operand2.Hex;
import com.kasirgalabs.etumulator.operand2.Imm8m;
import com.kasirgalabs.etumulator.operand2.Number;
import com.kasirgalabs.etumulator.operand2.Operand2;
import com.kasirgalabs.etumulator.registers.RdRegister;
import com.kasirgalabs.etumulator.registers.RmRegister;
import com.kasirgalabs.etumulator.registers.RnRegister;

public class ETUmulatorListener extends ArmBaseListener {
    @Override
    public void exitAdd(ArmParser.AddContext ctx) {
        RdRegister rdRegister = Registry.get(RdRegister.class);
        RnRegister rnRegister = Registry.get(RnRegister.class);
        Operand2 operand2 = Registry.get(Operand2.class);
        int value0 = rnRegister.getValue();
        int value1 = operand2.getValue();
        rdRegister.setValue(value0 + value1);
        rdRegister.update();
        System.out.println(value0 + value1);
    }

    @Override
    public void exitRd(ArmParser.RdContext ctx) {
        Registry.put(RdRegister.class, new RdRegister(ctx));
    }

    @Override
    public void exitRn(ArmParser.RnContext ctx) {
        Registry.put(RnRegister.class, new RnRegister(ctx));
    }

    @Override
    public void exitRm(ArmParser.RmContext ctx) {
        Registry.put(Operand2.class, new RmRegister(ctx));
    }

    @Override
    public void exitImm8m(ArmParser.Imm8mContext ctx) {
        Registry.put(Operand2.class, Registry.get(Imm8m.class));
    }

    @Override
    public void exitNumber(ArmParser.NumberContext ctx) {
        Registry.put(Imm8m.class, Registry.get(Number.class));
    }

    @Override
    public void exitDecimal(ArmParser.DecimalContext ctx) {
        Registry.put(Number.class, new Decimal(ctx));
    }

    @Override
    public void exitHex(ArmParser.HexContext ctx) {
        Registry.put(Number.class, new Hex(ctx));
    }

    @Override
    public void exitMov(ArmParser.MovContext ctx) {
        RdRegister rdRegister = Registry.get(RdRegister.class);
        Operand2 operand2 = Registry.get(Operand2.class);
        rdRegister.setValue(operand2.getValue());
        rdRegister.update();
    }
}
