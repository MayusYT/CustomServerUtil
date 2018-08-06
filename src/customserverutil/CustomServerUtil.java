
package customserverutil;

import org.bukkit.plugin.java.JavaPlugin;

public class CustomServerUtil extends JavaPlugin {
    public static CustomServerUtil instance;
    public static String prefix = "§7[§3System§7]§5 > §r";
    public static String noPermission = prefix + "§cDu hast nicht die nötige Berechtigung, um diesen Befehl auszuführen";

    @Override
    public void onEnable() {
        instance = this;

    }

    @Override
    public void onDisable() {

    }


    public static CustomServerUtil getInstance() {
        return instance;
    }
}
