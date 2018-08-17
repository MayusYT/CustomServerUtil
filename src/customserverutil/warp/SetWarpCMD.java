package customserverutil.warp;

import customserverutil.API;
import customserverutil.CustomServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if(p.hasPermission("CustomServerUtil.setWarp")) {
            if(args.length == 1) {
                if(!args[0].equalsIgnoreCase("list")) {
                    WarpCfg.addWarp(args[0], p.getLocation(), 0);
                    p.sendMessage(CustomServerUtil.prefix + "§aDer Warp wurde gesetzt!");
                } else {
                    p.sendMessage(CustomServerUtil.prefix + "§cDu darfst keinen Warp mit dem Namen 'list' erstellen!");
                }

            } else if(args.length == 2) {
                if (API.isInt(args[1])) {
                    if(Integer.parseInt(args[1]) < 50) {
                        WarpCfg.addWarp(args[0], p.getLocation(), Integer.parseInt(args[1]));
                        p.sendMessage(CustomServerUtil.prefix + "§aDer Warp wurde erstellt!");
                    } else {
                        p.sendMessage(CustomServerUtil.prefix + "§cDie Range darf nicht größer/gleich als 50 sein!");
                    }

                } else {
                    p.sendMessage(CustomServerUtil.prefix + "§cBitte gib eine gültige Zahl ein!");
                }
            } else{
                p.sendMessage(CustomServerUtil.prefix + "§cBenutzung: /setwarp <name> [range]");
            }
        } else {
            p.sendMessage(CustomServerUtil.noPermission);
        }



        return true;
    }
}
