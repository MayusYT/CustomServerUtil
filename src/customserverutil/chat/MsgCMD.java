package customserverutil.chat;

import customserverutil.CustomServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MsgCMD implements CommandExecutor {

    public static HashMap<Player, Player> lastMSGsender = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;

        if(args.length >= 2) {
            if (Bukkit.getPlayer(args[0]) != null) {
                Player rec = Bukkit.getPlayer(args[0]);
                if(lastMSGsender.containsKey(p) || lastMSGsender.containsValue(rec)) {

                    try{
                        lastMSGsender.remove(p, rec);
                        String msg = "";
                        int l = 1;
                        String recive = args[0];
                        List<String> list = Arrays.asList(args.clone());
                        for (String sto:list) {
                            if(l != 0) {
                                msg = msg + " " + sto;
                            }
                            l++;
                        }



                        p.sendMessage("§7[§aDu§7] §5> §7[§6" + recive + "§7] §5: §r" + msg);
                        rec.sendMessage("§7[§5" + p.getName() + "§7] §5> §7[§aDu§7] §5: §r" + msg);
                        lastMSGsender.put(rec, p);
                    } catch (NullPointerException e) {
                        lastMSGsender.remove(rec, p);
                        String msg = "";
                        int l = 1;
                        List<String> list = Arrays.asList(args.clone());
                        for (String sto:list) {
                            if(l != 0) {
                                msg = msg + " " + sto;
                            }
                            l++;
                        }

                        p.sendMessage("§7[§aDu§7] §5> §7[§6" + args[0] + "§7] §5: §r" + msg);
                        rec.sendMessage("§7[§5" + p.getName() + "§7] §5> §7[§aDu§7] §5: §r" + msg);
                        lastMSGsender.put(p, rec);
                    }

                } else {
                    String msg = "";
                    int l = 0;
                    List<String> list = Arrays.asList(args.clone());
                    for (String sto:list) {
                        if(l != 0) {
                            msg = msg + " " + sto;
                        }
                        l++;
                    }

                    p.sendMessage("§7[§aDu§7] §5> §7[§6" + args[0] + "§7] §5: §r" + msg);
                    rec.sendMessage("§7[§5" + p.getName() + "§7] §5> §7[§aDu§7] §5: §r" + msg);
                    lastMSGsender.put(p, rec);
                }


            } else {
                p.sendMessage(CustomServerUtil.prefix + "§cDieser Spieler ist nicht online. BungeeCord-weites MSG wird noch nicht unterstützt.");
            }

        } else {
            p.sendMessage(CustomServerUtil.prefix  + "§cBenutzung: /msg <Spieler> <Nachricht>");
        }



        return true;
    }
}
