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
import com.kasirgalabs.etumulator.Registry;
import com.kasirgalabs.etumulator.operand2.Decimal;
import com.kasirgalabs.etumulator.operand2.Hex;
import com.kasirgalabs.etumulator.operand2.Imm8m;
import com.kasirgalabs.etumulator.operand2.Number;
import com.kasirgalabs.etumulator.operand2.Operand2;
import com.kasirgalabs.etumulator.registers.RdRegister;
import com.kasirgalabs.etumulator.registers.RegisterUtils;
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
        int registerNumber = RegisterUtils.parseRegisterNumber(ctx.REGISTER());
        RdRegister rdRegister = new RdRegister(registerNumber);
        Registry.put(RdRegister.class, rdRegister);
    }

    @Override
    public void exitRn(ArmParser.RnContext ctx) {
        int registerNumber = RegisterUtils.parseRegisterNumber(ctx.REGISTER());
        RnRegister rnRegister = new RnRegister(registerNumber);
        Registry.put(RnRegister.class, rnRegister);
    }

    @Override
    public void exitRm(ArmParser.RmContext ctx) {
        int registerNumber = RegisterUtils.parseRegisterNumber(ctx.REGISTER());
        RmRegister rmRegister = new RmRegister(registerNumber);
        Registry.put(RmRegister.class, rmRegister);
    }

    @Override
    public void exitOperand2(ArmParser.Operand2Context ctx) {
        if(ctx.rm() != null) {
            Registry.put(Operand2.class, Registry.get(RmRegister.class));
        }
        else if(ctx.imm8m() != null) {
            Registry.put(Operand2.class, Registry.get(Imm8m.class));
        }
    }

    @Override
    public void exitImm8m(ArmParser.Imm8mContext ctx) {
        Registry.put(Imm8m.class, Registry.get(Number.class));
    }

    @Override
    public void exitNumber(ArmParser.NumberContext ctx) {
        if(ctx.decimal() != null) {
            Registry.put(Number.class, Registry.get(Decimal.class));
            return;
        }
        Registry.put(Number.class, Registry.get(Hex.class));
    }

    @Override
    public void exitDecimal(ArmParser.DecimalContext ctx) {
        Registry.put(Decimal.class, new Decimal(ctx.DECIMAL().toString()));
    }

    @Override
    public void exitHex(ArmParser.HexContext ctx) {
        Registry.put(Hex.class, new Hex(ctx.HEX().toString()));
    }

    @Override
    public void exitMov(ArmParser.MovContext ctx) {
        RdRegister rdRegister = Registry.get(RdRegister.class);
        Operand2 operand2 = Registry.get(Operand2.class);
        rdRegister.setValue(operand2.getValue());
        rdRegister.update();
    }
}
