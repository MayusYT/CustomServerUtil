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
            if(customserverutil.essential.PluginConfig.Config.getBoolean("settings.enableHomes") == true) {

                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("list")) {

                    } else {
                        if (HomeCfg.Config.get("homes." + p.getName() + "." + args[0] + ".X") != null) {
                            p.teleport(HomeCfg.getHomeLoc(p, args[0]));
                            p.sendMessage(CustomServerUtil.prefix + "§aTeleportiert!");
                        } else {
                            p.sendMessage(CustomServerUtil.prefix + "§cDieses Home existiert nicht. Zum setzen: §r/sethome" + args[0]);
                        }
                    }

                } else {
                    p.sendMessage(CustomServerUtil.prefix + "§cBenutzung: /home <name/list>");
                }
            } else {
                p.sendMessage(CustomServerUtil.prefix + "§cFür diesen Server sind Homes deaktiviert. Siehe config.yml");
            }
        } if(command.getName().equalsIgnoreCase("sethome")) {
            if(args.length == 1) {
                if(customserverutil.essential.PluginConfig.Config.getBoolean("settings.enableHomes") == true) {
                    if(HomeCfg.getHomeAmount(p) <= 3) {
                        HomeCfg.setHome(p, p.getLocation(), args[0]);
                        p.sendMessage( CustomServerUtil.prefix + "§aHome §3" + args[0] + " §agesetzt.");
                    } if(p.hasPermission("CustomServerUtil.15homes")) {

                        if(HomeCfg.getHomeAmount(p) <= 10) {

                        } else {
                            p.sendMessage(CustomServerUtil.prefix + "§cTrotz deines §6Premium Rangs §cwurden deine maximalen Homes(10) erreicht. Zur Zeit ist aus Platzgründen leider nicht mehr möglich, sorry D:");
                        }

                    } else {

                        p.sendMessage(CustomServerUtil.prefix + "cDie maximale Anzahl an Homes wurde erreicht. §r Mit dem §6 Premium Rang§r kannst du bis zu 10 Homes festlegen!");
                    }
                } else {
                    p.sendMessage(CustomServerUtil.prefix + "§cFür diesen Server sind Homes deaktiviert. Siehe config.yml");
                }

            } else {
                p.sendMessage(CustomServerUtil.prefix + "§cBenutzung: /sethome <name>");
            }
        }


        return true;
    }
}
