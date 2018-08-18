package customserverutil.report;

import customserverutil.CustomServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class reportAdminCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player p =(Player) commandSender;

        if(command.getName().equalsIgnoreCase("report")) {
            if(strings.length == 2) {
                if(Bukkit.getPlayer(strings[0]) != null) {
                    reportAdmin.sayAdmin(p, Bukkit.getPlayer(strings[0]), strings[1]);
                } else {
                    p.sendMessage(CustomServerUtil.prefix + "§cDu musst einen gültigen Namen angeben!");
                }

            } else {
                p.sendMessage(CustomServerUtil.prefix + "§cBenutzung: /report <Spieler> <Grund>");
            }
        }




        return true;
    }
}
