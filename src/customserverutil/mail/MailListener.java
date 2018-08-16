package customserverutil.mail;


import customserverutil.CustomServerUtil;
import customserverutil.SQL.Mail;
import customserverutil.SQL.SQLConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;
import java.util.List;

import static customserverutil.CustomServerUtil.getInstance;

public class MailListener implements Listener {


    SQLConfig conf = new SQLConfig();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));

        try {
            List<Mail> mails = conf.getMails(e.getPlayer().getName());

            if(mails.size() != 0) {
                e.getPlayer().sendMessage(CustomServerUtil.prefix + "§aDu hasst §6" + mails.size() + " §aungelesene Nachrichten! Nutze §7/§2mail §aum sie zu lesen.");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

}
