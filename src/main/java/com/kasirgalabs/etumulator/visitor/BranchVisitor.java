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
package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.etumulator.processor.APSR;
import com.kasirgalabs.etumulator.processor.LR;
import com.kasirgalabs.etumulator.processor.PC;
import com.kasirgalabs.etumulator.processor.UART;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;
import java.util.concurrent.CancellationException;

public class BranchVisitor extends ProcessorBaseVisitor<Void> {
    private final APSR apsr;
    private final UART uart;
    private final PC pc;
    private final LR lr;

    public BranchVisitor(APSR apsr, UART uart, PC pc, LR lr) {
        this.apsr = apsr;
        this.uart = uart;
        this.pc = pc;
        this.lr = lr;
    }

    @Override
    public Void visitB(ProcessorParser.BContext ctx) {
        pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        return null;
    }

    @Override
    public Void visitBeq(ProcessorParser.BeqContext ctx) {
        if(apsr.isZero()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBne(ProcessorParser.BneContext ctx) {
        if(!apsr.isZero()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBcs(ProcessorParser.BcsContext ctx) {
        if(apsr.isCarry()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBhs(ProcessorParser.BhsContext ctx) {
        if(apsr.isCarry()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBcc(ProcessorParser.BccContext ctx) {
        if(!apsr.isCarry()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBlo(ProcessorParser.BloContext ctx) {
        if(!apsr.isCarry()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBmi(ProcessorParser.BmiContext ctx) {
        if(apsr.isNegative()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBpl(ProcessorParser.BplContext ctx) {
        if(!apsr.isNegative()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBvs(ProcessorParser.BvsContext ctx) {
        if(apsr.isOverflow()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBvc(ProcessorParser.BvcContext ctx) {
        if(!apsr.isOverflow()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBhi(ProcessorParser.BhiContext ctx) {
        if(apsr.isCarry() && !apsr.isZero()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBls(ProcessorParser.BlsContext ctx) {
        if(!apsr.isCarry() || apsr.isZero()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBge(ProcessorParser.BgeContext ctx) {
        if(apsr.isNegative() == apsr.isOverflow()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBlt(ProcessorParser.BltContext ctx) {
        if(apsr.isNegative() != apsr.isOverflow()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBgt(ProcessorParser.BgtContext ctx) {
        if(!apsr.isZero() && apsr.isNegative() == apsr.isOverflow()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBle(ProcessorParser.BleContext ctx) {
        if(apsr.isZero() || apsr.isNegative() != apsr.isOverflow()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBal(ProcessorParser.BalContext ctx) {
        pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        return null;
    }

    @Override
    public Void visitBl(ProcessorParser.BlContext ctx) {
        lr.setValue((int) (Math.random() * Integer.MAX_VALUE));
        if(ctx.LABEL() != null) {
            String label = ctx.LABEL().getText();
            if("uart_read".equalsIgnoreCase(label)) {
                try {
                    uart.read();
                } catch(InterruptedException ex) {
                    throw new CancellationException();
                }
            }
            else if("uart_write".equalsIgnoreCase(label)) {
                uart.write();
            }
        }
        else {
            lr.setValue(pc.getValue());
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }
}
