package com.inzeworld.customevomenu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

import java.util.Objects;

public class CustomEvoMenu extends JavaPlugin implements CommandExecutor {

    private CustomMenu customMenu;

    @Override
    public void onEnable() {
        getLogger().info("CustomEvoMenu has been enabled");

        customMenu = new CustomMenu(this);
        getServer().getPluginManager().registerEvents(customMenu, this);

        Objects.requireNonNull(getCommand("evomenu")).setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("CustomEvoMenu has been disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("evomenu")) {
            if (args.length > 0 && args[0].equalsIgnoreCase("restart")) {
                if (sender.hasPermission("evomenu.restart")) {
                    reloadPlugin(sender);
                    return true;
                } else {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to restart the plugin.");
                    return true;
                }
            } else if (sender instanceof Player) {
                Player player = (Player) sender;
                customMenu.evoMenu(player);
                return true;
            }
        }
        return false;
    }

    private void reloadPlugin(CommandSender sender) {
        getLogger().info("Reloading CustomEvoMenu...");
        sender.sendMessage(ChatColor.GREEN + "Reloading CustomEvoMenu...");
        onDisable();
        onEnable();
        sender.sendMessage(ChatColor.GREEN + "CustomEvoMenu has been reloaded.");
    }
}
