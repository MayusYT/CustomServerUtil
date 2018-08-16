package customserverutil.nick;


import customserverutil.API;
import customserverutil.CustomServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Nick implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

            new API();
            if (cmd.getName().equalsIgnoreCase("nick")) {
                if(sender.hasPermission("CustomServerUtil.nick")) {
                    Player p = (Player) sender;
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("off")) {
                            p.setDisplayName(p.getName());
                            p.setPlayerListName(p.getName());
                            sender.sendMessage(CustomServerUtil.prefix + "§aNickname deaktiviert");
                        } else {
                            p.setDisplayName(args[0]);
                            p.setPlayerListName(args[0]);
                            sender.sendMessage(CustomServerUtil.prefix + "§aNickname zu §6" + args[0] + "§a gesetzt");
                        }
                    } else {
                        printNickHelp((Player) sender, "self");
                    }

                } else {
                    sender.sendMessage(CustomServerUtil.noPermission);
                }
            }


        if(cmd.getName().equalsIgnoreCase("nickplayer")) {
            if (sender.hasPermission("CustomServerUtil.nickother")) {
                if (args.length == 2) {
                    Player p = Bukkit.getPlayer(args[0]);
                    if (p != null) {
                        if (args[1].equalsIgnoreCase("off")) {
                            p.setDisplayName(p.getName());
                            p.setPlayerListName(p.getName());
                            sender.sendMessage(CustomServerUtil.prefix + "§aNickname von §6" + p.getName() + " §azurückgesetzt");
                        } else {
                            p.setDisplayName(args[1]);
                            p.setPlayerListName(args[1]);
                            sender.sendMessage(CustomServerUtil.prefix + "§aNickname von §6" + p.getName() + " §azu §6" + args[1] + "§a geändert");
                        }
                    } else {

                            sender.sendMessage(CustomServerUtil.prefix + " §cSpieler nicht gefunden");
                    }
                } else {
                    printNickHelp((Player) sender, "other");
                }

            } else {
                sender.sendMessage(CustomServerUtil.prefix);
            }
        }
        return true;
    }

    private void printNickHelp(Player p, String player) {
        if(player.equals("self")) {
            p.sendMessage(CustomServerUtil.prefix + "§6Usage§8: §r /nick §7<§rName/off§7>");
        }
        if(player.equals("other")) {
            p.sendMessage(CustomServerUtil.prefix + "§6Usage§8: §r /nickplayer <§rPlayer§7> §7<§rName/off§7>");
        }

    }
}
