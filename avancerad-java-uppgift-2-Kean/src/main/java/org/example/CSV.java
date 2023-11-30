package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSV {

    // laddar in data från csv till Jtable
    public static void loadCSV(JTable table, String filePath) throws IOException, CsvException {
        // skapar csv reader
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).build()) {
            // läser alla rows i csvn
            List<String[]> allRows = reader.readAll();

            // kollar ifall filen är tom
            if (allRows.isEmpty()) {
                throw new IOException("The file is empty");
            }

            // tar första raden för att få namnen på columnerna
            String[] columns = allRows.get(0);

            // tar bort första raden
            allRows.remove(0);

            // skapar tablemodel med kolumnnamnen och gör dom non editable
            DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    // gör den non editable
                    return false;
                }
            };

            // lägger till rows till tableModel
            allRows.forEach(tableModel::addRow);

            // sätter model till table och gör det möjligt att sortera
            table.setModel(tableModel);
            table.setAutoCreateRowSorter(true);
        }
    }
}
