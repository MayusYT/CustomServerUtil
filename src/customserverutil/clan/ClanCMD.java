package customserverutil.clan;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClanCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length == 0) {
            prodcWithoutArgs(sender);
        }

        return true;
    }



    private void prodcWithoutArgs(CommandSender sender) {

        if(sender instanceof Player) {



        }

    }
}
