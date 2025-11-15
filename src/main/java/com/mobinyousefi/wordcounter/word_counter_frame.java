/*
 * Project: Word Counter (Swing, Java)
 * File: WordCounterFrame.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Created: 2025-11-15
 * Updated: 2025-11-15
 * License: MIT License (see LICENSE file for details)
 * ==========================================================================================================
 */
package com.mobinyousefi.wordcounter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * Main Swing window for the Word Counter application.
 */
public class WordCounterFrame extends JFrame {

    private final JTextArea inputArea;
    private final JLabel wordLabel;
    private final JLabel charLabel;
    private final JLabel charNoSpaceLabel;
    private final JLabel lineLabel;
    private final JLabel avgWordLenLabel;

    public WordCounterFrame() {
        super("Word Counter - Mobin Yousefi");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
        setMinimumSize(new Dimension(720, 480));
        setLocationRelativeTo(null);

        // Attempt to use the system look and feel for a more native experience
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Fallback to default LAF
        }

        // Text input area
        inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(inputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Input Text"));
        add(scrollPane, BorderLayout.CENTER);

        // Toolbar with basic actions
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> inputArea.setText(""));

        JButton sampleButton = new JButton("Insert Sample");
        sampleButton.addActionListener(e -> inputArea.setText("This is a simple sample sentence to demonstrate the word counter tool."));

        JButton copyStatsButton = new JButton("Copy Stats");
        copyStatsButton.addActionListener(e -> copyStatisticsToClipboard());

        toolBar.add(clearButton);
        toolBar.add(sampleButton);
        toolBar.addSeparator();
        toolBar.add(copyStatsButton);

        add(toolBar, BorderLayout.NORTH);

        // Status panel with aggregated statistics
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 4));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

        wordLabel = new JLabel("Words: 0");
        charLabel = new JLabel("Characters (with spaces): 0");
        charNoSpaceLabel = new JLabel("Characters (no spaces): 0");
        lineLabel = new JLabel("Lines: 0");
        avgWordLenLabel = new JLabel("Avg. word length: 0.00");

        statusPanel.add(wordLabel);
        statusPanel.add(charLabel);
        statusPanel.add(charNoSpaceLabel);
        statusPanel.add(lineLabel);
        statusPanel.add(avgWordLenLabel);

        add(statusPanel, BorderLayout.SOUTH);

        // Live statistics updates when the document changes
        inputArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateStatistics();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateStatistics();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateStatistics();
            }
        });

        // Initial statistics
        updateStatistics();

        pack();
    }

    private void updateStatistics() {
        String text = inputArea.getText();
        TextStatistics stats = TextAnalyzer.analyze(text);

        wordLabel.setText("Words: " + stats.wordCount());
        charLabel.setText("Characters (with spaces): " + stats.charCount());
        charNoSpaceLabel.setText("Characters (no spaces): " + stats.charCountWithoutSpaces());
        lineLabel.setText("Lines: " + stats.lineCount());
        avgWordLenLabel.setText(String.format("Avg. word length: %.2f", stats.averageWordLength()));
    }

    private void copyStatisticsToClipboard() {
        String text = inputArea.getText();
        TextStatistics stats = TextAnalyzer.analyze(text);

        StringBuilder sb = new StringBuilder();
        sb.append("Word Counter Statistics\n");
        sb.append("========================\n");
        sb.append("Words: ").append(stats.wordCount()).append('\n');
        sb.append("Characters (with spaces): ").append(stats.charCount()).append('\n');
        sb.append("Characters (no spaces): ").append(stats.charCountWithoutSpaces()).append('\n');
        sb.append("Lines: ").append(stats.lineCount()).append('\n');
        sb.append(String.format("Average word length: %.2f\n", stats.averageWordLength()));

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(sb.toString()), null);
    }
}
