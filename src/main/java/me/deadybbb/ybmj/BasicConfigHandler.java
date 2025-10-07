package me.deadybbb.ybmj;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BasicConfigHandler {
    private final PluginProvider plugin;
    private final String configFileName;
    private File configFile;
    protected YamlConfiguration config;

    public BasicConfigHandler(PluginProvider plugin, String configFileName) {
        this.plugin = plugin;
        this.configFileName = configFileName;
        this.configFile = new File(plugin.getDataFolder(), configFileName);
        this.config = null;
    }

    public boolean reloadConfig() {
        if (!configFile.exists()) {
            try{
                plugin.getDataFolder().mkdirs();
                configFile.createNewFile();
                plugin.logger.info("Created new " + configFileName);
            } catch (IOException e) {
                plugin.logger.warning("Failed to create " + configFileName + ": " + e);
            }
        }
        config = YamlConfiguration.loadConfiguration(configFile);
        return true;
    }

    public boolean saveConfig() {
        try {
            if (config != null) {
                config.save(configFile);
                plugin.logger.info("Successfully saved to " + configFileName);
                return true;
            }
        } catch (IOException e) {
            plugin.logger.warning("Failed to save " + configFileName + ": " + e);
            return false;
        }
        return false;
    }
}
