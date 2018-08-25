package customserverutil.servergui;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import customserverutil.CustomServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ServerHandlerListener implements Listener {


    @EventHandler
    public void onServerInvClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        // Navigator
        if(e.getClickedInventory().getName().equalsIgnoreCase("§bNavigator")) {
            if(e.getCurrentItem().getType() == Material.WOOD_DOOR) {
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF("lobby");

                p.sendPluginMessage(CustomServerUtil.getInstance(), "BungeeCord", out.toByteArray());
            } if(e.getCurrentItem().getType() == Material.BRICK) {
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF("cb1");

                p.sendPluginMessage(CustomServerUtil.getInstance(), "BungeeCord", out.toByteArray());
            }


            e.setCancelled(true);
        }
    }

}
