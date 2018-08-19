
package customserverutil;

import customserverutil.banmanager.Banmanager;
import customserverutil.banmanager.PardonHandler;
import customserverutil.banmanager.WarnHandler;
import customserverutil.chat.ChatListener;
import customserverutil.economy.GetMoneyCMD;
import customserverutil.economy.MoneyTransfer;
import customserverutil.economy.SetMoneyCMD;
import customserverutil.essential.*;
import customserverutil.friends.friendsCMD;
import customserverutil.gamemodes.GameModes;
import customserverutil.home.HomeCMD;
import customserverutil.mail.MailCMD;
import customserverutil.mail.SendMailCMD;
import customserverutil.nick.Nick;
import customserverutil.report.SpectateThatPlayerCMD;
import customserverutil.report.reportAdminCMD;
import customserverutil.scoreboard.ScoreboardListener;
import customserverutil.servergui.ServerHandlerCMD;
import customserverutil.servergui.ServerHandlerListener;
import customserverutil.tablist.Tablist;
import customserverutil.tpa.AcceptTpaCMD;
import customserverutil.tpa.DeclineTpaCMD;
import customserverutil.tpa.SendTpaCMD;
import customserverutil.warp.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class CustomServerUtil extends JavaPlugin {
    public static CustomServerUtil instance;
    public static String prefix = "§7[§3System§7]§5 > §r";
    public static String noPermission = prefix + "§cDu hast nicht die nötige Berechtigung, um diesen Befehl auszuführen";
    public static Boolean pexenabled = false;

    @Override
    public void onEnable() {


        init();

        System.out.println("############################");
        System.out.println("CustomServerUtil gestartet!");
        System.out.println("############################");
        if(Bukkit.getPluginManager().getPlugin("PermissionsEx").isEnabled()) {
            pexenabled = true;
            System.out.println("PermissionsEX wurde geladen!");
        } else {
            pexenabled = false;
            System.out.println("PermissionsEX konnte nicht geladen werden!");
        }
        instance = this;

        Tablist.repeatingTab();

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
        getCommand("ban").setExecutor(new Banmanager());
        getCommand("warn").setExecutor(new WarnHandler());
        getCommand("pardon").setExecutor(new PardonHandler());
        getCommand("sendmail").setExecutor(new SendMailCMD());
        getCommand("mail").setExecutor(new MailCMD());
        getCommand("nick").setExecutor(new Nick());
        getCommand("ping").setExecutor(new PingCMD());
        getCommand("warp").setExecutor(new WarpCMD());
        getCommand("setwarp").setExecutor(new SetWarpCMD());
        getCommand("removewarp").setExecutor(new RemoveWarpCMD());
        getCommand("spawn").setExecutor(new SpawnCMD());
        getCommand("tpa").setExecutor(new SendTpaCMD());
        getCommand("tpaccept").setExecutor(new AcceptTpaCMD());
        getCommand("tpdecline").setExecutor(new DeclineTpaCMD());
        getCommand("report").setExecutor(new reportAdminCMD());
        getCommand("spectateplayer").setExecutor(new SpectateThatPlayerCMD());
        getCommand("build").setExecutor(new BuildModeCMD());
        getCommand("friend").setExecutor(new friendsCMD());
        getCommand("v").setExecutor(new Vanish());
        getCommand("lobby").setExecutor(new LobbyCMD());
        getCommand("money").setExecutor(new GetMoneyCMD());
        getCommand("setmoney").setExecutor(new SetMoneyCMD());
        getCommand("transfer").setExecutor(new MoneyTransfer());

        Bukkit.getPluginManager().registerEvents(new WarpProtection(), this);
        Bukkit.getPluginManager().registerEvents(new ServerHandlerListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new ScoreboardListener(), this);

        //Config-Defaults
        PluginConfig.Config.addDefault("player.DEFAULT.exist", false);
        PluginConfig.Config.addDefault("spawn.DEFAULT.exist", false);

        PluginConfig.Config.addDefault("SQL.host", "");
        PluginConfig.Config.addDefault("SQL.user", "");
        PluginConfig.Config.addDefault("SQL.pw", "");
        PluginConfig.Config.addDefault("SQL.db", "");

        PluginConfig.Config.options().copyDefaults(true);
        PluginConfig.saveCFG();
        PluginConfig.reloadCFG();
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
