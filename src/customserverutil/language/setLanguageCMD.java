package customserverutil.language;

import customserverutil.CustomServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class setLanguageCMD implements CommandExecutor {

    public static HashMap<Player, String> languageByPlayer = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player p = (Player)commandSender;

        if(strings.length == 1) {
             if(strings[0].equalsIgnoreCase("en_US")) {
                 languageByPlayer.remove(p);
                 languageByPlayer.put(p, "en_US");
                 p.sendMessage(CustomServerUtil.prefix + "§aSuccessfully changed language.");
             } else if(strings[0].equalsIgnoreCase("de_DE")) {
                 languageByPlayer.remove(p);
                 languageByPlayer.put(p, "de_DE");
                 p.sendMessage(CustomServerUtil.prefix + "§aSprache wurde erfolgreich geändert.");
             } else {
                 p.sendMessage(CustomServerUtil.prefix + "§cUsage: /setlang <langcode> \nValid language-codes are: en_US, de_DE.");
             }

        } else {
            p.sendMessage(CustomServerUtil.prefix + "§cUsage: /setlang <langcode> \nValid language-codes are: en_US, de_DE.");
        }



        return true;
    }
}
