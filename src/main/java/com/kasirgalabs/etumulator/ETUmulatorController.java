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

import com.kasirgalabs.arm.ArmBaseListener;
import com.kasirgalabs.arm.ArmLexer;
import com.kasirgalabs.arm.ArmParser;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ETUmulatorController implements Initializable, Console {
    @FXML
    private Button runButton;
    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textArea.appendText(String.valueOf((char) b));
            }
        };
        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }

    @Override
    public void write(Object o) {
    }

    @FXML
    private void runButtonOnAction(ActionEvent event) {
        String text = Registry.get(Document.class).getText();
        char[] code = (text + "\n").toCharArray();
        ANTLRInputStream in = new ANTLRInputStream(code, code.length);
        ArmLexer lexer = new ArmLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArmParser parser = new ArmParser(tokens);
        ArmParser.ProgContext tree = parser.prog();
        ParseTreeWalker.DEFAULT.walk(new ArmBaseListener(), tree);
    }
}
