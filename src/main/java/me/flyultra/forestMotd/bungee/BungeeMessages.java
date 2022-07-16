package me.flyultra.forestMotd.bungee;

public class BungeeMessages {
    private Bungee bungee;

    public BungeeMessages() {
        this.bungee = Bungee.getInstance();

    }

    public static String sendConfigMessage(String path) {
        return Bungee.getInstance().getYamlFile("config").getString(path);
    }
}
