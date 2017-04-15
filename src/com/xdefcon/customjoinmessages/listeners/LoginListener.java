package com.xdefcon.customjoinmessages.listeners;


import com.xdefcon.customjoinmessages.CustomJoinMessages;
import com.xdefcon.customjoinmessages.utils.SoundUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashSet;
import java.util.Set;

public class LoginListener implements Listener {

    private CustomJoinMessages plugin;
    private FileConfiguration config;

    public LoginListener(CustomJoinMessages plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (!this.config.getBoolean("join-event.enabled")) return;

        e.setJoinMessage(null);
        Player p = e.getPlayer();

        Set<String> customGroups = new HashSet();
        customGroups.addAll(config.getConfigurationSection("custom-groups").getKeys(false));

        for (int i = 1; i < customGroups.size() + 1; i++) {
            if (p.hasPermission(config.getString("custom-groups." + i + ".permission"))) {
                plugin.getServer().broadcastMessage(config.getString("custom-groups." + i + ".join-message").replace("&", "§").replace("{nick}", p.getName()));
                if (!config.getString("custom-groups." + i + ".join-sound").equalsIgnoreCase("NONE")) {
                    SoundUtil.playSound(p, config.getString("custom-groups." + i + ".join-sound"));
                }
                return;
            }
        }
        if (!config.getString("join-event.default-message").equalsIgnoreCase("none")) {
            plugin.getServer().broadcastMessage(config.getString("join-event.default-message").replace("&", "§").replace("{nick}", p.getName()));
            if (!config.getString("join-event.default-sound").equalsIgnoreCase("NONE")) {
                SoundUtil.playSound(p, config.getString("join-event.default-sound"));
            }
        }

    }
}
