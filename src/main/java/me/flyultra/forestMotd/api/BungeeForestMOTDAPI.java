package me.flyultra.forestMotd.api;

import me.flyultra.forestMotd.bungee.Bungee;

public class BungeeForestMOTDAPI {

    public static void reloadData() {
        Bungee.getInstance().getBungeeMOTDManager().loadData();
    }


}
