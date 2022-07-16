package me.flyultra.forestMotd.spigot.command;

import me.flyultra.forestMotd.options.Permissions;
import me.flyultra.forestMotd.spigot.Spigot;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MOTDCommand implements CommandExecutor {
    private Spigot spigot;

    public MOTDCommand() {
        this.spigot = Spigot.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player player = (Player) sender;

        if (!player.hasPermission(Permissions.FORESTMOTD_ADMIN)) {
            spigot.getMotdController().noPerm(player);
            return true;
        }

        if (args.length == 0) {
            return true;
        }

        if (args[0].equalsIgnoreCase("reload") && args.length ==1) {
            spigot.getMotdController().reload(player);
            return true;
        }
        return true;
    }
}
