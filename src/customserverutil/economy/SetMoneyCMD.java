package customserverutil.economy;

import customserverutil.API;
import customserverutil.CustomServerUtil;
import customserverutil.SQL.SQLConfig;
import customserverutil.language.StringCfg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static customserverutil.CustomServerUtil.getInstance;

public class SetMoneyCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));
        if(conf.canConnect()) {

            //TODO Commands registrieren, elses, usw.
            Player p = (Player) sender;

            if (args.length == 2) {
                if (p.hasPermission("CustomLobby.setMoney")) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        if (API.isInt(args[1])) {
                            SetMoney.setMoneyByPlayer(Bukkit.getPlayer(args[0]), Integer.parseInt(args[1]));
                            p.sendMessage(CustomServerUtil.prefix + "§aDer Kontostand des Spielers " + args[0] + " wurde auf " + args[1]
                                    + "gesetzt.");
                        } else {
                            p.sendMessage(CustomServerUtil.prefix + "§cBitte gib eine Zahl über 0 ein!");
                        }

                    } else {
                        p.sendMessage(CustomServerUtil.prefix + args[0] + " ist kein Spieler");
                    }
                } else {
                    p.sendMessage(StringCfg.getLangString(p, "noperm"));
                }


            } else {
                p.sendMessage(CustomServerUtil.prefix + "§cVerwendung: /setmoney <Spieler> <Geld>");
            }

        } else {
            sender.sendMessage(CustomServerUtil.prefix + "§cDiese Funktion ist deaktiviert, da die Database nicht aktiviert ist! Bitte melde dich bei einem Server Administrator!");
        }
        return true;
    }
}
