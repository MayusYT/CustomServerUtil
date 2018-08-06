
package customserverutil;

import customserverutil.home.HomeCMD;
import org.bukkit.plugin.java.JavaPlugin;


public class CustomServerUtil extends JavaPlugin {
    public static CustomServerUtil instance;
    public static String prefix = "§7[§3System§7]§5 > §r";
    public static String noPermission = prefix + "§cDu hast nicht die nötige Berechtigung, um diesen Befehl auszuführen";

    @Override
    public void onEnable() {

        System.out.println("############################");
        System.out.println("CustomServerUtil gestartet!");
        System.out.println("############################");
        instance = this;
        init();
    }

    public void init() {
        getCommand("home").setExecutor(new HomeCMD());
        getCommand("sethome").setExecutor(new HomeCMD());
    }

    @Override
    public void onDisable() {

    }


    public static CustomServerUtil getInstance() {
        return instance;
    }
}
