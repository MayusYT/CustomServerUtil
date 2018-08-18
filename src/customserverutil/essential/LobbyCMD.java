package customserverutil.essential;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import customserverutil.CustomServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCMD implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if(sender instanceof Player) {
            Player p = (Player)sender;
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("lobby");

            p.sendPluginMessage(CustomServerUtil.getInstance(), "BungeeCord", out.toByteArray());
        }

        return true;
    }
}
