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
package com.kasirgalabs.etumulator.processor.visitor;

import com.kasirgalabs.arm.ProcessorBaseVisitor;
import com.kasirgalabs.arm.ProcessorParser;
import com.kasirgalabs.etumulator.processor.CPSR;
import com.kasirgalabs.etumulator.processor.UART;

public class BranchVisitor extends ProcessorBaseVisitor<Integer> {
    private final CPSR cpsr;
    private final UART uart;

    public BranchVisitor(CPSR cpsr, UART uart) {
        this.cpsr = cpsr;
        this.uart = uart;
    }

    @Override
    public Integer visitB(ProcessorParser.BContext ctx) {
        return Integer.parseInt(ctx.DECIMAL().getText());
    }

    @Override
    public Integer visitBeq(ProcessorParser.BeqContext ctx) {
        if(cpsr.isZero()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBne(ProcessorParser.BneContext ctx) {
        if(!cpsr.isZero()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBcs(ProcessorParser.BcsContext ctx) {
        if(cpsr.isCarry()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBhs(ProcessorParser.BhsContext ctx) {
        if(cpsr.isCarry()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBcc(ProcessorParser.BccContext ctx) {
        if(!cpsr.isCarry()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBlo(ProcessorParser.BloContext ctx) {
        if(!cpsr.isCarry()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBmi(ProcessorParser.BmiContext ctx) {
        if(cpsr.isNegative()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBpl(ProcessorParser.BplContext ctx) {
        if(!cpsr.isNegative()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBvs(ProcessorParser.BvsContext ctx) {
        if(cpsr.isOverflow()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBvc(ProcessorParser.BvcContext ctx) {
        if(!cpsr.isOverflow()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBhi(ProcessorParser.BhiContext ctx) {
        if(cpsr.isCarry() && !cpsr.isZero()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBls(ProcessorParser.BlsContext ctx) {
        if(!cpsr.isCarry() || cpsr.isZero()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBge(ProcessorParser.BgeContext ctx) {
        if(cpsr.isNegative() == cpsr.isOverflow()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBlt(ProcessorParser.BltContext ctx) {
        if(cpsr.isNegative() != cpsr.isOverflow()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBgt(ProcessorParser.BgtContext ctx) {
        if(!cpsr.isZero() && cpsr.isNegative() == cpsr.isOverflow()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBle(ProcessorParser.BleContext ctx) {
        if(cpsr.isZero() || cpsr.isNegative() != cpsr.isOverflow()) {
            return Integer.parseInt(ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Integer visitBal(ProcessorParser.BalContext ctx) {
        return Integer.parseInt(ctx.DECIMAL().getText());
    }

    @Override
    public Integer visitBl(ProcessorParser.BlContext ctx) {
        String label = ctx.LABEL().getText();
        if("uart_read".equalsIgnoreCase(label)) {
            try {
                uart.read();
            } catch(InterruptedException ex) {
                System.exit(-1);
            }
        }
        else if("uart_write".equalsIgnoreCase(label)) {
            uart.write();
        }
        return null;
    }
}
