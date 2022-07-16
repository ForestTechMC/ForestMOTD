package me.flyultra.forestMotd.spigot;

public class Messages {
    private Spigot spigot;

    public Messages() {
        this.spigot = Spigot.getInstance();

    }

    public static String sendConfigMessage(String path) {
        return Spigot.getInstance().getConfig().getString(path);
    }

}
