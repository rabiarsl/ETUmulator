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

public class RegisterVisitor extends ProcessorBaseVisitor<String> {
    @Override
    public String visitRd(ProcessorParser.RdContext ctx) {
        return ctx.REGISTER().getText();
    }

    @Override
    public String visitRn(ProcessorParser.RnContext ctx) {
        return ctx.REGISTER().getText();
    }

    @Override
    public String visitRm(ProcessorParser.RmContext ctx) {
        return ctx.REGISTER().getText();
    }

    @Override
    public String visitRs(ProcessorParser.RsContext ctx) {
        return ctx.REGISTER().getText();
    }
}
