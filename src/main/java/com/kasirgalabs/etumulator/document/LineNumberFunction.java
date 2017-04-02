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
package com.kasirgalabs.etumulator.document;

import java.util.function.IntFunction;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import org.fxmisc.richtext.StyledTextArea;
import org.reactfx.collection.LiveList;
import org.reactfx.value.Val;

public class LineNumberFunction implements IntFunction<Node> {
    private static final Insets DEFAULT_INSETS = new Insets(0.0, 5.0, 0.0, 5.0);
    private static final Background DEFAULT_BACKGROUND = new Background(new BackgroundFill(Color
            .web("#ddd"), null, null));
    private final Val<Integer> paragraphs;
    private final IntFunction<String> format;

    private LineNumberFunction(StyledTextArea<?, ?> area, IntFunction<String> format) {
        paragraphs = LiveList.sizeOf(area.getParagraphs());
        this.format = format;
    }

    public static IntFunction<Node> applyTo(StyledTextArea<?, ?> area) {
        return new LineNumberFunction(area, digits -> "%" + digits + "d");
    }

    @Override
    public Node apply(int idx) {
        Val<String> formatted = paragraphs.map(n -> format(idx + 1, n));
        Label lineNo = new Label();
        lineNo.setCursor(Cursor.HAND);
        lineNo.setBackground(DEFAULT_BACKGROUND);
        lineNo.setPadding(DEFAULT_INSETS);
        lineNo.getStyleClass().add("lineno");
        lineNo.textProperty().bind(formatted.conditionOnShowing(lineNo));
        return lineNo;
    }

    private String format(int x, int max) {
        int digits = (int) Math.floor(Math.log10(max)) + 1;
        return String.format(format.apply(digits), x);
    }
}
