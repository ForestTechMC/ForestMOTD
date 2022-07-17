package me.flyultra.forestMotd.bungee.listener;

import me.flyultra.forestMotd.bungee.Bungee;
import me.flyultra.forestMotd.utils.Utils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;

public class BungeeMOTDListener implements Listener {
    private Bungee bungee;

    public BungeeMOTDListener() {
        this.bungee = Bungee.getInstance();
    }

    @EventHandler
    public void onBungeePing(ProxyPingEvent e) {
        ServerPing ping = e.getResponse();
        ping.setDescription(Utils.colorizeBungee(bungee.getBungeeMOTDManager().getMotdText()
                .replace(":n:", "\n")
                .replace("%online%", String.valueOf(ProxyServer.getInstance().getOnlineCount()))
                .replace("%online%", ProxyServer.getInstance().getVersion())
                .replace("%configPlayersMax%", String.valueOf(bungee.getBungeeMOTDManager().getMaxPlayers()))
                .replace("%defaultMax%", String.valueOf(ProxyServer.getInstance().getConfig().getPlayerLimit()))));
        ping.setPlayers(new ServerPing.Players(bungee.getBungeeMOTDManager().getMaxPlayers(), ProxyServer.getInstance().getOnlineCount(), null));
        ping.setFavicon(bungee.getFavicon());

        if (bungee.getBungeeMOTDManager().getHoverBox().size() != 0) {
            ping.getPlayers().setSample(getHoverBoxProfiles());
        }

        e.setResponse(ping);
    }

    public ServerPing.PlayerInfo[] getHoverBoxProfiles() {
        int l = bungee.getBungeeMOTDManager().getHoverBox().size();
        ServerPing.PlayerInfo[] lines = new ServerPing.PlayerInfo[l];
        for (int i = 0; i < l; i++) {
            lines[i] = new ServerPing.PlayerInfo(Utils.colorizeBungee(bungee.getBungeeMOTDManager().getHoverBox().get(i)
                    .replace(":n:", "\n")
                    .replace("%online%", String.valueOf(ProxyServer.getInstance().getOnlineCount()))
                    .replace("%online%", ProxyServer.getInstance().getVersion())
                    .replace("%configPlayersMax%", String.valueOf(bungee.getBungeeMOTDManager().getMaxPlayers()))
                    .replace("%defaultMax%", String.valueOf(ProxyServer.getInstance().getConfig().getPlayerLimit()))), new UUID(0L, 0L));
        }
        return lines;
    }

}



