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
import com.kasirgalabs.arm.ArmLexer;
import com.kasirgalabs.arm.ArmParser;
import com.kasirgalabs.etumulator.operand2.Decimal;
import com.kasirgalabs.etumulator.operand2.Hex;
import com.kasirgalabs.etumulator.operand2.Imm8m;
import com.kasirgalabs.etumulator.operand2.Number;
import com.kasirgalabs.etumulator.operand2.Operand2;
import com.kasirgalabs.etumulator.operand2.Shift;
import com.kasirgalabs.etumulator.register.CPSR;
import com.kasirgalabs.etumulator.register.RdRegister;
import com.kasirgalabs.etumulator.register.RegisterFile;
import com.kasirgalabs.etumulator.register.RmRegister;
import com.kasirgalabs.etumulator.register.RnRegister;
import com.kasirgalabs.etumulator.register.RsRegister;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Processor extends ArmBaseListener {
    private RdRegister rdRegister;
    private RnRegister rnRegister;
    private RmRegister rmRegister;
    private Shift shift;
    private RsRegister rsRegister;
    private Operand2 operand2;
    private Imm8m imm8m;
    private Number number;
    private final RegisterFile registerFile;
    private final CPSR cpsr;

    public Processor(RegisterFile registerFile, CPSR cpsr) {
        this.registerFile = registerFile;
        this.cpsr = cpsr;
    }

    public void run(char[] code) {
        ANTLRInputStream in = new ANTLRInputStream(code, code.length);
        ArmLexer lexer = new ArmLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArmParser parser = new ArmParser(tokens);
        ArmParser.ProgContext tree = parser.prog();
        if(parser.getNumberOfSyntaxErrors() != 0) {
            return;
        }
        ParseTreeWalker.DEFAULT.walk(this, tree);
    }

    @Override
    public void exitAdd(ArmParser.AddContext ctx) {
        rdRegister.setValue(rnRegister.getValue() + operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitAdds(ArmParser.AddsContext ctx) {
        rdRegister.setValue(cpsr.additionUpdateNZV(rnRegister.getValue(), operand2.getValue()));
        rdRegister.update();
    }

    @Override
    public void exitAdc(ArmParser.AdcContext ctx) {
        int result = rnRegister.getValue() + operand2.getValue();
        if(cpsr.isCarry()) {
            result++;
        }
        rdRegister.setValue(result);
        rdRegister.update();
    }

    @Override
    public void exitAdcs(ArmParser.AdcsContext ctx) {
        int result = cpsr.additionUpdateNZV(rnRegister.getValue(), operand2.getValue());
        if(cpsr.isCarry()) {
            if(!cpsr.isOverflow()) {
                cpsr.additionUpdateNZV(result, 1);
            }
            result++;
        }
        rdRegister.setValue(result);
        rdRegister.update();
    }

    @Override
    public void exitSub(ArmParser.SubContext ctx) {
        rdRegister.setValue(rnRegister.getValue() - operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitSubs(ArmParser.SubsContext ctx) {
        rdRegister.setValue(cpsr.subtractionUpdateNZV(rnRegister.getValue(), operand2.getValue()));
        rdRegister.update();
    }

    @Override
    public void exitSbc(ArmParser.SbcContext ctx) {
        int result = rnRegister.getValue() - operand2.getValue();
        if(!cpsr.isCarry()) {
            result--;
        }
        rdRegister.setValue(result);
        rdRegister.update();
    }

    @Override
    public void exitSbcs(ArmParser.SbcsContext ctx) {
        int result = cpsr.subtractionUpdateNZV(rnRegister.getValue(), operand2.getValue());
        if(!cpsr.isCarry()) {
            if(!cpsr.isOverflow()) {
                cpsr.subtractionUpdateNZV(result, 1);
            }
            result--;
        }
        rdRegister.setValue(result);
        rdRegister.update();
    }

    @Override
    public void exitRsb(ArmParser.RsbContext ctx) {
        rdRegister.setValue(operand2.getValue() - rnRegister.getValue());
        rdRegister.update();
    }

    @Override
    public void exitRsbs(ArmParser.RsbsContext ctx) {
        rdRegister.setValue(cpsr.subtractionUpdateNZV(operand2.getValue(), rnRegister.getValue()));
        rdRegister.update();
    }

    @Override
    public void exitRsc(ArmParser.RscContext ctx) {
        int result = operand2.getValue() - rnRegister.getValue();
        if(cpsr.isCarry()) {
            result--;
        }
        rdRegister.setValue(result);
        rdRegister.update();
    }

    @Override
    public void exitRscs(ArmParser.RscsContext ctx) {
        int result = cpsr.subtractionUpdateNZV(operand2.getValue(), rnRegister.getValue());
        if(!cpsr.isCarry()) {
            if(!cpsr.isOverflow()) {
                cpsr.subtractionUpdateNZV(result, 1);
            }
            result--;
        }
        rdRegister.setValue(result);
        rdRegister.update();
    }

    @Override
    public void exitAnd(ArmParser.AndContext ctx) {
        rdRegister.setValue(rnRegister.getValue() & operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitAnds(ArmParser.AndsContext ctx) {
        rdRegister.setValue(cpsr.updateNZ(rnRegister.getValue() & operand2.getValue()));
        rdRegister.update();
    }

    @Override
    public void exitEor(ArmParser.EorContext ctx) {
        rdRegister.setValue(rnRegister.getValue() ^ operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitEors(ArmParser.EorsContext ctx) {
        rdRegister.setValue(cpsr.updateNZ(rnRegister.getValue() ^ operand2.getValue()));
        rdRegister.update();
    }

    @Override
    public void exitOrr(ArmParser.OrrContext ctx) {
        rdRegister.setValue(rnRegister.getValue() | operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitOrrs(ArmParser.OrrsContext ctx) {
        rdRegister.setValue(cpsr.updateNZ(rnRegister.getValue() | operand2.getValue()));
        rdRegister.update();
    }

    @Override
    public void exitOrn(ArmParser.OrnContext ctx) {
        rdRegister.setValue(rnRegister.getValue() | ~operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitOrns(ArmParser.OrnsContext ctx) {
        rdRegister.setValue(cpsr.updateNZ(rnRegister.getValue() | ~operand2.getValue()));
        rdRegister.update();
    }

    @Override
    public void exitBic(ArmParser.BicContext ctx) {
        rdRegister.setValue(rnRegister.getValue() & ~operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitBics(ArmParser.BicsContext ctx) {
        rdRegister.setValue(cpsr.updateNZ(rnRegister.getValue() & ~operand2.getValue()));
        rdRegister.update();
    }

    @Override
    public void exitMov(ArmParser.MovContext ctx) {
        rdRegister.setValue(operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitMovs(ArmParser.MovsContext ctx) {
        rdRegister.setValue(cpsr.updateNZ(operand2.getValue()));
        rdRegister.update();
    }

    @Override
    public void exitMvn(ArmParser.MvnContext ctx) {
        rdRegister.setValue(0xffffffff ^ operand2.getValue());
        rdRegister.update();
    }

    @Override
    public void exitMvns(ArmParser.MvnsContext ctx) {
        rdRegister.setValue(cpsr.updateNZ(0xffffffff ^ operand2.getValue()));
        rdRegister.update();
    }

    @Override
    public void exitMul(ArmParser.MulContext ctx) {
        rdRegister.setValue(rmRegister.getValue() * rsRegister.getValue());
        rdRegister.update();
    }

    @Override
    public void exitMuls(ArmParser.MulsContext ctx) {
        rdRegister.setValue(cpsr.updateNZ(rmRegister.getValue() * rsRegister.getValue()));
        rdRegister.update();
    }

    @Override
    public void exitMla(ArmParser.MlaContext ctx) {
        rdRegister.setValue(rnRegister.getValue() + (rmRegister.getValue() * rsRegister.getValue()));
        rdRegister.update();
    }

    @Override
    public void exitMlas(ArmParser.MlasContext ctx) {
        rdRegister.setValue(cpsr.updateNZ(rnRegister.getValue()
                + (rmRegister.getValue() * rsRegister.getValue())));
        rdRegister.update();
    }

    @Override
    public void exitMls(ArmParser.MlsContext ctx) {
        rdRegister.setValue(rnRegister.getValue() - (rmRegister.getValue() * rsRegister.getValue()));
        rdRegister.update();
    }

    @Override
    public void exitCmp(ArmParser.CmpContext ctx) {
        cpsr.subtractionUpdateNZV(rnRegister.getValue(), operand2.getValue());
    }

    @Override
    public void exitCmn(ArmParser.CmnContext ctx) {
        cpsr.additionUpdateNZV(rnRegister.getValue(), operand2.getValue());
    }

    @Override
    public void exitTst(ArmParser.TstContext ctx) {
        cpsr.updateNZ(rnRegister.getValue() & operand2.getValue());
    }

    @Override
    public void exitTeq(ArmParser.TeqContext ctx) {
        cpsr.updateNZ(rnRegister.getValue() ^ operand2.getValue());
    }

    @Override
    public void exitLdr(ArmParser.LdrContext ctx) {
        rdRegister.setValue(number.getValue());
        rdRegister.update();
    }

    @Override
    public void exitRd(ArmParser.RdContext ctx) {
        rdRegister = new RdRegister(ctx, registerFile);
    }

    @Override
    public void exitRn(ArmParser.RnContext ctx) {
        rnRegister = new RnRegister(ctx, registerFile);
    }

    @Override
    public void exitRm(ArmParser.RmContext ctx) {
        rmRegister = new RmRegister(ctx, registerFile);
        operand2 = rmRegister;
    }

    @Override
    public void exitRs(ArmParser.RsContext ctx) {
        rsRegister = new RsRegister(ctx, registerFile);
    }

    @Override
    public void exitImm8m(ArmParser.Imm8mContext ctx) {
        operand2 = imm8m;
    }

    @Override
    public void exitAsr(ArmParser.AsrContext ctx) {
        rdRegister.setValue(Shifter.shift(rmRegister.getValue(),
                shift.getAmount(),
                Shift.ASR));
        rdRegister.update();
    }

    @Override
    public void exitAsrs(ArmParser.AsrsContext ctx) {
        rdRegister.setValue(cpsr.shiftUpdateNZC(rmRegister.getValue(),
                shift.getAmount(),
                Shift.ASR));
        rdRegister.update();
    }

    @Override
    public void exitLsl(ArmParser.LslContext ctx) {
        rdRegister.setValue(Shifter.shift(rmRegister.getValue(),
                shift.getAmount(),
                Shift.LSL));
        rdRegister.update();
    }

    @Override
    public void exitLsls(ArmParser.LslsContext ctx) {
        rdRegister.setValue(cpsr.shiftUpdateNZC(rmRegister.getValue(),
                shift.getAmount(),
                Shift.LSL));
        rdRegister.update();
    }

    @Override
    public void exitLsr(ArmParser.LsrContext ctx) {
        rdRegister.setValue(Shifter.shift(rmRegister.getValue(),
                shift.getAmount(),
                Shift.LSR));
        rdRegister.update();
    }

    @Override
    public void exitLsrs(ArmParser.LsrsContext ctx) {
        rdRegister.setValue(cpsr.shiftUpdateNZC(rmRegister.getValue(),
                shift.getAmount(),
                Shift.LSR));
        rdRegister.update();
    }

    @Override
    public void exitRor(ArmParser.RorContext ctx) {
        rdRegister.setValue(Shifter.shift(rmRegister.getValue(),
                shift.getAmount(),
                Shift.ROR));
        rdRegister.update();
    }

    @Override
    public void exitRors(ArmParser.RorsContext ctx) {
        rdRegister.setValue(cpsr.shiftUpdateNZC(rmRegister.getValue(),
                shift.getAmount(),
                Shift.ROR));
        rdRegister.update();
    }

    @Override
    public void exitShiftedRm(ArmParser.ShiftedRmContext ctx) {
        rmRegister.setValue(Shifter.shift(rmRegister.getValue(),
                shift.getAmount(),
                shift.getOption()));
    }

    @Override
    public void exitShiftOption(ArmParser.ShiftOptionContext ctx) {
        shift = new Shift(ctx);
    }

    @Override
    public void exitShiftAmount(ArmParser.ShiftAmountContext ctx) {
        if(shift == null) {
            shift = new Shift();
        }
        shift.setAmount(number.getValue());
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
}
