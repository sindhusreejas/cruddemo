package com.example.springboot.cruddemo.swinggui;

import com.example.springboot.cruddemo.swinggui.endpoints.fetchDataFromApi;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.springboot.cruddemo.swinggui.ViewDataHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.*;

public class ViewDataHandler {

    public static void showViewFrame(CrudGUI parentFrame) {
        // Open a new frame for viewing data
        JFrame viewFrame = new JFrame("View Data");
        viewFrame.setSize(500, 500);

        // Center the view frame with respect to the parent frame
        int x = parentFrame.getX() + (parentFrame.getWidth() - viewFrame.getWidth()) / 2;
        int y = parentFrame.getY() + (parentFrame.getHeight() - viewFrame.getHeight()) / 2;
        viewFrame.setLocation(x, y);

        //JTable dataTable = new JTable();

        // Call the fetchDataFromApi method to get data from the API
        fetchDataFromApi viewOperation = new fetchDataFromApi();
        String res = viewOperation.fetchDataFromApi();

        // Parse the JSON response and populate the table model
        List<Object[]> data = parseJsonResponse(res);

        // Create a JTable with a DefaultTableModel
        JTable dataTable = createTable(data);

        // Add the JTable to the frame
        viewFrame.add(new JScrollPane(dataTable));

        viewFrame.setVisible(true);
    }

    private static List<Object[]> parseJsonResponse(String jsonResponse) {
        List<Object[]> data = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            if (jsonNode != null && jsonNode.isArray()) {
                Iterator<JsonNode> elements = jsonNode.elements();

                while (elements.hasNext()) {
                    JsonNode entry = elements.next();
                    // Assuming the JSON structure has properties "field1", "field2", etc.
                    Object[] row = {
                            entry.path("fname").asText(),
                            entry.path("lname").asText(),
                            entry.path("email").asText(),
                            entry.path("id").asText(),
                            // Add more fields as needed
                    };
                    data.add(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error parsing JSON: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return data;
    }

    private static JTable createTable(List<Object[]> data) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Fname"); // Replace with actual column names
        tableModel.addColumn("Lname");
        tableModel.addColumn("Email");
        tableModel.addColumn("Id");
        // Add more column names as needed

        for (Object[] row : data) {
            tableModel.addRow(row);
        }

        return new JTable(tableModel);
    }
}
