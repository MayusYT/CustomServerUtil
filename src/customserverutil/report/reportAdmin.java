package customserverutil.report;

import customserverutil.CustomServerUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class reportAdmin {


    public static void sayAdmin(Player s, Player p, String reason) {

        //Player s: sender, Player p: reported Player

        for(Player pl : Bukkit.getOnlinePlayers()) {
            if(pl.hasPermission("CustomServerUtil.offerHelp")) {
                pl.sendMessage(CustomServerUtil.prefix + "Der Spieler §6" + s.getName() + " §rmeldet den Spieler §c" + s.getName() + " §r für: §9" + reason);
                TextComponent message = new TextComponent( "[Anschauen]" );
                message.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/spectateplayer " + p.getName() + " " + reason) );
                p.spigot().sendMessage(message);
            }
        }


    }


}
