package customserverutil.essential;

import customserverutil.CustomServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;


public class PluginOverride implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPreCommand(PlayerCommandPreprocessEvent e){
        if(e.getMessage().equalsIgnoreCase("/plugin") || e.getMessage().equalsIgnoreCase("/plugins") || e.getMessage().equalsIgnoreCase("/pl")){
            if(e.getPlayer().hasPermission("CustomServerUtil.ShowPlugins")) {
                e.setCancelled(false);
            } else {
                e.setCancelled(true);
                e.getPlayer().sendMessage(CustomServerUtil.prefix + "§a(Fast) alle Plugins sind §6selbst programmiert §aund stehen als §6Open Source§a auf§6 GitHub (http://github.com/MayusYT) §azur freien Verfügung.");
            }
        }
    }
}
