package me.flyultra.forestMotd.spigot.completer;

import me.flyultra.forestMotd.spigot.Spigot;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MOTDCompleter implements TabCompleter {
    private Spigot spigot;

    public MOTDCompleter() {
        this.spigot = Spigot.getInstance();
    }

    @Override
    public List<String> onTabComplete( CommandSender sender, Command cmd, String s, String[] args) {
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList("reload"), new ArrayList<>());
        }

        return new ArrayList<>();
    }
}
