package dev.ev1dent.jeffery.utils;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PteroUtil {
    public static void postToPanel(String input, String ServerID, String APIKEY) {
        try {
            String url = "https://panel.ev1dent.dev/api/client/servers/" + ServerID + "/power";

            // Create connection
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + APIKEY);
            connection.setDoOutput(true);

            // Write the JSON data to the output stream
            try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream())) {
                outputStreamWriter.write(input);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode != 204) {
                System.out.println("Response Code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
