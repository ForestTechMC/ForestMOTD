package me.flyultra.forestMotd.bungee.manager;

import me.flyultra.forestMotd.bungee.Bungee;
import me.flyultra.forestMotd.options.ConfigOptions;
import net.md_5.bungee.api.ProxyServer;

import java.util.List;

public class BungeeMOTDManager {
    private Bungee bungee;

    private String iconName;
    private int maxPlayers;
    private String motdText;
    private List<String> hoverBox;

    public BungeeMOTDManager() {
        this.bungee = Bungee.getInstance();
        loadData();
    }

    public void loadData() {
        iconName = bungee.getYamlFile("config").getString(ConfigOptions.MOTD_ICON_NAME);
        maxPlayers = bungee.getYamlFile("config").getInt(ConfigOptions.MOTD_MAX_PLAYERS);
        motdText = bungee.getYamlFile("config").getString(ConfigOptions.MOTD_TEXT);
        hoverBox = bungee.getYamlFile("config").getStringList(ConfigOptions.MOTD_HOVER_BOX);
    }

    public void changeIconName(String iconNameS) {
        iconName = iconNameS;
        bungee.getYamlFile("config").set(ConfigOptions.MOTD_ICON_NAME, iconNameS);
        bungee.saveYamlFiles("config");
    }

    public void setMaxPlayers(int maxPlayersI) {
        maxPlayers = maxPlayersI;
        bungee.getYamlFile("config").set(ConfigOptions.MOTD_MAX_PLAYERS, maxPlayersI);
        bungee.saveYamlFiles("config");
    }

    public void setMotdText(String motdTextS) {
        if (motdTextS == null) {
            bungee.getLogger().warning("MOTD text is empty!");
            return;
        }
        motdText = motdTextS;
        bungee.getYamlFile("config").set(ConfigOptions.MOTD_TEXT, motdTextS);
        bungee.saveYamlFiles("config");
    }


    public void setHoverBox(List<String> hoverBoxL) {
        if (hoverBoxL == null || hoverBoxL.size() == 0) {
            bungee.getLogger().warning("Hover list is empty!");
            return;
        }
        hoverBox = hoverBoxL;
        bungee.getYamlFile("config").set(ConfigOptions.MOTD_HOVER_BOX, hoverBoxL);
        bungee.saveYamlFiles("config");

    }

    public int getMaxPlayers() {
        if (maxPlayers == -1) {
            return ProxyServer.getInstance().getConfig().getPlayerLimit();
        }
        return maxPlayers;
    }

    public String getIconName() {
        if (iconName == null) {
            bungee.getLogger().warning("Icon name is null!");
        }
        return iconName;
    }

    public String getMotdText() {
        if (motdText == null) {
            bungee.getLogger().warning("MOTD Text is null!");
        }
        return motdText;
    }

    public List<String> getHoverBox() {
        return hoverBox;
    }


}
