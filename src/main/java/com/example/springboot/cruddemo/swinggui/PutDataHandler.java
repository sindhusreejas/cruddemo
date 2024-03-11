package com.example.springboot.cruddemo.swinggui;

import com.example.springboot.cruddemo.swinggui.endpoints.PutDataOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.apache.el.lang.ELArithmetic.add;

public class PutDataHandler extends JFrame {

    public static void handlePutData(CrudGUI parentFrame) {
        // Implement logic to put data (e.g., update existing data)
        JFrame viewFrame = new JFrame("Put Data");
        viewFrame.setSize(500, 500);

        // Center the view frame with respect to the parent frame
        int x = parentFrame.getX() + (parentFrame.getWidth() - viewFrame.getWidth()) / 2;
        int y = parentFrame.getY() + (parentFrame.getHeight() - viewFrame.getHeight()) / 2;
        viewFrame.setLocation(x, y);

        JTextArea jsonTextArea = new JTextArea();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve JSON data from the JTextArea
                String jsonData = jsonTextArea.getText();

                // Pass the JSON data to your logic or operation
                handleJsonData(jsonData);
            }
        });

        viewFrame.setLayout(new BorderLayout());
        viewFrame.add(new JScrollPane(jsonTextArea), BorderLayout.CENTER);
        viewFrame.add(submitButton, BorderLayout.SOUTH);
        viewFrame.setVisible(true);
    }

    private static void handleJsonData(String jsonData) {
        // Implement your logic to process or send the JSON data
        PutDataOperation.putDataToApi(jsonData);
        System.out.println("Received JSON data: " + jsonData);
    }
}
