package customserverutil.troll;

import customserverutil.CustomServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DdosCmd implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length == 1) {

            Player p = Bukkit.getPlayer(args[0]);
            if(p != null) {
                p.kickPlayer("Timed out!");
            } else {
                sender.sendMessage(CustomServerUtil.prefix + "§cDieser Spieler wurde nicht gefunden!");
            }

        } else {
            sender.sendMessage(CustomServerUtil.prefix + "§cUsage: /ddos <Name>");
        }

        return true;
    }
}
