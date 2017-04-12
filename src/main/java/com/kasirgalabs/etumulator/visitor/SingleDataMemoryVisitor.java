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

import com.kasirgalabs.etumulator.processor.Memory;
import com.kasirgalabs.etumulator.processor.Memory.Size;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class SingleDataMemoryVisitor extends ProcessorBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final Memory memory;
    private final RegisterVisitor registerVisitor;
    private final NumberVisitor numberVisitor;
    private final MemoryAddressVisitor memoryAddressVisitor;

    public SingleDataMemoryVisitor(RegisterFile registerFile, Memory memory) {
        this.registerFile = registerFile;
        this.memory = memory;
        registerVisitor = new RegisterVisitor();
        numberVisitor = new NumberVisitor();
        memoryAddressVisitor = new MemoryAddressVisitor(registerFile);
    }

    @Override
    public Void visitLdr(ProcessorParser.LdrContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        if(ctx.ASSIGN() != null) {
            registerFile.setValue(destRegister, numberVisitor.visit(ctx.number()));
        }
        else {
            int address = memoryAddressVisitor.visit(ctx.memoryAddress());
            registerFile.setValue(destRegister, memory.get(address, Size.WORD));
        }
        return null;
    }

    @Override
    public Void visitLdrb(ProcessorParser.LdrbContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        if(ctx.ASSIGN() != null) {
            registerFile.setValue(destRegister, (0x0000_00ff) & numberVisitor.visit(ctx.number()));
        }
        else {
            int address = memoryAddressVisitor.visit(ctx.memoryAddress());
            registerFile.setValue(destRegister, memory.get(address, Size.BYTE));
        }
        return null;
    }

    @Override
    public Void visitLdrh(ProcessorParser.LdrhContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        if(ctx.ASSIGN() != null) {
            registerFile.setValue(destRegister, (0x0000_ffff) & numberVisitor.visit(ctx.number()));
        }
        else {
            int address = memoryAddressVisitor.visit(ctx.memoryAddress());
            registerFile.setValue(destRegister, memory.get(address, Size.HALFWORD));
        }
        return null;
    }

    @Override
    public Void visitStr(ProcessorParser.StrContext ctx) {
        String srcRegister = registerVisitor.visit(ctx.rd());
        int address = memoryAddressVisitor.visit(ctx.memoryAddress());
        memory.set(address, registerFile.getValue(srcRegister), Size.WORD);
        return null;
    }

    @Override
    public Void visitStrb(ProcessorParser.StrbContext ctx) {
        String srcRegister = registerVisitor.visit(ctx.rd());
        int address = memoryAddressVisitor.visit(ctx.memoryAddress());
        memory.set(address, registerFile.getValue(srcRegister), Size.BYTE);
        return null;
    }

    @Override
    public Void visitStrh(ProcessorParser.StrhContext ctx) {
        String srcRegister = registerVisitor.visit(ctx.rd());
        int address = memoryAddressVisitor.visit(ctx.memoryAddress());
        memory.set(address, registerFile.getValue(srcRegister), Size.HALFWORD);
        return null;
    }
}
