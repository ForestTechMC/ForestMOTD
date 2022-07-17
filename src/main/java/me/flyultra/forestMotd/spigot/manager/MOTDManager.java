package me.flyultra.forestMotd.spigot.manager;

import me.flyultra.forestMotd.options.ConfigOptions;
import me.flyultra.forestMotd.spigot.Spigot;

import java.util.List;

public class MOTDManager {
    private Spigot spigot;

    private String iconName;
    private int maxPlayers;
    private String motdText;
    private List<String> hoverBox;

    public MOTDManager() {
        this.spigot = Spigot.getInstance();
        loadData();
    }

    public void loadData() {
        iconName = spigot.getConfig().getString(ConfigOptions.MOTD_ICON_NAME);
        maxPlayers = spigot.getConfig().getInt(ConfigOptions.MOTD_MAX_PLAYERS);
        motdText = spigot.getConfig().getString(ConfigOptions.MOTD_TEXT);
        hoverBox = spigot.getConfig().getStringList(ConfigOptions.MOTD_HOVER_BOX);
    }

    public void changeIconName(String iconNameS) {
        iconName = iconNameS;
        spigot.getConfig().set(ConfigOptions.MOTD_ICON_NAME, iconNameS);
        spigot.saveConfig();
    }

    public void setMaxPlayers(int maxPlayersI) {
        maxPlayers = maxPlayersI;
        spigot.getConfig().set(ConfigOptions.MOTD_MAX_PLAYERS, maxPlayersI);
        spigot.saveConfig();
    }

    public void setMotdText(String motdTextS) {
        motdText = motdTextS;
        spigot.getConfig().set(ConfigOptions.MOTD_TEXT, motdTextS);
        spigot.saveConfig();
    }

    public void setHoverBox(List<String> hoverBoxL) {
        hoverBox = hoverBoxL;
        spigot.getConfig().set(ConfigOptions.MOTD_HOVER_BOX, hoverBoxL);
        spigot.saveConfig();
    }

    public int getMaxPlayers() {
        if (maxPlayers == -1) {
            return spigot.getServer().getMaxPlayers();
        }
        return maxPlayers;
    }

    public String getIconName() {
        if (iconName == null) {
            spigot.getLogger().warning("Icon name is null!");
        }
        return iconName;
    }

    public String getMotdText() {
        if (motdText == null) {
            spigot.getLogger().warning("MOTD Text is null!");
        }
        return motdText;
    }

    public List<String> getHoverBox() {
        if (hoverBox == null || hoverBox.size() == 0) {
            spigot.getLogger().warning("Hover box is null!");
        }
        return hoverBox;
    }
}
