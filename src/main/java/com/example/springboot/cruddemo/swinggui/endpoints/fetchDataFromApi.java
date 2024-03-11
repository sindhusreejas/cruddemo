package com.example.springboot.cruddemo.swinggui.endpoints;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class fetchDataFromApi {

    public String fetchDataFromApi() {
        String apiUrl = "http://localhost:8080/api/employees"; // Replace with your API endpoint URL
        String res = "null";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                res=String.valueOf(response);
                System.out.println(res);

                reader.close();


            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return res;
    }

}
