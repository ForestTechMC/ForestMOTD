package me.flyultra.forestMotd.spigot.controller;

import me.flyultra.forestMotd.options.ConfigOptions;
import me.flyultra.forestMotd.spigot.Messages;
import me.flyultra.forestMotd.spigot.Spigot;
import me.flyultra.forestMotd.utils.Utils;
import org.bukkit.entity.Player;

public class MOTDController {
    private Spigot spigot;

    public MOTDController() {
        this.spigot = Spigot.getInstance();

    }

    public void reload(Player player) {
        spigot.saveConfig();
        spigot.getMotdManager().loadData();
        player.sendMessage(Utils.colorize(Messages.sendConfigMessage(ConfigOptions.RELOAD)));
    }

    public void noPerm(Player player) {
        player.sendMessage(Utils.colorize(Messages.sendConfigMessage(ConfigOptions.NO_PERM)));
    }



}
