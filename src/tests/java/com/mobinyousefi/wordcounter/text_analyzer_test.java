/*
 * Project: Word Counter (Swing, Java)
 * File: TextAnalyzerTest.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Created: 2025-11-15
 * Updated: 2025-11-15
 * License: MIT License (see LICENSE file for details)
 * ==========================================================================================================
 */
package com.mobinyousefi.wordcounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Basic unit tests for {@link TextAnalyzer}.
 */
public class TextAnalyzerTest {

    @Test
    void analyze_emptyText_returnsZeroStats() {
        TextStatistics stats = TextAnalyzer.analyze("");
        Assertions.assertEquals(0, stats.wordCount());
        Assertions.assertEquals(0, stats.charCount());
        Assertions.assertEquals(0, stats.charCountWithoutSpaces());
        Assertions.assertEquals(0, stats.lineCount());
        Assertions.assertEquals(0.0, stats.averageWordLength(), 1e-9);
    }

    @Test
    void analyze_singleWord_countsCorrectly() {
        TextStatistics stats = TextAnalyzer.analyze("Hello");
        Assertions.assertEquals(1, stats.wordCount());
        Assertions.assertEquals(5, stats.charCount());
        Assertions.assertEquals(5, stats.charCountWithoutSpaces());
        Assertions.assertEquals(1, stats.lineCount());
        Assertions.assertEquals(5.0, stats.averageWordLength(), 1e-9);
    }

    @Test
    void analyze_multipleWordsAndLines_countsCorrectly() {
        String text = "Hello world\nThis is a test.";
        TextStatistics stats = TextAnalyzer.analyze(text);

        // Words: Hello(5), world(5), This(4), is(2), a(1), test.(5)
        Assertions.assertEquals(6, stats.wordCount());
        Assertions.assertEquals(text.length(), stats.charCount());
        Assertions.assertEquals(5 + 5 + 4 + 2 + 1 + 5, stats.charCountWithoutSpaces());
        Assertions.assertEquals(2, stats.lineCount());
    }
}
