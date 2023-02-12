package be.mrtibo.dynhut;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    Dynhut dynhut;

    public JoinListener(Dynhut dynhut){
        this.dynhut = dynhut;
        dynhut.getServer().getPluginManager().registerEvents(this, dynhut);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.getPlayer().sendMessage("Dynmap is available at: " + ChatColor.GREEN + dynhut.getPublicUrl());
    }

}
