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

public interface ProcessorUnits {
    void setRegisterFile(RegisterFile registerFile);

    RegisterFile getRegisterFile();

    void setAPSR(APSR apsr);

    APSR getAPSR();

    void setStack(Stack stack);

    Stack getStack();

    void setMemory(Memory memory);

    Memory getMemory();

    void setUART(UART uart);

    UART getUART();

    void setPC(PC pc);

    PC getPC();

    void setLR(LR lr);

    LR getLR();

    void reset();
}
