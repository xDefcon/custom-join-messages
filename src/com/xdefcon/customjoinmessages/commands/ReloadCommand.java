package com.xdefcon.customjoinmessages.commands;


import com.xdefcon.customjoinmessages.CustomJoinMessages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {
    private CustomJoinMessages plugin;

    public ReloadCommand(CustomJoinMessages plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command c, String label, String[] args) {
        if (!sender.hasPermission("customjoinmessages.reload")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("permission-system.no-perm-message")));
            return true;
        }

        plugin.reload();
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.GREEN + "Plugin reloaded.");
        }
        return true;
    }
}
