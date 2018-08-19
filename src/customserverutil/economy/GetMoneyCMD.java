package customserverutil.economy;

import customserverutil.CustomServerUtil;
import customserverutil.SQL.SQLConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static customserverutil.CustomServerUtil.getInstance;

public class GetMoneyCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));

        if (conf.canConnect()) {

            Player p = (Player) sender;
            try {
                if (GetMoney.getMoneyByPlayer(p) != 0) {
                    p.sendMessage(CustomServerUtil.prefix + "Dein Kontostand: §a" + GetMoney.getMoneyByPlayer(p) + "$");
                } else {
                    p.sendMessage(CustomServerUtil.prefix + "Glückwunsch! Du hast dein Startgeld bekommen: §a" + 1000 + "$");
                    //CustomLobby.getInstance().getConfig().set("players." + p.getName() + ".money", 1000);
                    GetMoney.setMoneyByPlayer(p, 1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            sender.sendMessage(CustomServerUtil.prefix + "§cDiese Funktion ist deaktiviert, da die Database nicht aktiviert ist! Bitte melde dich bei einem Server Administrator!");
        }
        return true;
    }
}
