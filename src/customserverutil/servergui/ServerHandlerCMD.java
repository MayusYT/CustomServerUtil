package customserverutil.servergui;

import customserverutil.servergui.ServerGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ServerHandlerCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
            p.openInventory(ServerGUI.createNavGUI());
        return true;
    }
}
