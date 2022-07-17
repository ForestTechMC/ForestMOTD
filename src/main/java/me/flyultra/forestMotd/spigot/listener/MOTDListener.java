package me.flyultra.forestMotd.spigot.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerOptions;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import me.flyultra.forestMotd.spigot.Spigot;
import me.flyultra.forestMotd.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MOTDListener implements Listener {
    private Spigot spigot;

    public MOTDListener() {
        this.spigot = Spigot.getInstance();
    }

    @EventHandler
    public void onPing(ServerListPingEvent e) {

        e.setMotd(Utils.colorize(spigot.getMotdManager().getMotdText().replace(":n:", "\n")));
        e.setMaxPlayers(spigot.getMotdManager().getMaxPlayers());
        try {
            e.setServerIcon(Bukkit.loadServerIcon(new File(spigot.getMotdManager().getIconName())));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (spigot.getMotdManager().getHoverBox().size() != 0) {
            List<WrappedGameProfile> lines = getHoverBoxData();
            ProtocolManager pm = ProtocolLibrary.getProtocolManager();

            pm.addPacketListener(new PacketAdapter(spigot, ListenerPriority.NORMAL,
                    Arrays.asList(PacketType.Status.Server.OUT_SERVER_INFO), ListenerOptions.ASYNC) {
                @Override
                public void onPacketSending(PacketEvent event) {
                    event.getPacket().getServerPings().read(0).setPlayers(lines);
                }
            });
            return;
        }
    }

    public List<WrappedGameProfile> getHoverBoxData() {
        List<WrappedGameProfile> lines = new ArrayList<WrappedGameProfile>();
        for (int i = 0; i < spigot.getMotdManager().getHoverBox().size(); i++) {
            lines.add(new WrappedGameProfile(String.valueOf(i), Utils.colorize(spigot.getMotdManager().getHoverBox().get(i))));
        }


        return lines;
    }


}
