package be.mrtibo.dynhut;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MapCommand implements CommandExecutor {

    Dynhut dynhut;

    public MapCommand(Dynhut main){
        this.dynhut = main;
        main.getCommand("map").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage("Dynmap is available at: " + ChatColor.GREEN + dynhut.getPublicUrl());
        return true;
    }
}
