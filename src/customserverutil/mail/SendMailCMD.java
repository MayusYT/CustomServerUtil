package customserverutil.mail;

import customserverutil.CustomServerUtil;
import customserverutil.SQL.SQLConfig;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import static customserverutil.CustomServerUtil.getInstance;

public class SendMailCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));


        if (args.length >= 2) {


                String msg = "";
                int l = 0;
                for(String s : args) {
                    if (l != 0) {
                        msg = msg + " " + s;
                    }
                    l++;
                }
                conf.addMail(sender.getName(), args[0], msg);
                sender.sendMessage(CustomServerUtil.prefix + "§aNachricht an §6" + args[0] + "§a gesendet.");

                if(Bukkit.getPlayer(args[0]) != null) {
                    Player rec  = Bukkit.getPlayer(args[0]);
                    TextComponent message1 = new TextComponent( "[§aAnsehen§r] " );
                    message1.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/mail" ) );
                    rec.spigot().sendMessage(message1);

                }

        } else {
            sender.sendMessage(CustomServerUtil.prefix + "§cUsage: §7/§6sendmail <Player> <Message>");
        }

    return true;
    }
}
