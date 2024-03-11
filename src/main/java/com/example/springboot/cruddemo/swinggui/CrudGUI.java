package com.example.springboot.cruddemo.swinggui;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Component
public class CrudGUI extends JFrame {

    private JTextArea displayArea;

    public CrudGUI(){
        setTitle("Swing CRUD UI");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        displayArea = new JTextArea();
        JButton viewButton = new JButton("View");
        JButton putDataButton = new JButton("Put Data");
        JButton postDataButton = new JButton("Post Data");
        JButton deleteButton = new JButton("Delete");

        // Set layout
        setLayout(new BorderLayout());

        // Add components to the JFrame
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewButton);
        buttonPanel.add(putDataButton);
        buttonPanel.add(postDataButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Set up ActionListeners for the buttons
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewDataHandler.showViewFrame(CrudGUI.this);
            }
        });

        putDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PutDataHandler.handlePutData(CrudGUI.this);
            }
        });

        postDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PostDataHandler.handlePostData(CrudGUI.this);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteDataHandler.handleDeleteData(CrudGUI.this);
            }
        });
    }

}

