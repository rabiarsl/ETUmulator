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

import com.kasirgalabs.arm.ArmBaseVisitor;
import com.kasirgalabs.arm.ArmParser;

public class RegisterVisitor extends ArmBaseVisitor<String> {
    @Override
    public String visitRd(ArmParser.RdContext ctx) {
        return ctx.REGISTER().getText();
    }

    @Override
    public String visitRn(ArmParser.RnContext ctx) {
        return ctx.REGISTER().getText();
    }

    @Override
    public String visitRm(ArmParser.RmContext ctx) {
        return ctx.REGISTER().getText();
    }

    @Override
    public String visitRs(ArmParser.RsContext ctx) {
        return ctx.REGISTER().getText();
    }
}
