/*
 * Project: Word Counter (Swing, Java)
 * File: WordCounterApp.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Created: 2025-11-15
 * Updated: 2025-11-15
 * License: MIT License (see LICENSE file for details)
 * ==========================================================================================================
 */
package com.mobinyousefi.wordcounter;

import javax.swing.SwingUtilities;

/**
 * Application entry point. Boots the Swing UI on the Event Dispatch Thread.
 */
public final class WordCounterApp {

    private WordCounterApp() {
        // Utility class: prevent instantiation.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordCounterFrame frame = new WordCounterFrame();
            frame.setVisible(true);
        });
    }
}
