package com.xdefcon.customjoinmessages.listeners;


import com.xdefcon.customjoinmessages.Config;
import com.xdefcon.customjoinmessages.SoundManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashSet;
import java.util.Set;

public class LogoutListener implements Listener {

    private Config c;
    private FileConfiguration config;

    public LogoutListener(){
        this.c = new Config();
        this.config = c.getConfig();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (!this.config.getBoolean("quit-event.enabled")) return;

        Player p = e.getPlayer();

        Set<String> customGroups = new HashSet();
        customGroups.addAll(config.getConfigurationSection("custom-groups").getKeys(false));

        for (int i = 1; i < customGroups.size() + 1; i++) {
            if (p.hasPermission(config.getString("custom-groups." + i + ".permission"))) {
                Bukkit.broadcastMessage(config.getString("custom-groups." + i + ".quit-message").replace("&", "§").replace("{nick}", p.getName()));
                if (!config.getString("custom-groups." + i + ".quit-sound").equalsIgnoreCase("NONE")) {
                    SoundManager sm = new SoundManager();
                    sm.playSound(p, config.getString("custom-groups." + i + ".quit-sound"));
                }
                return;
            }
        }
        if (!config.getString("quit-event.default-message").equalsIgnoreCase("none")) {
            Bukkit.broadcastMessage(config.getString("quit-event.default-message").replace("&", "§").replace("{nick}", p.getName()));
            if (!config.getString("quit-event.default-sound").equalsIgnoreCase("NONE")) {
                SoundManager sm = new SoundManager();
                sm.playSound(p, config.getString("quit-event.default-sound"));
            }
        }

    }
}
