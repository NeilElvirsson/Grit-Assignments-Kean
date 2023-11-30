package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import com.opencsv.exceptions.CsvException;

public class Main {

    public static void GUI() {
        JFrame frame = new JFrame("Data Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // JTable för att visa datan
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // panel för knapparna
        JPanel buttons = new JPanel();
        JButton csvButton = new JButton("Load csv file");
        JButton jsonButton = new JButton("Load json file"); // funkar inte än

        // File chooser för att välja csv filer
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("src")); // börjar i source mappen
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV Files", "csv"));

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
