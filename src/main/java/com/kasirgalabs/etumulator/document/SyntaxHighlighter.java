package com.kasirgalabs.etumulator.document;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Parent;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

public class SyntaxHighlighter {
    private static final String[] KEYWORDS = new String[]{
        "add", "adds", "adc", "adcs", "sub", "subs", "sbc", "sbcs",
        "rsb", "rsbs", "rsc", "rscs", "mul", "muls", "mla", "mlas",
        "mls", "sdiv", "udiv", "mov", "movs", "mvn", "mvns", "movt",
        "asr", "asrs", "lsl", "lsls", "lsr", "lsrs", "ror", "rors",
        "rrx", "rrxs", "cmp", "cmn", "tst", "teq", "and", "ands",
        "eor", "eors", "orr", "orrs", "orn", "orns", "bic", "bics",
        "b", "beq", "bne", "bcs", "bhs", "bcc", "blo", "bmi",
        "bpl", "bvs", "bvc", "bhi", "bls", "bge", "blt", "bgt",
        "ble", "bal", "bl", "ldr", "ldrb", "ldrsb", "ldrh", "ldrsh",
        "str", "strb", "strsb", "strh", "strsh", "push", "pop"
    };
    private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";
    private static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
            + "|(?<STRING>" + STRING_PATTERN + ")"
            + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
    );
    private final Parent parent;

    public SyntaxHighlighter(Parent parent) {
        this.parent = parent;
    }

    public StyleSpans<Collection<String>> highlight(String text) {
        parent.getStylesheets().add(SyntaxHighlighter.class.getClassLoader()
                .getResource("arm-syntax-highlight.css").toExternalForm());
        Matcher matcher = PATTERN.matcher(text);
        int lastKeyword = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass
                    = matcher.group("KEYWORD") != null ? "keyword"
                    : matcher.group("STRING") != null ? "string"
                    : matcher.group("COMMENT") != null ? "comment"
                    : null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKeyword);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKeyword = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKeyword);
        return spansBuilder.create();
    }
}
