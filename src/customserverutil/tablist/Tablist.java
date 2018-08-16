package customserverutil.tablist;

import customserverutil.CustomServerUtil;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;


import java.lang.reflect.Field;

public class Tablist {
    public static int stage = 0;
    public static String toJsonString(String input) {
        return "{'text':'" + input + "'}";
    }
    public static void updateTab(String header, String footer, Player p) {
        PacketPlayOutPlayerListHeaderFooter tab = new PacketPlayOutPlayerListHeaderFooter(IChatBaseComponent.ChatSerializer.a(toJsonString(header)));
        try {
            Field a = tab.getClass().getDeclaredField("a");
            a.setAccessible(true);
            a.set(tab, IChatBaseComponent.ChatSerializer.a(toJsonString(header)));
            a.setAccessible(false);

            Field b = tab.getClass().getDeclaredField("b");
            b.setAccessible(true);
            b.set(tab, IChatBaseComponent.ChatSerializer.a(toJsonString(footer)));
            b.setAccessible(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(tab);
    }

    private static volatile int tier;
    public static void repeatingTab() {

        String defaultHeader = "Wilkommen, ";

        Bukkit.getScheduler().scheduleSyncRepeatingTask(CustomServerUtil.getInstance(), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                String header = defaultHeader + p.getName();
                int stage = tier % header.length();
                String h = "§a" + header.substring(0, stage) + "§2" + header.charAt(stage + 1) + "§a" + header.substring(stage + 1);

                updateTab(h, "Your wonderful footer", p);
            }

        }, 20, 20);
    }


}
