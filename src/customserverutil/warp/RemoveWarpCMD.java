package customserverutil.warp;

import customserverutil.CustomServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveWarpCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        if(p.hasPermission("CustomServerUtil.removeWarp")) {
            if(strings.length == 1) {
                WarpCfg.removeWarp(strings[0]);
                p.sendMessage(CustomServerUtil.prefix + "§aDer Warp wurde erfolgreich gelöscht!");
            } else {
                p.sendMessage(CustomServerUtil.prefix + "§cBenutzung: /removewarp <name>");
            }
        } else {
            p.sendMessage(CustomServerUtil.noPermission);
        }
        return true;
    }
}
