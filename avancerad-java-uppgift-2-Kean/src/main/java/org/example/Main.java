package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import com.opencsv.exceptions.CsvException;

public class Main {

    public static void GUI() {
        JFrame frame = new JFrame("Best file reader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // JTable för att visa datan
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // panel för knapparna
        JPanel buttons = new JPanel();
        JButton csvButton = new JButton("Load csv file");
        JButton jsonButton = new JButton("Load json file");
        buttons.setBackground(Color.darkGray);

        // File chooser för att välja csv filer
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("src")); // starts in the source folder
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV Files", "csv"));
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JSON Files", "json"));


        // Action listener för CSV knappen
        csvButton.addActionListener(e -> {
            int fileChoice = fileChooser.showOpenDialog(frame);
            if (fileChoice == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    // laddar och visar csvfilen i table
                    CSV.loadCSV(table, file.getAbsolutePath());
                } catch (IOException | CsvException exception) {
                    // hanterar exceptions
                    exception.printStackTrace();
                }
            }
        });

        jsonButton.addActionListener(e -> {
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JSON Files", "json"));
            int fileChoice = fileChooser.showOpenDialog(frame);
            if (fileChoice == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    // laddar och displayar json i table
                    JSON.loadJSON(table, file.getAbsolutePath());
                } catch (IOException exception) {
                    // hanterar exceptions - var tvungen att skriva det på ett lite nnorlunda sätt än med json
                    JOptionPane.showMessageDialog(frame, "Error loading JSON: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    exception.printStackTrace();
                }
            }
        });

        // lägger till knapparna till panelen
        buttons.add(csvButton);
        buttons.add(jsonButton);
        frame.add(buttons, BorderLayout.NORTH);

        // framen startas i mitten av skärmen och anpassas efter storlek
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        GUI();
    }
}
