package customserverutil.tpa;

import customserverutil.CustomServerUtil;
import customserverutil.report.reportAdmin;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SendTpa {

    public static HashMap<String, String> tpaMap = new HashMap<>();


    public static void sendTpaToPlayer(Player s, Player p) {

        //s = sender, p = requested Player

        if(p.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
            s.sendMessage(CustomServerUtil.prefix + "§aDu hast §6" + p.getName() + " §aerfolgreich eine Teleportanfrage geschickt!");
            p.sendMessage(CustomServerUtil.prefix + "§aDu hast eine Teleport-Anfrage von §6" + s.getName() + "§a bekommen!");


            //Accept & Decline Buttons for receiver
            TextComponent message1 = new TextComponent( "[§aAnnehmen§r] " );
            message1.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/tpaccept " + s.getName()) );
            TextComponent message2 = new TextComponent( "[§cAblehnen§r]" );
            message2.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/tpdecline " + s.getName()) );
            message1.addExtra(message2);
            p.spigot().sendMessage(message1);

            tpaMap.put(s.getName(), p.getName());
        } else {
            s.sendMessage(CustomServerUtil.prefix + "§cAchtung: Der Teleport war nicht sicher! Spieler §6" + p.getName() + " §cwurde einem Admin für Teleportfallen gemeldet!");
            reportAdmin.sayAdmin(s, p, "Teleportfalle");
        }




    }


}
