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

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kasirgalabs.etumulator.util.BaseDispatcher;
import com.kasirgalabs.etumulator.util.GUISafeDispatcher;

@Singleton
public class GUISafeProcessorUnits extends BaseProcessorUnits {
    public GUISafeProcessorUnits() {
        super(new RegisterFile(new GUISafeDispatcher()),
                new CPSR(new GUISafeDispatcher()),
                new Stack(new GUISafeDispatcher()),
                new Memory(new GUISafeDispatcher()),
                new UART(null, new BaseDispatcher()));
        super.getUART().setRegisterFile(super.getRegisterFile());
    }

    @Inject
    public GUISafeProcessorUnits(RegisterFile registerFile, CPSR cpsr, Stack stack, Memory memory,
            UART uart) {
        super(registerFile, cpsr, stack, memory, uart);
    }
}
