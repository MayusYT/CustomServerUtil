package customserverutil.essential;

import customserverutil.CustomServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RlCFGCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("CustomServerUtil.reloadConfig")) {
            PluginConfig.reloadCFG();
            p.sendMessage(CustomServerUtil.prefix + "§aConfig neu geladen.");
        } else {
            p.sendMessage(CustomServerUtil.noPermission);
        }

        return true;
    }
}
