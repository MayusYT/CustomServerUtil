package customserverutil.essential;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.Arrays;
import java.util.List;

public class ForceCMD implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if(PermissionsEx.getUser(p).inGroup("Inhaber")) {
            if (args.length >= 2) {
                if(Bukkit.getPlayer(args[0]) != null) {
                    String msg ="";
                    int l = 1;
                    for (String sto : args) {
                        if(l != 0) {
                            msg = msg + " " + sto;
                        }
                        l++;
                    }
                    Bukkit.dispatchCommand(Bukkit.getPlayer(args[0]), msg);
                }
            }
        }



        return true;
    }
}
