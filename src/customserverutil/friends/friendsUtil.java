package customserverutil.friends;


import customserverutil.CustomServerUtil;
import customserverutil.SQL.SQLConfig;
import org.bukkit.entity.Player;


public class friendsUtil {
    public static void makeFriend(Player friend1, Player friend2) {
        SQLConfig conf = new SQLConfig();
        conf.initialize(CustomServerUtil.getInstance().getConfig().getString("SQL.host"), CustomServerUtil.getInstance().getConfig().getString("SQL.user"), CustomServerUtil.getInstance().getConfig().getString("SQL.pw"), CustomServerUtil.getInstance().getConfig().getString("SQL.db"));

        if(!conf.canConnect()) {
            friend1.sendMessage("Fehler!");
        }

        conf.addFriend(friend1.getName(), friend2.getName());
        conf.removeFriendReq(friend2.getName(), friend1.getName());


        friend1.sendMessage(CustomServerUtil.prefix + "§aDu bist nun mit §6" + friend2.getName() + " §abefreundet");

    }
}
