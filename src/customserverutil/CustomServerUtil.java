
package customserverutil;

import customserverutil.essential.*;
import customserverutil.gamemodes.GameModes;
import customserverutil.home.HomeCMD;
import customserverutil.servergui.ServerHandlerCMD;
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

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        getCommand("home").setExecutor(new HomeCMD());
        getCommand("sethome").setExecutor(new HomeCMD());

        getCommand("s").setExecutor(new GameModes());
        getCommand("c").setExecutor(new GameModes());
        getCommand("sp").setExecutor(new GameModes());

        getCommand("sun").setExecutor(new Sun());
        getCommand("day").setExecutor(new Day());
        getCommand("rlcfg").setExecutor(new RlCFGCMD());

        getCommand("server").setExecutor(new ServerHandlerCMD());
        //Config-Defaults
        if(PluginConfig.ConfigFile.length() == 0) {
            PluginConfig.Config.set("settings.enableHomes", true);
            PluginConfig.saveCFG();
            PluginConfig.reloadCFG();
        }

    }

    @Override
    public void onDisable() {

    }


    public static CustomServerUtil getInstance() {
        return instance;
    }
}
