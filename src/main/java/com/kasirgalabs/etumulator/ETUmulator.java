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
package com.kasirgalabs.etumulator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.kasirgalabs.etumulator.processor.CPSR;
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.RegisterFile;

public class ETUmulator {
    public static void main(String[] args) {
        Module module = new ETUmulatorModule();
        Injector injector = Guice.createInjector(module);
        Processor processor = injector.getInstance(Processor.class);
        RegisterFile registerFile = injector.getInstance(RegisterFile.class);
        CPSR cpsr = injector.getInstance(CPSR.class);
        cpsr.setCarry(true);
        processor.run("add r0, r1, #0x7ffffffe\n"
                + "adcs r2, r0, 1\n", null);
    }
}
