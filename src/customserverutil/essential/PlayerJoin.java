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

import static customserverutil.CustomServerUtil.getInstance;

public class PlayerJoin implements Listener {
    public StringBuilder sb = new StringBuilder();
    public String onlineFriends = "";
    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));

        Player p = e.getPlayer();



        e.setJoinMessage(null);
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
            for(Player ply : Bukkit.getOnlinePlayers()) {
                if(!ply.getName().equalsIgnoreCase(p.getName())) {
                    for(String plyer : conf.getFriends(ply.getName())) {
                        if(plyer.equalsIgnoreCase(p.getName())) {

                            sb.append(ply.getName() + ", ");
                        }
                    }

                }

            }
            onlineFriends = sb.toString();
            if(sb.length() != 0) {
                p.sendMessage(CustomServerUtil.prefix + "§aFolgende Freunde sind online: §6" + onlineFriends);
            } else {
                p.sendMessage(CustomServerUtil.prefix + "§aZur Zeit ist keiner deiner Freunde online :c");
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