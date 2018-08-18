package customserverutil.essential;

import customserverutil.CustomServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Vanish implements CommandExecutor {

    public static List<Player> vanishedPlayers = new ArrayList<>();


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        Player p = (Player) commandSender;

        if(p.hasPermission("CustomServerUtil.vanish")) {
            if(!vanishedPlayers.contains(p)) {
                vanishedPlayers.add(p);
                PotionEffect potion = new PotionEffect(PotionEffectType.INVISIBILITY, 10, 10);
                p.addPotionEffect(potion);
            } else {
                vanishedPlayers.remove(p);
                p.removePotionEffect(PotionEffectType.INVISIBILITY);
            }
        } else {
            p.sendMessage(CustomServerUtil.noPermission);
        }


        return true;
    }
}
