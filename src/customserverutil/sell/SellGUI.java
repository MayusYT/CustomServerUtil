package customserverutil.sell;

import customserverutil.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SellGUI {

    public static Inventory createMainGUI(String buyername) {
        Inventory inv = Bukkit.createInventory(null, 9, "§aItem verkaufen");

        List<String> infoLore = new ArrayList<>();
        infoLore.add("1. Ziehe deine Items in der richtigen Menge");
        infoLore.add("   auf die Glasscheibe.");
        infoLore.add("2. Klicke auf den Goldbarren und wähle den");
        infoLore.add("   Betrag mit den Farbstoffen aus.");
        infoLore.add("3. Klicke auf die Fackel und fertig!");

        ItemStack info = ItemAPI.createItemWithLore(Material.NETHER_STAR, "Hilfe", (byte) 0, 1, infoLore);
        ItemStack glass_pane = ItemAPI.createItem(Material.THIN_GLASS, "Ziehe deine Items hier hinein!", (byte) 0, 1);
        ItemStack gold_ingot = ItemAPI.createItem(Material.GOLD_INGOT, "Betrag hier festlegen", (byte) 0, 1);
        ItemStack buyerskull = ItemAPI.createSkull(buyername, "§aKäufer: §6" + buyername, 1);
        ItemStack barrier = ItemAPI.createItem(Material.BARRIER, "§cDu hast etwas vergessen. Klicke mal auf den Netherstern!", (byte) 0, 1);
        ItemStack torch = ItemAPI.createItem(Material.TORCH, "§aVerkaufen!", (byte) 0, 1);

        inv.setItem(0, info);
        inv.setItem(1, buyerskull);
        inv.setItem(3, glass_pane);
        inv.setItem(5, gold_ingot);
        inv.setItem(8, barrier);

        return inv;
    }

    public static Inventory createValueGUI() {

        Inventory inv = Bukkit.createInventory(null, 9, "Betrag eingeben");

        ItemStack goldingot = ItemAPI.createItem(Material.GOLD_INGOT, "Betrag: §60$", (byte) 0, 1);
        ItemStack plus = ItemAPI.createItem(Material.INK_SACK, "Betrag um §a10$ §rerhöhen", (byte) 10, 1);
        ItemStack minus = ItemAPI.createItem(Material.INK_SACK, "Betrag um §c10$ §rsenken", (byte) 1, 1);
        ItemStack torch = ItemAPI.createItem(Material.TORCH, "§aÜbernehmen", (byte) 0, 1);

        inv.setItem(4, goldingot);
        inv.setItem(2, plus);
        inv.setItem(6, minus);
        inv.setItem(8, torch);

        return inv;
    }





}
