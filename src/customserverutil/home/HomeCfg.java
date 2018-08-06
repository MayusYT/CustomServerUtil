package customserverutil.home;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeCfg {

    public static File ConfigFile = new File("plugins/CustomLobby", "bans.yml");
    public static FileConfiguration Config = YamlConfiguration.loadConfiguration(ConfigFile);

    public static void saveHome() {
        try{
            Config.save(ConfigFile);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
    public static void reloadHome() {
        try {
            Config = YamlConfiguration.loadConfiguration(ConfigFile);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void setHome(Player p, Location loc, String name) {

        if(Config.getString("homes." + p.getName() + ".amount") != null && Config.getInt("homes." + p.getName() + ".amount") != 0){
            int amount = Config.getInt("homes." + p.getName() + ".amount");
            amount = amount + 1;
            Config.set("homes." + p.getName() + ".amount", String.valueOf(amount));
        } else {
            Config.set("homes." + p.getName() + ".amount", 1);
        }
        Config.set("homes." + p.getName() + "." + name + ".WORLD", loc.getWorld());
        Config.set("homes." + p.getName() + "." + name + ".X", loc.getX());
        Config.set("homes." + p.getName() + "." + name + ".Y", loc.getY());
        Config.set("homes." + p.getName() + "." + name + ".Z", loc.getZ());
        Config.set("homes." + p.getName() + "." + name + ".PITCH", loc.getPitch());
        Config.set("homes." + p.getName() + "." + name + ".YAW", loc.getYaw());
        saveHome();
        reloadHome();
    }
    public static Location getHomeLoc(Player p, String name) {
        if(Config.get("homes." + p.getName()) != null) {
            if(Config.get("homes" + p.getName() + "." + name) != null) {

                World locworld = Bukkit.getWorld(Config.getString("homes." + p.getName() + "." + name + ".WORLD"));
                Double locx = Double.parseDouble(Config.getString("homes." + p.getName() + "." + name + ".X"));
                Double locy = Double.parseDouble(Config.getString("homes." + p.getName() + "." + name + ".Y"));
                Double locz = Double.parseDouble(Config.getString("homes." + p.getName() + "." + name + ".Z"));
                Float locpitch = Float.parseFloat(Config.getString("homes." + p.getName() + "." + name + ".PITCH"));
                Float locyaw = Float.parseFloat(Config.getString("homes." + p.getName() + "." + name + ".YAW"));
                Location loc = new Location(locworld, locx, locy, locz, locyaw, locpitch);
                return loc;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }
    public static Integer getHomeAmount(Player p) {
        if(Config.getString("homes." + p.getName()) != null) {
            if(Config.getInt("homes." + p.getName() + ".amount") != 0) {
                return Config.getInt("homes." + p.getName() + ".amount");
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
    @SuppressWarnings("deprecation")
    public static List<String> listHome(Player p) {
        List<String> homelist = new ArrayList<>();
        for(String key : Config.getConfigurationSection("homes").getKeys(true)){
            if(Bukkit.getPlayer(key) == null) {
                if(Bukkit.getOfflinePlayer(key) == null) {
                    homelist.add(key);
                }
            }
        }

        return homelist;

    }
}
