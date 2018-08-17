package customserverutil.warp;

import customserverutil.CustomServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player p = (Player) commandSender;

        if(WarpCfg.getWarpLoc("spawn") != null) {
            p.teleport(WarpCfg.getWarpLoc("spawn"));
            p.sendMessage(CustomServerUtil.prefix + "§aWillkommen am §6Spawn§a!");
        } else {
            p.sendMessage(CustomServerUtil.prefix + "§cDer Spawn wurde noch nicht gesetzt! Bitte sage deinem Admin, dass er einen Warp namens" +
                    " 'spawn' einrichten soll!");

            System.out.println("[CustomServerUtil] Der Spieler " + p.getName() + " möchte sich zum Spawn teleportieren, obwohl dieser noch nicht" +
                    " vorhanden ist!");
        }



        return true;
    }
}
