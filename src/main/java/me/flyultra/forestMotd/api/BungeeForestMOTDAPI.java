package me.flyultra.forestMotd.api;

import me.flyultra.forestMotd.bungee.Bungee;

import java.util.List;

public class BungeeForestMOTDAPI {

    public static void reloadData() {
        Bungee.getInstance().getBungeeMOTDManager().loadData();
    }

    public static void changeMOTD(String motdText) {
        Bungee.getInstance().getBungeeMOTDManager().setMotdText(motdText);
    }

    public static void changeIcon(String iconName) {
        Bungee.getInstance().getBungeeMOTDManager().changeIconName(iconName);
    }

    public static void changeMaxPlayers(int maxPlayers) {
        Bungee.getInstance().getBungeeMOTDManager().setMaxPlayers(maxPlayers);
    }

    public static void changeHoverBox(List<String> list) {
        Bungee.getInstance().getBungeeMOTDManager().setHoverBox(list);
    }


}
