package me.flyultra.forestMotd.api;

import me.flyultra.forestMotd.spigot.Spigot;

import java.util.List;

public class SpigotForestMOTDAPI {

    public static void reloadData() {
        Spigot.getInstance().getMotdManager().loadData();
    }

    public static void changeMOTD(String motdText) {
        Spigot.getInstance().getMotdManager().setMotdText(motdText);
    }

    public static void changeIcon(String iconName) {
        Spigot.getInstance().getMotdManager().changeIconName(iconName);
    }

    public static void changeMaxPlayers(int maxPlayers) {
        Spigot.getInstance().getMotdManager().setMaxPlayers(maxPlayers);
    }

    public static void changeHoverBox(List<String> hoverBox) {
        Spigot.getInstance().getMotdManager().setHoverBox(hoverBox);
    }


}
