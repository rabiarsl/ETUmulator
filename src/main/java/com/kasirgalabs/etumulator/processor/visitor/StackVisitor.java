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
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.etumulator.processor.Stack;
import java.util.ArrayList;
import java.util.List;

public class StackVisitor extends ArmBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final Stack stack;
    private final RegListVisitor regListVisitor;

    public StackVisitor(RegisterFile registerFile, Stack stack) {
        this.registerFile = registerFile;
        this.stack = stack;
        regListVisitor = new RegListVisitor();
    }

    @Override
    public Void visitPush(ArmParser.PushContext ctx) {
        List<String> regList = regListVisitor.visit(ctx.regList());
        regList.forEach((registerName) -> {
            stack.push(registerFile.getValue(registerName));
        });
        return null;
    }

    @Override
    public Void visitPop(ArmParser.PopContext ctx) {
        List<String> regList = regListVisitor.visit(ctx.regList());
        regList.forEach((registerName) -> {
            registerFile.setValue(registerName, stack.pop());
        });
        return null;
    }

    private static class RegListVisitor extends ArmBaseVisitor<List<String>> {
        @Override
        public List<String> visitRegList(ArmParser.RegListContext ctx) {
            List<String> regList = new ArrayList<>();
            ctx.REGISTER().forEach((terminalNode) -> {
                regList.add(terminalNode.getText());
            });
            return regList;
        }
    }
}
