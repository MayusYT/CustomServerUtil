package customserverutil.tpa;

import customserverutil.CustomServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DeclineTpa {

    public static void declineTpaByPlayer(Player p, String s) {

        //Player p = Player who sends /tpdecline
        //String s = args[0] of /tpdecline


        if(SendTpa.tpaMap.containsKey(s) && SendTpa.tpaMap.get(s).equalsIgnoreCase(p.getName())) {
            SendTpa.tpaMap.remove(s, p.getName());
            p.sendMessage(CustomServerUtil.prefix + "§aDu hast die Anfrage abgelehnt");
            Bukkit.getPlayer(s).sendMessage(CustomServerUtil.prefix + "§6" + p.getName() + " §ahat deine Teleportanfrage §cabgelehnt!");

        } else {
            p.sendMessage(CustomServerUtil.prefix + "§cDu hast keine Teleportanfrage von diesem Spieler bekommen!");
        }


    }

}
