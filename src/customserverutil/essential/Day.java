package customserverutil.essential;

import customserverutil.CustomServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Day implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;
        if(p.hasPermission("CustomServerUtil.setDayTime")) {
            p.getLocation().getWorld().setTime(0);
            p.sendMessage(CustomServerUtil.prefix + "§aEs ist nun §6Tag!");
        } else {
            p.sendMessage(CustomServerUtil.noPermission);
        }

        return true;
    }
}
