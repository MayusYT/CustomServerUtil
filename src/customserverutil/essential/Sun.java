package customserverutil.essential;

import customserverutil.CustomServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sun implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(p.hasPermission("CustomServerUtil.setWeather")) {
            p.getLocation().getWorld().setStorm(false);
            p.getLocation().getWorld().setThundering(false);
            p.sendMessage(CustomServerUtil.prefix + "§aEs ist nun §6sonnig!");
        } else {
            p.sendMessage(CustomServerUtil.noPermission);
        }




        return true;
    }
}
