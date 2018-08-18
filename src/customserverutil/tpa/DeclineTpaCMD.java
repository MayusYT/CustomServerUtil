package customserverutil.tpa;

import customserverutil.CustomServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeclineTpaCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;

        if(strings.length == 1) {
            DeclineTpa.declineTpaByPlayer(p, strings[0]);
        } else {
            p.sendMessage(CustomServerUtil.prefix + "§cBenutzung: /tpdecline <Spieler>");
        }



        return true;
    }
}
