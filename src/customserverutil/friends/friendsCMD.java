package customserverutil.friends;

import customserverutil.CustomServerUtil;
import customserverutil.SQL.SQLConfig;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class friendsCMD implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        CustomServerUtil.getInstance().saveConfig();
        CustomServerUtil.getInstance().reloadConfig();
        SQLConfig conf = new SQLConfig();
        conf.initialize(CustomServerUtil.getInstance().getConfig().getString("SQL.host"), CustomServerUtil.getInstance().getConfig().getString("SQL.user"), CustomServerUtil.getInstance().getConfig().getString("SQL.pw"), CustomServerUtil.getInstance().getConfig().getString("SQL.db"));
        if (conf.canConnect()) {

            if (args.length == 2) {
                CustomServerUtil.getInstance().saveConfig();
                CustomServerUtil.getInstance().reloadConfig();
                if (args[0].equalsIgnoreCase("add")) {
                    if (args.length == 2) {
                        Player rec = Bukkit.getPlayer(args[1]);
                        if (rec != null) {

                            /*
                            List<String> requests = CustomLobby.CustomServerUtil.getInstance().getConfig().getStringList("player." + sender.getName() + ".requests");
                            sender.sendMessage(String.valueOf(requests));
                            if (requests == null) {
                                requests = new ArrayList<String>();
                                requests.add(rec.getName());
                            } else {
                                requests.add(rec.getName());
                            }
                            CustomLobby.CustomServerUtil.getInstance().getConfig().set("player." + sender.getName() + ".requests", requests);*/
                            conf.addFriendReq(sender.getName(), rec.getName());
                            CustomServerUtil.getInstance().saveConfig();
                            CustomServerUtil.getInstance().reloadConfig();
                            sender.sendMessage(CustomServerUtil.prefix + "§aDu hasst eine Freundschaftsanfrage an §6" + rec.getName() + "§a geschickt.");
                            rec.sendMessage(CustomServerUtil.prefix + "§aDu hasst eine Freundschaftsanfrage von §6" + sender.getName() + "§a bekommen!");
                            //rec.sendMessage(CustomServerUtil.prefix + "§aNutze §7/§6friend accept " + sender.getName() + " §aum seine anfrage anzunehmen");
                            TextComponent message1 = new TextComponent( "[§aAnnehmen§r] " );
                            message1.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/friend accept " + sender.getName()) );
                            rec.spigot().sendMessage(message1);
                        } else {
                            sender.sendMessage(CustomServerUtil.prefix + "§cDieser Spieler wurde nicht gefunden!");
                        }
                    } else {
                        printHelp(sender);
                    }
                }

                if (args[0].equalsIgnoreCase("accept")) {
                    if (args.length == 2) {
                        CustomServerUtil.getInstance().saveConfig();
                        CustomServerUtil.getInstance().reloadConfig();
                        Player p = Bukkit.getPlayer(args[1]);
                        boolean friended = false;
                        //List<String> friends = CustomLobby.CustomServerUtil.getInstance().getConfig().getStringList("player." + p.getName() + ".friends");
                        List<String> friends = null;
                        try {
                            friends = conf.getFriends(sender.getName());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        if (friends != null) {
                            if (friends.contains(p.getName())) {
                                friended = true;
                            }
                        }
                        if (!friended) {
                            CustomServerUtil.getInstance().saveConfig();
                            CustomServerUtil.getInstance().reloadConfig();
                            if (p != null) {
                                boolean gotrequest = false;


                                //List<String> requests = CustomLobby.CustomServerUtil.getInstance().getConfig().getStringList("player." + sender.getName() + ".requests");
                                List<String> requests = null;
                                try {
                                    requests = conf.getFriendReqs(sender.getName());
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }


                                if (requests != null) {

                                    for (int i = 0; i < requests.size(); i++) {
                                        if (requests.get(i).equalsIgnoreCase(p.getName())) {
                                            p.sendMessage(requests.get(i));
                                            sender.sendMessage(requests.get(i));
                                            gotrequest = true;
                                            break;
                                        }
                                    }
                                    if (gotrequest = true) {
                                        p.sendMessage("MakeFriend");
                                        sender.sendMessage("MakeFriend");
                                        friendsUtil.makeFriend((Player) sender, p);
                                        friendsUtil.makeFriend(p, (Player) sender);

                                    } else {
                                        sender.sendMessage(CustomServerUtil.prefix + "§cDu hast keine Anfrage dieser Person");
                                    }
                                } else {
                                    sender.sendMessage(CustomServerUtil.prefix + "§cDu hast keine Anfrage dieser Person");
                                }



                            } else {
                                sender.sendMessage(CustomServerUtil.prefix + "§cDieser Spieler wurde nicht gefunden!");
                            }
                        } else {
                            sender.sendMessage(CustomServerUtil.prefix + "§cDu bist bereits mit diesem Spieler befreundet");
                        }
                    } else {
                        printHelp(sender);
                    }
                }
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    CustomServerUtil.getInstance().saveConfig();
                    CustomServerUtil.getInstance().reloadConfig();
                    List<String> output = new ArrayList<String>();
                    output.add(CustomServerUtil.prefix + "§6Deine Freunde:");
                    List<String> friends = null;
                    try {
                        friends = conf.getFriends(sender.getName());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (friends.size() == 0) {
                        output.remove(0);
                        output.add(CustomServerUtil.prefix + "§6Du hast aktuell keine Freunde§7! §6Nutze §7/§2friend add NAME §6 um eine Freundschaftsanfrage zu schicken!");
                    } else {
                        for (String name : friends) {
                            Player p = Bukkit.getPlayer(name);
                            if (p != null) {
                                output.add(CustomServerUtil.prefix + "    §7- §3" + p.getName() + " §7(§aOnline§7)");
                            } else {
                                boolean b = false;
                                for (OfflinePlayer off : Bukkit.getOfflinePlayers()) {
                                    if (off.getName().equalsIgnoreCase(name)) {
                                        b = true;
                                        output.add(CustomServerUtil.prefix + "    §7- §3" + off.getName() + " §7(§cOffline§7)");
                                        break;
                                    }
                                }
                                if (!b) {
                                    output.add(CustomServerUtil.prefix + "    §7- §3Unkown §7(§cDataBaseError§7) §aBitte melde dich bei einem Administrator");
                                }
                            }
                        }
                    }
                    for (String row : output) {
                        sender.sendMessage(row);
                    }
                }
            }

            if (args.length >= 1 && args.length <= 2) {
                if (!args[0].equalsIgnoreCase("add") && !args[0].equalsIgnoreCase("accept") && !args[0].equalsIgnoreCase("list")) {
                    printHelp(sender);
                }
            } else {
                printHelp(sender);
            }
            return true;
        } else {
            sender.sendMessage(CustomServerUtil.prefix + "§cDiese Funktion ist deaktiviert, da die Database nicht aktiviert ist! Bitte melde dich bei einem Server Administrator!");
        }
        return true;
    }
        public void printHelp (CommandSender sender){
            sender.sendMessage(CustomServerUtil.prefix + "§cUsage: §7/§6friend §7<§6add§7/§6accept§7> <§6Name§7>");
        }


}
