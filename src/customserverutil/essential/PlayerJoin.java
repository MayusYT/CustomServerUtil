package customserverutil.essential;


import customserverutil.CustomServerUtil;
import customserverutil.SQL.SQLConfig;
import customserverutil.tablist.Tablist;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static customserverutil.CustomServerUtil.getInstance;

public class PlayerJoin implements Listener {
    public StringBuilder sb = new StringBuilder();
    public String onlineFriends = "";
    @EventHandler
    public void playerTablist(PlayerJoinEvent e) {
        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));

        Player p = e.getPlayer();



        e.setJoinMessage(null);

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

}