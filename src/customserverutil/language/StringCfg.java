package customserverutil.language;

import customserverutil.CustomServerUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class StringCfg {

    public static File ConfigFile = new File("plugins/CustomServerUtil", "strings.yml");
    public static FileConfiguration Config = YamlConfiguration.loadConfiguration(ConfigFile);

    public static void saveStrings() {
        try{
            Config.save(ConfigFile);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
    public static void reloadStrings() {
        try {
            Config = YamlConfiguration.loadConfiguration(ConfigFile);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void addStrings() {

        //en_US
        Config.set("strings.en_US.noperm", "§cYou don't have the permission to execute this command!"); //
        Config.set("strings.en_US.nofriendsonline", "§cNobody §rfrom your friends list is currently online.");//
        Config.set("strings.en_US.friendsonline", "§aThese friends of you are online: §6");//
        Config.set("strings.en_US.msgYOU", "you");//
        Config.set("strings.en_US.gotWarn", "§aYou just received a warn! At 3 warns you will be banned. Amount of warns: §c"); //
        Config.set("strings.en_US.getStarterMoney", "§aCongratulations! You just got your starter money in value of §61000$§a!");//
        Config.set("strings.en_US.getMoney", "§aYour current balance is: §6");//
        Config.set("strings.en_US.transferMoneySender1", "§aYou tranferred §6");//
        Config.set("strings.en_US.transferMoneySender2", "$§a to §6");//
        Config.set("strings.en_US.transferMoneyReceiver1", "§aYou just received §6");//
        Config.set("strings.en_US.transferMoneyReceiver2", "$§afrom §6");//
        Config.set("strings.en_US.ping", "§aYour current ping is: §6");//
        Config.set("strings.en_US.FriendRequestSender", "§aFriend request sent.");
        Config.set("strings.en_US.FriendRequestReceiver", "§a wants to be your friend!");
        Config.set("strings.en_US.accept", "§aACCEPT");
        Config.set("strings.en_US.decline", "§cDECLINE");
        Config.set("strings.en_US.setHomeSuccess", "§aHome set.");
        Config.set("strings.en_US.homeLimitReached", "§cYou just reached the home limit! Consider buying premium to get more homes!");
        Config.set("strings.en_US.homeDoesNotExist", "§cThis home does not exist!");
        Config.set("strings.en_US.usage", "§cUsage: ");
        Config.set("strings.en_US.teleported", "§aTeleported!");
        Config.set("strings.en_US.homesdisabled", "§cUnfortunately, homes are disabled for this server!");

        //de_DE
        Config.set("strings.de_DE.noperm", "§cYou don't have the permission to execute this command!");
        Config.set("strings.de_DE.nofriendsonline", "§cNobody §rfrom your friends list is currently online.");
        Config.set("strings.de_DE.friendscount1", "§aThere are §6");
        Config.set("strings.de_DE.friendscount2", "§a friends online");
        Config.set("strings.de_DE.msgYOU", "you");
        Config.set("strings.de_DE.gotWarn", "§aYou just received a warn! At 3 warns you will be banned. Amount of warns: §c");
        Config.set("strings.de_DE.getStarterMoney", "§aCongratulations! You just got your starter money in value of §61000$§a!");
        Config.set("strings.de_DE.getMoney", "§aYour current balance is: §6");
        Config.set("strings.de_DE.transferMoneySender1", "§aYou tranferred §6");
        Config.set("strings.de_DE.transferMoneySender1", "$§a to §6");
        Config.set("strings.de_DE.transferMoneyReceiver1", "§aYou just received §6");
        Config.set("strings.de_DE.transferMoneyReceiver1", "$§afrom §6");
        Config.set("strings.de_DE.ping", "§aYour current ping is: §6");
        Config.set("strings.de_DE.FriendRequestSender", "§aFriend request sent.");
        Config.set("strings.de_DE.FriendRequestReceiver", "§a wants to be your friend!");
        Config.set("strings.de_DE.accept", "§aACCEPT");
        Config.set("strings.de_DE.decline", "§cDECLINE");
        Config.set("strings.de_DE.setHomeSuccess", "§aHome set.");
        Config.set("strings.de_DE.homeLimitReached", "§cYou just reached the home limit! Consider buying premium to get more homes!");
        Config.set("strings.de_DE.homeDoesNotExist", "§cThis home does not exist!");
        Config.set("strings.de_DE.usage", "§cUsage: ");
        Config.set("strings.de_DE.teleported", "§aTeleported!");
        Config.set("strings.de_DE.homesdisabled", "§cUnfortunately, homes are disabled for this server!");

        saveStrings();
        reloadStrings();
    }

    public static String getLangString(Player p, String codename) {

        String langString = "";

        if(PlayerLanguage.getLanguage(p).equalsIgnoreCase("en_US")) {
            langString = CustomServerUtil.prefix + Config.getString("strings.en_US." + codename);
        } else {
            langString = CustomServerUtil.prefix + Config.getString("strings.de_DE." + codename);
        }

        return langString;
    }
}
