package customserverutil.home;

import customserverutil.CustomServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        if(command.getName().equalsIgnoreCase("home")) {

            if(args.length == 1) {
                if(HomeCfg.Config.get("homes." + p.getName() + "." + args[0]) != null) {
                    if(HomeCfg.getHomeAmount(p) <= 3) {

                    } if(p.hasPermission("CustomServerUtil.15homes")) {

                        if(HomeCfg.getHomeAmount(p) <= 10) {

                        } else {
                            p.sendMessage(CustomServerUtil.prefix + "§cTrotz deines §6Premium Rangs §cwurden deine maximalen Homes(10) erreicht. Zur Zeit ist aus Platzgründen leider nicht mehr möglich, sorry D:");
                        }

                    } else {

                        p.sendMessage(CustomServerUtil.prefix + "cDie maximale Anzahl an Homes wurde erreicht. §r Mit dem §6 Premium Rang§r kannst du bis zu 10 Homes festlegen!");
                    }
                } else {
                    p.sendMessage(CustomServerUtil.prefix + "§cDieses Home existiert nicht. Zum setzen: §r/sethome" + args[0]);
                }
            } else {
                p.sendMessage(CustomServerUtil.prefix + "§cBenutzung: /home <name/list>");
            }

        } if(command.getName().equalsIgnoreCase("sethome")) {
            if(args.length == 1) {

            } else {
                p.sendMessage(CustomServerUtil.prefix + "§cBenutzung: /sethome <name>");
            }
        }


        return true;
    }
}
