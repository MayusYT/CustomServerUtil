package customserverutil.chat;

import customserverutil.CustomServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ReplyCMD implements CommandExecutor {
//Does not run
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player)commandSender;
        if(args.length >= 1) {
            if(MsgCMD.lastMSGsender.containsKey(p) || MsgCMD.lastMSGsender.containsValue(p)) {
                try {
                    MsgCMD.lastMSGsender.remove(p);

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
                    MsgCMD.lastMSGsender.get(p).sendMessage("§7[§5" + p.getName() + "§7] §5> §7[§aDu§7] §5: §r" + msg);
                    MsgCMD.lastMSGsender.remove(p);
                } catch (Exception e) {
                    p.sendMessage(CustomServerUtil.prefix + "§cDu hast keine Nachricht bekommen!");
                    return true;
                }

            }
        } else {
            p.sendMessage(CustomServerUtil.prefix + "§cBenutzung: /r <Nachricht>");
        }


        return true;

    }
}
