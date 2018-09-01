package customserverutil.sell;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

public class SellHandler implements Listener {

    public static ItemStack sellitem;

    @EventHandler
    public void onDragToGlassPane(InventoryDragEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getTitle().equalsIgnoreCase("§aItem verkaufen")) {
            if(e.getCursor().getType() == Material.THIN_GLASS) {
                e.setCancelled(true);
            } else {
                sellitem.setType(e.getCursor().getType());
                p.sendMessage("Item: " + sellitem.getItemMeta().getDisplayName());
            }
        }
    }


    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if(e.getClickedInventory().getTitle().equalsIgnoreCase("§aItem verkaufen")) {

        } else if(e.getClickedInventory().getTitle().equalsIgnoreCase("Betrag eingeben")) {

        } else {
            e.setCancelled(false);
        }


    }



}
