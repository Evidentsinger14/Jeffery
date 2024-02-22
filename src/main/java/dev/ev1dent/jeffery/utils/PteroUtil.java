package dev.ev1dent.jeffery.utils;

import dev.ev1dent.jeffery.settings.JeffyConfig;
import org.w3c.dom.html.HTMLTableCaptionElement;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PteroUtil {
    public static void getFromPanel(String ServerID){
//        HttpURLConnection connection = getHttpURLConnection(JeffyConfig.getAPIKey(Platform), url)
    }
    public static void postToPanel(String input, String ServerID, String Platform) {
        try {
            String url = JeffyConfig.getPlatformURL(Platform, ServerID);

            // Create connection
            HttpURLConnection connection = getHttpURLConnection(JeffyConfig.getAPIKey(Platform), url);
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

    private static HttpURLConnection getHttpURLConnection(String APIKEY, String url) throws IOException {
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("Post");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", APIKEY);
        connection.setDoOutput(true);
        return connection;
    }
}
