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

public class MoneyTransfer implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));

        if(conf.canConnect()) {

        Player p = (Player) sender;
        if (args.length == 2) {
            if (Bukkit.getPlayer(args[0]) != null) {
                if (API.isInt(args[1]) && Integer.parseInt(args[1]) > 0) {
                    if (GetMoney.getMoneyByPlayer(p) > Integer.parseInt(args[1])) {
                        int moneyamountofexecutor = GetMoney.getMoneyByPlayer(p);
                        int moneyamounttobetransferred = Integer.parseInt(args[1]);
                        int moneyamountofreceiver = GetMoney.getMoneyByPlayer(Bukkit.getPlayer(args[0]));

                        SetMoney.setMoneyByPlayer(p, moneyamountofexecutor - moneyamounttobetransferred);
                        SetMoney.setMoneyByPlayer(Bukkit.getPlayer(args[0]), moneyamountofreceiver + moneyamounttobetransferred);

                        p.sendMessage(CustomServerUtil.prefix + "§c-" + args[1]);
                        p.sendMessage(StringCfg.getLangString(p, "transferMoneySender1") + args[1] + StringCfg.getLangString(p, "transferMoneySender2") + args[0]);
                        Bukkit.getPlayer(args[0]).sendMessage(CustomServerUtil.prefix + "§a+" + args[1] + "$");
                        Bukkit.getPlayer(args[0]).sendMessage(StringCfg.getLangString(p, "transferMoneyReceiver1") + args[1] + StringCfg.getLangString(p, "transferMoneyReceiver2"));
                    } else {
                        p.sendMessage(CustomServerUtil.prefix + "§cDu hast nicht genügend Geld dafür!");
                    }
                } else {
                    p.sendMessage(CustomServerUtil.prefix + "§cDu musst als zweites Argument eine Zahl über 0 angeben!");
                }
            } else {
                p.sendMessage(CustomServerUtil.prefix + "§cDer Spieler " + args[0] + " existiert nicht!");
            }
        } else {
            p.sendMessage(CustomServerUtil.prefix + "§cBenutzung: /transfer <Spieler> <Geld>");
        }

        } else {
            sender.sendMessage(CustomServerUtil.prefix + "§cDiese Funktion ist deaktiviert, da die Database nicht aktiviert ist! Bitte melde dich bei einem Server Administrator!");
        }
        return false;
    }
}
