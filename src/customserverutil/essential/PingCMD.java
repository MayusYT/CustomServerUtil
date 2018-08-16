package customserverutil.essential;

import customserverutil.CustomServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        int ping = ((CraftPlayer) p).getHandle().ping;
        p.sendMessage(CustomServerUtil.prefix + "§aDein Ping ist: §6" + ping);

        return true;
    }
}
