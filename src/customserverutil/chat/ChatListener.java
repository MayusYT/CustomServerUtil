package customserverutil.chat;


import customserverutil.CustomServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ChatListener implements Listener {


    private String group = "";
    @EventHandler
    public void onChat(PlayerChatEvent e) {

        e.setCancelled(true);
        Player p = e.getPlayer();
        String msg = e.getMessage();
        String pref = "";

        if(PermissionsEx.getUser(p).inGroup("Premium")) {
            pref = "§6Premium";
        } if(PermissionsEx.getUser(p).inGroup("YouTuber")) {
            pref = "§5YouTuber";
        } if(PermissionsEx.getUser(p).inGroup("Builder")) {
            pref = "§l§9Builder ";
        } if(PermissionsEx.getUser(p).inGroup("Developer")) {
            pref = "§l§3Dev ";
        } if(PermissionsEx.getUser(p).inGroup("Supporter")) {
            pref = "§l§aMod ";
        } if(PermissionsEx.getUser(p).inGroup("Inhaber")) {
            pref = "§l§cInhaber ";
        }
        for(Player cp : Bukkit.getOnlinePlayers()) {
            cp.sendMessage(pref + "§7[§f" + p.getName() + "§7]§5: §r" + e.getMessage());
        }

    }

}
