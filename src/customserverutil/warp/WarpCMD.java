package customserverutil.warp;

import customserverutil.CustomServerUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WarpCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;

        if(strings.length == 1) {
            if(strings[0].equalsIgnoreCase("list")) {
                List<String> textlist = WarpCfg.getWarps();
                String text = textlist.toString();
                String result = text.replaceAll("\\[", "");
                String endresult = result.replaceAll("\\]", "");

                p.sendMessage(CustomServerUtil.prefix + "§aWarps: §6" + endresult);
            } else if(WarpCfg.getWarpLoc(strings[0]) != null) {
                Location loc = WarpCfg.getWarpLoc(strings[0]);
                p.teleport(loc);
                p.sendMessage(CustomServerUtil.prefix + "§aWillkommen am Warp §6" + strings[0] + "§a!");
            } else {
                p.sendMessage(CustomServerUtil.prefix + "§cDiesen Warp gibt es nicht!");
            }
        } else {
            p.sendMessage(CustomServerUtil.prefix + "§cBenutzung: /warp <Name>");
        }

        return true;

    }
}
