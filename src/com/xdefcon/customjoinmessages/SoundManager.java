package com.xdefcon.customjoinmessages;


import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundManager {
    private static float volume;
    private static float pitch;
    private static Config config;
    private static String soundMode;
    private static boolean enabled;

    public SoundManager(){
        config = new Config();
        enabled = config.getConfig().getBoolean("sound-manager.enabled");
        volume = (float) config.getConfig().getDouble("sound-manager.volume");
        pitch = (float) config.getConfig().getDouble("sound-manager.pitch");
        soundMode = config.getConfig().getString("sound-manager.sound-mode");
    }

    public void playSound(Player p, String soundType){
        if (!enabled) return;
        if (soundMode.equalsIgnoreCase("everybody")) {
            for(Player pl : Bukkit.getOnlinePlayers()){
                playSoundOnPlayer(pl, soundType);
            }
        } else if (soundMode.equalsIgnoreCase("single")) {
            playSoundOnPlayer(p, soundType);
        }
    }

    public void playSoundOnPlayer(Player p, String soundType) {
        p.playSound(p.getLocation(), Sound.valueOf(soundType), volume, pitch);
    }
}
