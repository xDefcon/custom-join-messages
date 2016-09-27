package com.xdefcon.customjoinmessages;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Config {

    private static FileConfiguration config;

    public Config(boolean load) {
        loadConfig();
    }

    public Config() {
    }

    private void loadConfig() {
        File file = new File("plugins/CustomJoinMessages/config.yml");
        YamlConfiguration yc = new YamlConfiguration();
        try {
            yc.load(file);
            config = YamlConfiguration.loadConfiguration(file);
            Bukkit.getLogger().info("Configuration file loaded.");
        } catch (FileNotFoundException ex) {
            Bukkit.getLogger().warning("Configuration file can not be found.");
        } catch (IOException ex) {
            Bukkit.getLogger().warning("Error while loading the configuration.");
            ex.printStackTrace();
        } catch (InvalidConfigurationException ex) {
            Bukkit.getLogger().warning("Error while loading the configuration, please check for some typos.");
            ex.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

}
