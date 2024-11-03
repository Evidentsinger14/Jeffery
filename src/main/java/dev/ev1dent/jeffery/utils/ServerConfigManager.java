package dev.ev1dent.jeffery.utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ServerConfigManager {
    private final String configFile;
    private final Gson gson;
    private Map<String, ServerConfig> configData;

    public static class ServerConfig {
        private final String serverId;
        private final String roleId;

        public ServerConfig(String serverId, String roleId) {
            this.serverId = serverId;
            this.roleId = roleId;
        }

        public String getServerId() {
            return serverId;
        }

        public String getRoleId() {
            return roleId;
        }
    }

    public ServerConfigManager() {
        this.configFile = "servers.json";
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.loadConfig();
    }

    private void loadConfig() {
        try {
            File file = new File(configFile);
            if (!file.exists()) {
                configData = new HashMap<>();
                saveConfig();
                return;
            }

            Reader reader = new FileReader(file);
            Type type = new TypeToken<HashMap<String, ServerConfig>>(){}.getType();
            configData = gson.fromJson(reader, type);
            reader.close();

            if (configData == null) {
                configData = new HashMap<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            configData = new HashMap<>();
        }
    }

    // Save the configuration to the JSON file
    private void saveConfig() {
        try {
            Writer writer = new FileWriter(configFile);
            gson.toJson(configData, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addServerConfig(String serverName, String serverId, String roleId) {
        ServerConfig config = new ServerConfig(serverId, roleId);
        configData.put(serverName, config);
        saveConfig();
    }

    public void removeServerConfig(String serverName) {
        if (configData.remove(serverName) != null) {
            saveConfig();
        }
    }

    public String getServerId(String serverName) {
        ServerConfig config = configData.get(serverName);
        return config != null ? config.getServerId() : null;
    }

    public String getRoleId(String serverName) {
        ServerConfig config = configData.get(serverName);
        return config != null ? config.getRoleId() : null;
    }

    public ServerConfig getServerConfig(String serverName) {
        return configData.get(serverName);
    }

    public boolean hasServer(String serverName) {
        return configData.containsKey(serverName);
    }

    public Set<String> getAllServerNames() {
        return configData.keySet();
    }
}