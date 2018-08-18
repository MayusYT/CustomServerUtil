package customserverutil.tpa;

import customserverutil.CustomServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AcceptTpa {

    public static void acceptTpaByPlayer(Player p, String s) {

        //Player p = Player who sends /tpaccept
        //String s = args[0] of /tpaccept


        if(SendTpa.tpaMap.containsKey(s) && SendTpa.tpaMap.get(s).equalsIgnoreCase(p.getName())) {
            SendTpa.tpaMap.remove(s, p.getName());
            Bukkit.getPlayer(s).sendMessage(CustomServerUtil.prefix + "§6" + p.getName() + " §ahat deine Teleportanfrage angenommen!");
            p.teleport(Bukkit.getPlayer(s));
            p.sendMessage(CustomServerUtil.prefix + "§aDu wurdest zum Spieler §6" + s + " §ateleportiert!");
        } else {
            p.sendMessage(CustomServerUtil.prefix + "§cDu hast keine Teleportanfrage von diesem Spieler bekommen!");
        }


    }



}
