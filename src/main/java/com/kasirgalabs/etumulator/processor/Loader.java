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

import com.kasirgalabs.arm.ArmLexer;
import com.kasirgalabs.arm.ArmParser;
import com.kasirgalabs.etumulator.pattern.Registry;
import com.kasirgalabs.etumulator.register.RegisterFile;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Loader {
    private Processor processor;
    private ANTLRErrorListener listener;

    public Loader(Processor processor) {
        this.processor = processor;
        listener = new ErrorListener();
    }

    public void setErrorListener(ANTLRErrorListener listener) {
        this.listener = listener;
    }

    public void loadAndRun(char[] code) {
        ANTLRInputStream in = new ANTLRInputStream(code, code.length);
        ArmLexer lexer = new ArmLexer(in);
        lexer.removeErrorListeners();
        lexer.addErrorListener(listener);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArmParser parser = new ArmParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(listener);
        ArmParser.ProgContext tree = parser.prog();
        if(parser.getNumberOfSyntaxErrors() != 0) {
            return;
        }
        Registry.put(RegisterFile.class, new RegisterFile());
        ParseTreeWalker.DEFAULT.walk(processor, tree);
    }
}
