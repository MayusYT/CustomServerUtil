package customserverutil.essential;


import customserverutil.tablist.Tablist;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void playerTablist(PlayerJoinEvent e) {
        Tablist.setForPlayer(e.getPlayer());

    }

}