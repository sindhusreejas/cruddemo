package com.example.springboot.cruddemo.swinggui;

import com.example.springboot.cruddemo.swinggui.endpoints.PostDataOperation;
import com.example.springboot.cruddemo.swinggui.endpoints.PutDataOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostDataHandler extends  JFrame{
    public static void handlePostData(CrudGUI parentFrame) {
        // Implement logic to post data (e.g., create new data)
        JFrame viewFrame1 = new JFrame("Post Data");
        viewFrame1.setSize(500, 500);

        // Center the view frame with respect to the parent frame
        int x = parentFrame.getX() + (parentFrame.getWidth() - viewFrame1.getWidth()) / 2;
        int y = parentFrame.getY() + (parentFrame.getHeight() - viewFrame1.getHeight()) / 2;
        viewFrame1.setLocation(x, y);

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

        viewFrame1.setLayout(new BorderLayout());
        viewFrame1.add(new JScrollPane(jsonTextArea), BorderLayout.CENTER);
        viewFrame1.add(submitButton, BorderLayout.SOUTH);
        viewFrame1.setVisible(true);
    }

    private static void handleJsonData(String jsonData) {
        // Implement your logic to process or send the JSON data
        PostDataOperation.postDataToApi(jsonData);
        System.out.println("Posted JSON data: " + jsonData);
    }
       // JOptionPane.showMessageDialog(null, "Post Data Button Clicked", "Post Data", JOptionPane.INFORMATION_MESSAGE);
    }
