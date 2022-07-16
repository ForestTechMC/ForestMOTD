package me.flyultra.forestMotd.spigot.listener;

import me.flyultra.forestMotd.spigot.Spigot;
import me.flyultra.forestMotd.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;

public class MOTDListener implements Listener {
    private Spigot spigot;

    public MOTDListener() {
        this.spigot = Spigot.getInstance();
    }

    @EventHandler
    public void onPing(ServerListPingEvent e) {

        e.setMotd(Utils.colorize(spigot.getMotdManager().getMotdText()));
        e.setMaxPlayers(spigot.getMotdManager().getMaxPlayers());
        try {
            e.setServerIcon(Bukkit.loadServerIcon(new File(spigot.getMotdManager().getIconName())));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
