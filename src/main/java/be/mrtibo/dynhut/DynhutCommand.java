package be.mrtibo.dynhut;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DynhutCommand implements CommandExecutor {

    Dynhut dynhut;

    public DynhutCommand(Dynhut dynhut){
        this.dynhut = dynhut;
        dynhut.getCommand("dynhut").setExecutor(this);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        dynhut.client.disconnect(dynhut.getPublicUrl());
        dynhut.client.kill();

        if(dynhut.startHttpTunnel()){
            sender.sendMessage(ChatColor.GREEN + "Ngrok process started successfully.");
        } else {
            sender.sendMessage(ChatColor.RED + "Something went wrong, check the console for errors.");
        }
        return true;
    }
}
