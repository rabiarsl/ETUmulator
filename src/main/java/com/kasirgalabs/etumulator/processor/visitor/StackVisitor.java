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
import com.kasirgalabs.etumulator.processor.LR;
import com.kasirgalabs.etumulator.processor.PC;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.etumulator.processor.Stack;
import java.util.ArrayList;
import java.util.List;

public class StackVisitor extends ProcessorBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final PC pc;
    private final LR lr;
    private final Stack stack;
    private final RegListVisitor regListVisitor;

    public StackVisitor(RegisterFile registerFile, PC pc, LR lr, Stack stack) {
        this.registerFile = registerFile;
        this.pc = pc;
        this.lr = lr;
        this.stack = stack;
        regListVisitor = new RegListVisitor();
    }

    @Override
    public Void visitPush(ProcessorParser.PushContext ctx) {
        List<String> regList = regListVisitor.visit(ctx.regList());
        regList.forEach((registerName) -> {
            if("PC".equalsIgnoreCase(registerName)) {
                stack.push(pc.getValue());
            }
            else if("LR".equalsIgnoreCase(registerName)) {
                stack.push(lr.getValue());
            }
            else {
                stack.push(registerFile.getValue(registerName));
            }
        });

        return null;
    }

    @Override
    public Void visitPop(ProcessorParser.PopContext ctx) {
        List<String> regList = regListVisitor.visit(ctx.regList());
        regList.forEach((registerName) -> {
            if("PC".equalsIgnoreCase(registerName)) {
                pc.setValue(stack.pop());
            }
            else if("LR".equalsIgnoreCase(registerName)) {
                lr.setValue(stack.pop());
            }
            else {
                registerFile.setValue(registerName, stack.pop());
            }
        });
        return null;
    }

    private static class RegListVisitor extends ProcessorBaseVisitor<List<String>> {
        @Override
        public List<String> visitRegList(ProcessorParser.RegListContext ctx) {
            List<String> regList = new ArrayList<>(10);
            if(!ctx.REGISTER().isEmpty()) {
                ctx.REGISTER().forEach((terminalNode) -> {
                    regList.add(terminalNode.getText());
                });
            }
            if(!ctx.PC().isEmpty()) {
                ctx.PC().forEach((terminalNode) -> {
                    regList.add(terminalNode.getText());
                });
            }
            if(!ctx.LR().isEmpty()) {
                ctx.LR().forEach((terminalNode) -> {
                    regList.add(terminalNode.getText());
                });
            }
            return regList;
        }
    }
}
