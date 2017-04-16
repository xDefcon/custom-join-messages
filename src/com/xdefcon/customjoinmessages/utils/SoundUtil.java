package com.xdefcon.customjoinmessages.utils;


import com.xdefcon.customjoinmessages.CustomJoinMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundUtil {
    private static CustomJoinMessages plugin = CustomJoinMessages.getInstance();

    private SoundUtil() {
    }

    public static void playSound(Player p, String soundType) {
        if (!plugin.getConfig().getBoolean("sound-manager.enabled")) return;

        if (plugin.getConfig().getString("sound-manager.sound-mode").equalsIgnoreCase("everybody")) {
            for(Player pl : Bukkit.getOnlinePlayers()){
                playSoundOnPlayer(pl, soundType);
            }
        } else if (plugin.getConfig().getString("sound-manager.sound-mode").equalsIgnoreCase("single")) {
            playSoundOnPlayer(p, soundType);
        }
    }

    private static void playSoundOnPlayer(Player p, String soundType) {
        float volume = (float) plugin.getConfig().getDouble("sound-manager.volume");
        float pitch = (float) plugin.getConfig().getDouble("sound-manager.pitch");

        try {
            p.playSound(p.getLocation(), Sound.valueOf(soundType), volume, pitch);
        } catch (IllegalArgumentException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[CustomJoinMessages] Can not play a sound. " +
                    "The specified sound-type is not available in your server version, please ensure to read carefully the config file.");
        }
    }
}
