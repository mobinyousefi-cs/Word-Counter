/*
 * Project: Word Counter (Swing, Java)
 * File: TextStatistics.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Created: 2025-11-15
 * Updated: 2025-11-15
 * License: MIT License (see LICENSE file for details)
 * ==========================================================================================================
 */
package com.mobinyousefi.wordcounter;

/**
 * Immutable container for aggregated text statistics.
 */
public record TextStatistics(
        int wordCount,
        int charCount,
        int charCountWithoutSpaces,
        int lineCount,
        double averageWordLength
) {

    /**
     * Convenience factory method that normalizes edge cases (e.g., negative values).
     */
    public static TextStatistics of(int wordCount,
                                    int charCount,
                                    int charCountWithoutSpaces,
                                    int lineCount,
                                    double averageWordLength) {
        int safeWordCount = Math.max(0, wordCount);
        int safeCharCount = Math.max(0, charCount);
        int safeCharNoSpaceCount = Math.max(0, charCountWithoutSpaces);
        int safeLineCount = Math.max(0, lineCount);
        double safeAvgWordLength = Double.isFinite(averageWordLength) ? averageWordLength : 0.0;
        return new TextStatistics(safeWordCount, safeCharCount, safeCharNoSpaceCount, safeLineCount, safeAvgWordLength);
    }
}
