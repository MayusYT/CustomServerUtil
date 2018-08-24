package customserverutil.nametag;

import com.bringholm.nametagchanger.NameTagChanger;
import customserverutil.chat.ChatListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class NametagListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        p.sendMessage(ChatListener.getRankString(p));
        NameTagChanger.INSTANCE.changePlayerName(p, ChatListener.getRankString(p) + p.getDisplayName());
    }

}
