package me.flyultra.forestMotd.bungee.command;

import me.flyultra.forestMotd.bungee.Bungee;
import me.flyultra.forestMotd.options.Permissions;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BungeeMOTDCommand extends Command implements TabExecutor {
    private Bungee bungee;

    public BungeeMOTDCommand() {
        super("forestMOTD", "forestMOTD.admin");
        this.bungee = Bungee.getInstance();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (!player.hasPermission(Permissions.FORESTMOTD_ADMIN)) {
            bungee.getBungeeMOTDController().sendNoPerm(player);
            return;
        }

        if (args.length == 0) {
            return;
        }

        if (args[0].equalsIgnoreCase("reload") && args.length ==1) {
            bungee.getBungeeMOTDController().reload(player);
            return;
        }

    }


    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        List<String> completer = new ArrayList<>();

        if (args.length == 1) {
            completer.add("reload");
            return completer.stream().filter(val -> val.toLowerCase().startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        }

        return completer;
    }
}
