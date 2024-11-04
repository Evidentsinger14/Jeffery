package dev.ev1dent.jeffery.utils;

import dev.ev1dent.jeffery.JefferyBrains;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PteroUtil {

    static String APIKEY = JefferyBrains.config().get("PTERO_APIKEY");
    public static void postToPanel(String input, String ServerID) {
        try {
            String url = "https://panel.ev1dent.dev/api/client/servers/" + ServerID + "/power";

            HttpURLConnection connection = getHttpURLConnection(input, url, APIKEY);

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

    private static @NotNull HttpURLConnection getHttpURLConnection(String input, String url, String APIKEY) throws IOException {
        String status = "{\"signal\": \"%SIGNAL%\"}".replace("%SIGNAL%", input);
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + APIKEY);
        connection.setDoOutput(true);

        // Write the JSON data to the output stream
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream())) {
            outputStreamWriter.write(status);
        }
        return connection;
    }
}
