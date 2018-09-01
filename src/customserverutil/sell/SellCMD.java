package customserverutil.sell;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SellCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;


        if(args.length ==1 ) {
            p.sendMessage("höhö");
            p.openInventory(SellGUI.createMainGUI(args[0]));
        }



        return true;
    }
}
