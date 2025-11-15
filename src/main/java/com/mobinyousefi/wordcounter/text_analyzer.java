/*
 * Project: Word Counter (Swing, Java)
 * File: TextAnalyzer.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Created: 2025-11-15
 * Updated: 2025-11-15
 * License: MIT License (see LICENSE file for details)
 * ==========================================================================================================
 */
package com.mobinyousefi.wordcounter;

/**
 * Pure, side-effect free utility for turning raw text into {@link TextStatistics}.
 */
public final class TextAnalyzer {

    private TextAnalyzer() {
        // Utility class: prevent instantiation.
    }

    /**
     * Analyze the given {@code text} and compute aggregated statistics.
     *
     * @param text raw input text (may be {@code null})
     * @return aggregated statistics; never {@code null}
     */
    public static TextStatistics analyze(String text) {
        if (text == null || text.isEmpty()) {
            return TextStatistics.of(0, 0, 0, 0, 0.0);
        }

        int charCount = text.length();
        int charCountWithoutSpaces = 0;
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isWhitespace(text.charAt(i))) {
                charCountWithoutSpaces++;
            }
        }

        // Line count: treat different OS line separators uniformly
        int lineCount = computeLineCount(text);

        // Word count and average word length based on a simple whitespace split
        String trimmed = text.trim();
        if (trimmed.isEmpty()) {
            return TextStatistics.of(0, charCount, charCountWithoutSpaces, lineCount, 0.0);
        }

        String[] tokens = trimmed.split("\\s+");
        int wordCount = 0;
        int totalWordChars = 0;
        for (String token : tokens) {
            if (!token.isBlank()) {
                wordCount++;
                totalWordChars += token.length();
            }
        }

        double avgWordLength = (wordCount > 0) ? (double) totalWordChars / wordCount : 0.0;

        return TextStatistics.of(wordCount, charCount, charCountWithoutSpaces, lineCount, avgWordLength);
    }

    private static int computeLineCount(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        // Split on any Unicode line-break sequence (Java 8+): \R
        String[] lines = text.split("\\R", -1);

        // If the text ends with a newline, split("\\R", -1) will include a trailing empty token;
        // we still consider that a separate (empty) line, which is usually what the user expects.
        int count = 0;
        for (String ignored : lines) {
            count++;
        }
        return count;
    }
}
