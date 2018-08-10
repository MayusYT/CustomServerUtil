package customserverutil.gamemodes;

import customserverutil.CustomServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModes implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;


        if(p.hasPermission("CustomServerUtil.gamemode")) {
            if(args.length == 0) {
                if(command.getName().equalsIgnoreCase("s")) {
                    p.setGameMode(GameMode.SURVIVAL);
                } if(command.getName().equalsIgnoreCase("c")) {
                    p.setGameMode(GameMode.CREATIVE);
                } if(command.getName().equalsIgnoreCase("sp")) {
                    p.setGameMode(GameMode.SPECTATOR);
                }
            } if(args.length == 1) {
                if(Bukkit.getPlayer(args[0]) != null) {
                    if(command.getName().equalsIgnoreCase("s")) {
                        Bukkit.getPlayer(args[0]).setGameMode(GameMode.SURVIVAL);
                    } if(command.getName().equalsIgnoreCase("c")) {
                        Bukkit.getPlayer(args[0]).setGameMode(GameMode.CREATIVE);
                    } if(command.getName().equalsIgnoreCase("sp")) {
                        Bukkit.getPlayer(args[0]).setGameMode(GameMode.SPECTATOR);
                    } else {
                        p.sendMessage(CustomServerUtil.prefix + "§cBenutzung: /<c/s/sp>");
                    }
                } else {
                    p.sendMessage(CustomServerUtil.prefix + "§cBitte gib einen gültigen Spielernamen ein!");
                }
            } else {
                p.sendMessage(CustomServerUtil.prefix + "§cBenutzung: /<c/s/sp> <Spieler>");
            }
        } else {
            p.sendMessage(CustomServerUtil.noPermission);
        }



        return true;
    }
}
