package com.xdefcon.customjoinmessages;


import com.xdefcon.customjoinmessages.listeners.LoginListener;
import com.xdefcon.customjoinmessages.listeners.LogoutListener;
import org.bukkit.plugin.java.JavaPlugin;


public class CustomJoinMessages extends JavaPlugin {

    public void onEnable() {

        saveDefaultConfig();
        Config config = new Config(true);

        getServer().getPluginManager().registerEvents(new LoginListener(), this);
        getServer().getPluginManager().registerEvents(new LogoutListener(), this);

        if (config.getConfig().getBoolean("console-intro-message")) {
            getLogger().info("Heyo, thanks for using CustomJoinMessages! If you need help with this plugin send a PM to xDefcon on SpigotMC.org. You can disable this message in the config.");
        }
        if (!config.getConfig().getBoolean("join-event.enabled")) {
            getLogger().info("The join-event is disabled. This means that no messages about joins will be displayed.");
        }
        if (!config.getConfig().getBoolean("quit-event.enabled")) {
            getLogger().info("The quit-event is disabled. This means that no messages about quits will be displayed.");
        }
        if (!config.getConfig().getBoolean("sound-manager.enabled")) {
            getLogger().info("The sound-manager is disabled. This means that no sounds will be played.");
        }
    }
}
