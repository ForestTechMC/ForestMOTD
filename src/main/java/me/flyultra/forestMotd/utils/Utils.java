package me.flyultra.forestMotd.utils;

import me.flyultra.forestMotd.utils.colors.BungeeColorAPI;
import me.flyultra.forestMotd.utils.colors.ColorAPI;

public class Utils {

    public static String colorize(String message) {
        return ColorAPI.colorize(message);
    }

    public static String colorizeBungee(String message) {
        return BungeeColorAPI.colorize(message);
    }


}
