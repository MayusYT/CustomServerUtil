package customserverutil.banmanager;


import customserverutil.API;
import customserverutil.CustomServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Banmanager implements CommandExecutor {

    //   /ban <Spieler> <Grund> [Dauer]


    public Banmanager() {

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;

        if(p.hasPermission("CustomLobby.ban")) {
            if(args.length == 2) {
                if(Bukkit.getPlayer(args[0]) != null) {
                    try {
                        BanmanagerCfg.addToBans(Bukkit.getPlayer(args[0]), args[1]);
                        if(Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                            Bukkit.getPlayer(args[0]).kickPlayer(CustomServerUtil.prefix + "§cDu wurdest vom Netzwerk §6PERMANENT §cverbannt.");
                        }

                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    p.sendMessage(CustomServerUtil.prefix + "§cDieser Spieler existiert nicht!");
                }
            } else {
                p.sendMessage(CustomServerUtil.prefix + "§cVerwendung: /ban <Spieler> <Grund(Zusammenhängend!)>");
            }
            if(args.length == 3) {
                if(Bukkit.getPlayer(args[0]) != null) {
                    if(!API.isInt(args[2])) {
                        try {
                            BanmanagerCfg.addToBans(Bukkit.getPlayer(args[0]), args[1], System.currentTimeMillis(), Integer.parseInt(args[2]));
                            Bukkit.getPlayer(args[0]).kickPlayer(CustomServerUtil.prefix + "§cDu wurdest vom Netzwerk für §6" + args[2] + " Tage §cverbannt.");
                        } catch(Exception e) {
                            e.printStackTrace();
                            p.sendMessage(CustomServerUtil.prefix + "§cEin Fehler ist aufgetreten. Stelle sicher, dass der Grund zusammenhängt und die Dauer eine ganze Zahl ist, siehe Log");
                        }
                    } else {
                        p.sendMessage(CustomServerUtil.prefix + "§cDu musst eine Zahl als 3. Argument eingeben!");
                    }

                } else {
                    p.sendMessage(CustomServerUtil.prefix + "§cDieser Spieler existiert nicht!");
                }
            } else {
                p.sendMessage(CustomServerUtil.prefix + "§cVerwendung: /ban <Spieler> <Grund> [Zeit(Tage)] (Grund muss zusammenhängend sein!)");
            }


        } else {
            p.sendMessage(CustomServerUtil.noPermission);
        }



        return true;
    }
}
