package com.xdefcon.customjoinmessages;


import com.xdefcon.customjoinmessages.bstats.Metrics;
import com.xdefcon.customjoinmessages.listeners.LoginListener;
import com.xdefcon.customjoinmessages.listeners.LogoutListener;
import org.bukkit.plugin.java.JavaPlugin;


public class CustomJoinMessages extends JavaPlugin {
    private static CustomJoinMessages instance;

    public static CustomJoinMessages getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new LoginListener(this), this);
        this.getServer().getPluginManager().registerEvents(new LogoutListener(this), this);

        if (this.getConfig().getBoolean("console-intro-message")) {
            getLogger().info("Heyo, thanks for using CustomJoinMessages! If you need help with this plugin send a PM to xDefcon on SpigotMC.org. You can disable this message in the config.");
        }
        if (!this.getConfig().getBoolean("join-event.enabled")) {
            getLogger().info("The join-event is disabled. This means that no messages about joins will be displayed.");
        }
        if (!this.getConfig().getBoolean("quit-event.enabled")) {
            getLogger().info("The quit-event is disabled. This means that no messages about quits will be displayed.");
        }
        if (!this.getConfig().getBoolean("sound-manager.enabled")) {
            getLogger().info("The sound-manager is disabled. This means that no sounds will be played.");
        }

        Metrics metrics = new Metrics(this);
    }

    @Override
    public void onDisable() {
        instance = null;
        //todo?
    }

    public void reload() {
        this.getLogger().info("Reloading the plugin...");
        this.getServer().getScheduler().cancelAllTasks();
        this.reloadConfig();
        this.getLogger().info("Plugin reloaded.");
    }
}
