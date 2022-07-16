package me.flyultra.forestMotd.bungee.controller;

import me.flyultra.forestMotd.bungee.Bungee;
import me.flyultra.forestMotd.bungee.BungeeMessages;
import me.flyultra.forestMotd.options.ConfigOptions;
import me.flyultra.forestMotd.spigot.Messages;
import me.flyultra.forestMotd.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeMOTDController {
    private Bungee bungee;

    public BungeeMOTDController() {
        this.bungee = Bungee.getInstance();
    }

    public void sendNoPerm(ProxiedPlayer player) {
        player.sendMessage(Utils.colorizeBungee(BungeeMessages.sendConfigMessage(ConfigOptions.NO_PERM)));
    }

    public void reload(ProxiedPlayer player) {
        bungee.saveYamlFiles("config");
        bungee.getBungeeMOTDManager().loadData();
        player.sendMessage(Utils.colorize(Messages.sendConfigMessage(ConfigOptions.RELOAD)));
    }

}
