package com.example.springboot.cruddemo.swinggui.endpoints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostDataOperation {
    public static String postDataToApi(String jsonData) {
        String apiUrl = "http://localhost:8080/api/employees"; // Replace with your API endpoint URL
        String res = "null";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");

            // Enable input/output streams and set other properties
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Write the JSON data to the output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response from the server
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                res = response.toString();
                System.out.println(res);

                reader.close();
            } else {
                // Handle non-OK response codes if needed
                System.err.println("Failed to post data. HTTP response code: " + responseCode);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return res;
    }
}
