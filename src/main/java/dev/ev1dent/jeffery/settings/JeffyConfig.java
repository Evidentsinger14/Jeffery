package dev.ev1dent.jeffery.settings;

import dev.ev1dent.jeffery.JefferyBrains;

public class JeffyConfig {
    public static String getAPIKey(String Platform){
        return JefferyBrains.config().get(Platform + "_APIKEY");
    }

    public static String getPlatformURL(String Platform, String Identifier){
        String url = "";
        switch (Platform) {
            case "Pterodactyl" -> {
                url = String.format("https://panel.ev1dent.dev/api/client/servers/%s", Identifier);
            }
            default -> url = null;
        }
        return url;

    }


}
