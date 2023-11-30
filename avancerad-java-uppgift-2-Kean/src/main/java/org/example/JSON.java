package org.example;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSON {

    // Laddar data från json filen till JTable
    public static void loadJSON(JTable table, String filePath) throws IOException {
        // skapar en array
        JsonArray jsonArray = readJsonArrayFromFile(filePath);

        // kollar ifall den är tom
        if (jsonArray.isEmpty()) {
            throw new IOException("Filen är tom");
        }

        // skapar tablemodel från arrayen
        DefaultTableModel tableModel = createTableModelFromJsonArray(jsonArray);

        // Sätter model i table och gör den sorterbar
        table.setModel(tableModel);
        table.setAutoCreateRowSorter(true);
    }

    // läser arrayen från filen
    private static JsonArray readJsonArrayFromFile(String filePath) throws IOException {
        try (FileReader fileReader = new FileReader(filePath)) {
            return Json.parse(fileReader).asArray();
        }
    }

    // skapar tablemodel från arrayen
    private static DefaultTableModel createTableModelFromJsonArray(JsonArray jsonArray) {
        List<String> columns = new ArrayList<>();

        // tar första raden för kolumnnamnen
        JsonObject firstRow = jsonArray.get(0).asObject();
        firstRow.names().forEach(columns::add);

        // skapar en arraylista för att hålla raderna
        List<Object[]> rowArray = new ArrayList<>();

        // stoppar i alla rader i listan
        for (JsonValue value : jsonArray) {
            JsonObject jsonObject = value.asObject();
            Object[] row = new Object[columns.size()];
            int i = 0;
            for (String column : columns) {
                row[i++] = jsonObject.get(column).toString();
            }
            rowArray.add(row);
        }

        // skapar tablemodel med kolumnnamnen och gör dom non editable likt i csvklassen
        return new DefaultTableModel(rowArray.toArray(new Object[0][]), columns.toArray()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }
}
