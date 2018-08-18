package customserverutil.report;

import customserverutil.CustomServerUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpectateThatPlayerCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player p = (Player) commandSender;

        if(p.hasPermission("CustomServerUtil.offerHelp")) {
            if(strings.length == 2) {
                p.setGameMode(GameMode.SPECTATOR);
                p.teleport(Bukkit.getPlayer(strings[0]));
                p.sendMessage(CustomServerUtil.prefix + "§aDu beobachtest gerade den Spieler §6" + strings[0]);
                TextComponent message1 = new TextComponent( "[§eVerwarnen§r] " );
                message1.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/warn " + strings[0]) );
                TextComponent message2 = new TextComponent( "[§cBannen§r]" );
                message2.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/ban " + strings[0] + " " + strings[1] ) );
                message1.addExtra(message2);
                p.spigot().sendMessage(message1);

            } else if(strings.length == 1) {
                p.setGameMode(GameMode.SPECTATOR);
                p.teleport(Bukkit.getPlayer(strings[0]));
                p.sendMessage(CustomServerUtil.prefix + "§aDu beobachtest gerade den Spieler §6" + strings[0]);
                TextComponent message1 = new TextComponent( "[§eVerwarnen§r] " );
                message1.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/warn " + strings[0]) );
                TextComponent message2 = new TextComponent( "[§cBannen§r]" );
                message2.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/ban " + strings[0] + " keinGrundAngegeben" ) );
                message1.addExtra(message2);
                p.spigot().sendMessage(message1);

            } else {
                p.sendMessage(CustomServerUtil.prefix + "§cBenutzung: /spectateplayer <name> <grund>");
            }

        } else {
            p.sendMessage(CustomServerUtil.noPermission);
        }


        return true;
    }
}
