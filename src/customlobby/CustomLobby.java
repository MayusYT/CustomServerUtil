
package customlobby;

import org.bukkit.plugin.java.JavaPlugin;

public class CustomLobby extends JavaPlugin {
    public static CustomLobby instance;

    @Override
    public void onEnable() {
        instance = this;
        initConfig();
    }

    @Override
    public void onDisable() {

    }

    private void initConfig() {
        saveConfig();
        reloadConfig();
        getConfig().addDefault("player.DEFAULT.exist", false);
        getConfig().addDefault("spawn.DEFAULT.exist", false);
        saveDefaultConfig();
        saveConfig();
        reloadConfig();
    }

    public static CustomLobby getInstance() {
        return instance;
    }
}
