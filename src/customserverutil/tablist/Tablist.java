package customserverutil.tablist;

import customserverutil.CustomServerUtil;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;

public class Tablist {
    public static boolean stage = false;
    public static void setForPlayer(Player p){
        CraftPlayer cplayer = (CraftPlayer) p;
        PlayerConnection connection = cplayer.getHandle().playerConnection;
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        new BukkitRunnable(){
            @Override
            public void run() {
                try{
                    Field a = packet.getClass().getDeclaredField("a");
                    a.setAccessible(true);
                    Field b = packet.getClass().getDeclaredField("b");
                    b.setAccessible(true);

                    Object header1 = new ChatComponentText("§aWillkommen!");
                    Object header2 = new ChatComponentText("§2Willkommen!");




                    Object footer = new ChatComponentText("Website: snapecraft.ddns.net");
                    if(stage) {
                        a.set(packet, header1);
                        stage = false;
                    }
                    else {
                        a.set(packet, header2);
                        stage = true;
                    }
                    b.set(packet, footer);
                    connection.sendPacket(packet);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            }.runTaskTimer(CustomServerUtil.getInstance(), 0, 20);
        }



}
