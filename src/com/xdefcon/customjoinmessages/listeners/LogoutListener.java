package com.xdefcon.customjoinmessages.listeners;


import com.xdefcon.customjoinmessages.CustomJoinMessages;
import com.xdefcon.customjoinmessages.utils.SoundUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashSet;
import java.util.Set;

public class LogoutListener implements Listener {

    private CustomJoinMessages plugin;
    private FileConfiguration config;

    public LogoutListener(CustomJoinMessages plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        if (!this.config.getBoolean("quit-event.enabled")) return;

        Player p = e.getPlayer();

        Set<String> customGroups = new HashSet();
        customGroups.addAll(config.getConfigurationSection("custom-groups").getKeys(false)); //FIXME

        for (int i = 1; i < customGroups.size() + 1; i++) {
            if (p.hasPermission(config.getString("custom-groups." + i + ".permission"))) {
                plugin.getServer().broadcastMessage(config.getString("custom-groups." + i + ".quit-message")
                        .replace("&", "§").replace("{nick}", p.getName()));
                if (!config.getString("custom-groups." + i + ".quit-sound").equalsIgnoreCase("NONE")) {
                    SoundUtil.playSound(p, config.getString("custom-groups." + i + ".quit-sound"));
                }
                return;
            }
        }
        if (!config.getString("quit-event.default-message").equalsIgnoreCase("none")) {
            plugin.getServer().broadcastMessage(config.getString("quit-event.default-message")
                    .replace("&", "§").replace("{nick}", p.getName()));
            if (!config.getString("quit-event.default-sound").equalsIgnoreCase("NONE")) {
                SoundUtil.playSound(p, config.getString("quit-event.default-sound"));
            }
        }

    }
}
