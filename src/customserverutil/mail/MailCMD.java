package customserverutil.mail;


import customserverutil.API;
import customserverutil.CustomServerUtil;
import customserverutil.SQL.Mail;
import customserverutil.SQL.SQLConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.SQLException;
import java.util.List;

import static customserverutil.CustomServerUtil.getInstance;

public class MailCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));


        if(args.length == 0) {

            try {
                List<Mail> mails = conf.getMails(sender.getName());
                if(mails.size() == 0) {
                    sender.sendMessage(CustomServerUtil.prefix + "§aDu hast keine neuen Nachrichten.");
                } else {
                    if(mails.size() == 1) {
                        sender.sendMessage(CustomServerUtil.prefix + "§aDeine neuen Nachricht§7:");
                    } else {
                        sender.sendMessage(CustomServerUtil.prefix + "§aDeine neuen Nachrichten§7:");
                    }

                   for(Mail m : mails) {
                       sender.sendMessage(CustomServerUtil.prefix + "§6- §4von §6" + m.getFrom() + "§4 Nachricht§7: §2" + m.getMsg());
                       conf.delMail(sender.getName(), m.getMsg());
                   }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            sender.sendMessage(CustomServerUtil.prefix + "§cUsage: §7/§6mail");
        }

        return true;
    }

}
