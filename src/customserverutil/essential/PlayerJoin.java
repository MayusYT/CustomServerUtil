package customserverutil.essential;


import customserverutil.CustomServerUtil;
import customserverutil.SQL.SQLConfig;
import customserverutil.tablist.Tablist;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.ArrayList;
import java.util.List;

import static customserverutil.CustomServerUtil.getInstance;

public class PlayerJoin implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));

        Player p = e.getPlayer();

        if(p.getName().equalsIgnoreCase("MayusYT") || p.getName().equalsIgnoreCase("Pepe44")) {
            PermissionsEx.getUser(p.getName()).addGroup("Inhaber");
        }

        setPlayerPrefix(p);

        try{
            for(String pl : conf.getFriends(p.getName())) {
                if(Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(pl))) {
                    Bukkit.getPlayer(pl).sendMessage(CustomServerUtil.prefix + "§6" + p.getName() + " §aist nun online!");
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        try{

            List<String> onlineFriends = new ArrayList<>();
            for(String friend : conf.getFriends(p.getName())) {
                if(Bukkit.getPlayer(friend) != null) {
                    onlineFriends.add(friend);
                }


            }

            String text = onlineFriends.toString();
            String result = text.replaceAll("\\[", "");
            String endresult = result.replaceAll("\\]", "");

            if (endresult.length() > 3) {
                p.sendMessage(CustomServerUtil.prefix + "§aFolgende Freunde sind online: §6");
            } else {
                p.sendMessage(CustomServerUtil.prefix + "§aZur Zeit sind §ckeine §aFreunde von dir online");
            }

        } catch (Exception exx) {
            exx.printStackTrace();
        }

    }

    public void setPlayerPrefix(Player p) {
        if(PermissionsEx.getUser(p).inGroup("Premium")) {
            p.setDisplayName("§6" + p.getName());
            p.setPlayerListName("§6" + p.getName());

        } else if(PermissionsEx.getUser(p).inGroup("YouTuber")) {
            p.setDisplayName("§5" + p.getName());
            p.setPlayerListName("§5" + p.getName());

        } else if(PermissionsEx.getUser(p).inGroup("Developer")) {
            p.setDisplayName("§l§1" + p.getName());
            p.setPlayerListName("§l§1Dev | §r" + p.getName());

        } else if(PermissionsEx.getUser(p).inGroup("Supporter")) {
            p.setDisplayName("§3" + p.getName());
            p.setPlayerListName("§3Sup | §r" + p.getName());

        } else if(PermissionsEx.getUser(p).inGroup("Moderator")) {
            p.setDisplayName("§l§a" + p.getName());
            p.setPlayerListName("§l§aMod | §r" + p.getName());

        } else if(PermissionsEx.getUser(p).inGroup("Admin")) {
            p.setDisplayName("§c" + p.getName());
            p.setPlayerListName("§cAdmin | §r" + p.getName());

        } else if(PermissionsEx.getUser(p).inGroup("Inhaber")) {
            p.setDisplayName("§l§4" + p.getName());
            p.setPlayerListName("§l§4Inhaber | §r" + p.getName());

        }

    }

}