package customserverutil.essential;


import customserverutil.CustomServerUtil;
import customserverutil.SQL.SQLConfig;
import customserverutil.tablist.Tablist;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.Permission;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static customserverutil.CustomServerUtil.board;
import static customserverutil.CustomServerUtil.getInstance;


public class PlayerJoin implements Listener {

    public static HashMap<String, String> realnames = new HashMap<>();

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        SQLConfig conf = new SQLConfig();
        conf.initialize(getInstance().getConfig().getString("SQL.host"), getInstance().getConfig().getString("SQL.user"), getInstance().getConfig().getString("SQL.pw"), getInstance().getConfig().getString("SQL.db"));

        Player p = e.getPlayer();
        if(p.getName().equalsIgnoreCase("MayusYT") || p.getName().equalsIgnoreCase("Pepe44")) {
            PermissionsEx.getUser(p.getName()).addGroup("Inhaber");
        }


        e.setJoinMessage(null);
        //setPlayerPrefix(p);

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
                p.sendMessage(CustomServerUtil.prefix + "§aFolgende Freunde sind online: §6" + endresult);
            } else {
                p.sendMessage(CustomServerUtil.prefix + "§aZur Zeit sind §ckeine §aFreunde von dir online");
            }

        } catch (Exception exx) {
            exx.printStackTrace();
        }

    }

    public void setPlayerPrefix(Player p) {

        String team = "";


        if(PermissionsEx.getUser(p).inGroup("Inhaber")) {

            team = "00000Inhaber";
            //p.setDisplayName("§6" + p.getName());
            //p.setPlayerListName("§6" + p.getName());

        } else if(PermissionsEx.getUser(p).inGroup("Admin")) {

            team = "00001Admin";
            //p.setDisplayName("§5" + p.getName());
            //p.setPlayerListName("§5" + p.getName());

        } else if(PermissionsEx.getUser(p).inGroup("Developer")) {

            team = "00002Dev";
            //p.setDisplayName("§l§1" + p.getName());
            //p.setPlayerListName("§l§1Dev | §r" + p.getName());

        }else if(PermissionsEx.getUser(p).inGroup("Moderator")) {

            team = "00003Mod";
            //p.setDisplayName("§l§a" + p.getName());
            //p.setPlayerListName("§l§aMod | §r" + p.getName());

        } else if(PermissionsEx.getUser(p).inGroup("YouTuber")) {
            team = "00004YouTuber";
            //p.setDisplayName("§c" + p.getName());
//            p.setPlayerListName("§cAdmin | §r" + p.getName());

        } else if(PermissionsEx.getUser(p).inGroup("Builder")) {
            team = "00005Builder";
            //p.setDisplayName("§l§4" + p.getName());
            //p.setPlayerListName("§l§4Inhaber | §r" + p.getName());

        } else if(PermissionsEx.getUser(p).inGroup("Premium")) {
            team = "00006Premium";
        } else {
            team = "00007Spieler";
        }

        board.getTeam(team).addPlayer(p);
        p.setDisplayName(board.getTeam(team).getPrefix() + p.getName());

        for(Player x : Bukkit.getOnlinePlayers()) {
            x.setScoreboard(board);
        }

    }

}