package me.flyultra.forestMotd.utils.colors;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BungeeColorAPI {
    private static final HashMap<Character, BungeeColorAPI> COLORS_BY_CHAR = new HashMap<>();

    public static final Pattern x1;
    public static final Pattern x2;
    public static final Pattern x3;
    public static final Pattern x4;
    public static final Pattern x5;
    public static final Pattern x6;
    public static final Pattern x7;
    public static final Pattern x8;

    public static final BungeeColorAPI BLACK;
    public static final BungeeColorAPI DARK_BLUE;
    public static final BungeeColorAPI DARK_GREEN;
    public static final BungeeColorAPI DARK_AQUA;
    public static final BungeeColorAPI DARK_RED;
    public static final BungeeColorAPI DARK_PURPLE;
    public static final BungeeColorAPI GOLD;
    public static final BungeeColorAPI GRAY;
    public static final BungeeColorAPI DARK_GRAY;
    public static final BungeeColorAPI BLUE;
    public static final BungeeColorAPI GREEN;
    public static final BungeeColorAPI AQUA;
    public static final BungeeColorAPI RED;
    public static final BungeeColorAPI LIGHT_PURPLE;
    public static final BungeeColorAPI YELLOW;
    public static final BungeeColorAPI WHITE;
    public static final BungeeColorAPI OBFUSCATED;
    public static final BungeeColorAPI BOLD;
    public static final BungeeColorAPI STRIKETHROUGH;
    public static final BungeeColorAPI UNDERLINE;
    public static final BungeeColorAPI ITALIC;
    public static final BungeeColorAPI RESET;
    public static final BungeeColorAPI HEX;

    private char character;
    private boolean color;
    private boolean isReset;
    private int redChannel;
    private int greenChannel;
    private int blueChannel;
    private String hexCode;

    static {
        for (float i = 0.0F; i <= 1.0F; i = (float) ((double) i + 0.1D)) {
            for (float j = 0.1F; j <= 1.0F; j = (float) ((double) j + 0.1D)) {
                for (float k = 0.0F; k <= 1.0F; k = (float) ((double) k + 0.03D)) {
                    Color currentColor = Color.getHSBColor(k, i, j);
                    StringBuilder strBuilder = (new StringBuilder()).append(Integer.toHexString((currentColor.getRed() << 16) + (currentColor.getGreen() << 8) + currentColor.getBlue() & 16777215));

                    while (strBuilder.length() < 6) {
                        strBuilder.append("0").append(strBuilder);
                    }
                }
            }
        }

        x1 = Pattern.compile("(\\{#)([0-9A-Fa-f]{6}|[0-9A-Fa-f]{3})(\\})");
        x2 = Pattern.compile("(\\{#)([0-9A-Fa-f]{6}|[0-9A-Fa-f]{3})(\\})(?!.*\\{#)");
        x3 = Pattern.compile("((&|§)x)(((&|§)[0-9A-Fa-f]){6})");
        x4 = Pattern.compile("(\\{(#[^\\{]*?)>\\})(.*?)(\\{(#.*?)<(>?)\\})");
        x5 = Pattern.compile("((\\{#)([0-9A-Fa-f]{6}|[0-9A-Fa-f]{3})(\\})|(\\{#)([a-zA-Z_]{3,})(\\}))(.)((\\{#)([0-9A-Fa-f]{6}|[0-9A-Fa-f]{3})(\\})|(\\{#)([a-zA-Z_]{3,})(\\}))");
        x6 = Pattern.compile("((\\{#)([0-9A-Fa-f]{6}|[0-9A-Fa-f]{3})(\\})|(\\{#)([a-zA-Z_]{3,})(\\}))(.)(((\\{#)([0-9A-Fa-f]{6}|[0-9A-Fa-f]{3})(\\})|(\\{#)([a-zA-Z_]{3,})(\\}))(.))+");
        x7 = Pattern.compile("(&[0123456789abcdefklmnorABCDEFKLMNOR])|(\\{#)([0-9A-Fa-f]{6}|[0-9A-Fa-f]{3})(\\})|(\\{#)([a-zA-Z_]{3,})(\\})|(\\{@)([a-zA-Z_]{3,})(\\})");
        x8 = Pattern.compile("(&[klmnorKLMNOR])");

        BLACK = new BungeeColorAPI('0', 0, 0, 0);
        DARK_BLUE = new BungeeColorAPI('1', 0, 0, 170);
        DARK_GREEN = new BungeeColorAPI('2', 0, 170, 0);
        DARK_AQUA = new BungeeColorAPI('3', 0, 170, 170);
        DARK_RED = new BungeeColorAPI('4', 170, 0, 0);
        DARK_PURPLE = new BungeeColorAPI('5', 170, 0, 170);
        GOLD = new BungeeColorAPI('6', 255, 170, 0);
        GRAY = new BungeeColorAPI('7', 170, 170, 170);
        DARK_GRAY = new BungeeColorAPI('8', 85, 85, 85);
        BLUE = new BungeeColorAPI('9', 85, 85, 255);
        GREEN = new BungeeColorAPI('a', 85, 255, 85);
        AQUA = new BungeeColorAPI('b', 85, 255, 255);
        RED = new BungeeColorAPI('c', 255, 85, 85);
        LIGHT_PURPLE = new BungeeColorAPI('d', 255, 85, 255);
        YELLOW = new BungeeColorAPI('e', 255, 255, 85);
        WHITE = new BungeeColorAPI('f', 255, 255, 255);
        OBFUSCATED = new BungeeColorAPI('k', false);
        BOLD = new BungeeColorAPI('l', false);
        STRIKETHROUGH = new BungeeColorAPI('m', false);
        UNDERLINE = new BungeeColorAPI('n', false);
        ITALIC = new BungeeColorAPI('o', false);
        RESET = new BungeeColorAPI('r', false, true);
        HEX = new BungeeColorAPI('x', false, false);
    }

    public BungeeColorAPI(char character, int redChannel, int greenChannel, int blueChannel) {
        this(character, true, false, redChannel, greenChannel, blueChannel);
    }

    public BungeeColorAPI(String hexCode) {
        this.color = true;
        this.isReset = false;
        if (hexCode.startsWith("#")) {
            hexCode = hexCode.substring(1);
        }
        this.hexCode = hexCode;

        try {
            this.redChannel = Integer.valueOf(this.hexCode.substring(0, 2), 16);
            this.greenChannel = Integer.valueOf(this.hexCode.substring(2, 4), 16);
            this.blueChannel = Integer.parseInt(this.hexCode.substring(4, 6), 16);
        } catch (Exception ex) {
            this.hexCode = null;
        }
    }

    public BungeeColorAPI(char character, Boolean color) {
        this(character, color, false);
    }

    public BungeeColorAPI(char character, Boolean color, Boolean isReset) {
        this(character, color, isReset, -1, -1, -1);
    }

    public BungeeColorAPI(char character, Boolean color, Boolean isReset, int redChannel, int greenChannel, int blueChannel) {
        this.color = true;
        this.isReset = false;
        this.hexCode = null;
        this.character = character;
        this.color = color;
        this.isReset = isReset;
        this.redChannel = redChannel;
        this.greenChannel = greenChannel;
        this.blueChannel = blueChannel;
        COLORS_BY_CHAR.put(character, this);
    }

    public static String processGradient(String input) {
        Matcher matcher = x4.matcher(input);

        while (true) {
            String matcherGroup;
            BungeeColorAPI colorOne;
            BungeeColorAPI colorTwo;
            do {
                do {
                    if (!matcher.find()) {
                        return input;
                    }
                    matcherGroup = matcher.group();
                    colorOne = getColor("{#" + matcher.group(2).replace("#", "") + "}");
                    colorTwo = getColor("{#" + matcher.group(5).replace("#", "") + "}");
                } while (colorOne == null);
            } while (colorTwo == null);

            String var5 = matcher.group(3);
            boolean isEmpty = !matcher.group(6).isEmpty();
            StringBuilder stringBuilder = new StringBuilder();
            Set<BungeeColorAPI> formatsSet = getFormats(var5);
            var5 = stripColor(var5);

            for (int i = 0; i < var5.length(); ++i) {
                char var10 = var5.charAt(i);
                int var11 = var5.length();
                var11 = Math.max(var11, 2);
                double var12 = (double) i * 100.0D / (double) (var11 - 1);
                BungeeColorAPI var14 = mixColors(colorOne, colorTwo, var12);
                stringBuilder.append("{#").append(var14.getHex()).append("}");
                if (!formatsSet.isEmpty()) {
                    for (BungeeColorAPI format : formatsSet) {
                        stringBuilder.append("&").append(format.getChar());
                    }
                }
                stringBuilder.append(var10);
            }

            if (isEmpty) {
                stringBuilder.append("{#").append(matcher.group(5).replace("#", "")).append(">").append("}");
            }

            input = input.replace(matcherGroup, stringBuilder.toString());
            if (isEmpty) {
                input = processGradient(input);
            }
        }
    }

    public static String translate(String input) {
        if (input == null) {
            return null;
        }

        input = processGradient(input);
        if (!input.contains("{#")) {
            return ChatColor.translateAlternateColorCodes('&', input);
        }
        String str;
        StringBuilder stringBuilder;
        for (Matcher matcher = x1.matcher(input); matcher.find(); input = input.replace(str, stringBuilder.toString())) {
            str = matcher.group();
            stringBuilder = new StringBuilder("§x");

            char[] hexCharArray = str.substring(2, str.length() - 1).toCharArray();
            for (char value : hexCharArray) {
                stringBuilder.append('§').append(value);
                if (str.substring(2, str.length() - 1).length() == 3) {
                    stringBuilder.append('§').append(value);
                }
            }
        }

        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static String colorize(String input) {
        return input == null ? null : translate(input);
    }

    public static String deColorize(String input) {
        return deColorize(input, true);
    }

    public static String deColorize(String input, boolean translate) {
        if (input == null) {
            return null;
        }
        if (translate) {
            input = translate(input);
        }

        input = input.replace("§", "&");
        if (input.contains("&x")) {
            Matcher matcher = x3.matcher(input);

            while (matcher.find()) {
                String replacedStr = matcher.group(3).replace("&", "");
                input = input.replace(matcher.group(), "{#" + replacedStr + "}");
            }
        }

        return input;
    }

    public static String stripColor(String input) {
        if (input == null) {
            return null;
        }
        input = translate(input);
        return ChatColor.stripColor(input);
    }

    public String getBungeeColorCode() {
        return this.hexCode != null ? translate("{#" + this.hexCode + "}") : "§" + this.character;
    }

    public static Set<BungeeColorAPI> getFormats(String input) {
        input = input.replace("§", "&");
        HashSet<BungeeColorAPI> set = new HashSet<>();
        Matcher matcher = x8.matcher(input);

        while (matcher.find()) {
            String group = matcher.group();
            BungeeColorAPI bungeeColorAPI = getFormat(group);
            if (bungeeColorAPI != null && bungeeColorAPI.isFormat()) {
                set.add(bungeeColorAPI);
            }
        }

        return set;
    }

    public static BungeeColorAPI getFormat(String input) {
        if (input == null) {
            return null;
        }

        String deColorizedStr = deColorize(input);
        input = input.replace("§", "&");

        if (deColorizedStr.length() > 1 && String.valueOf(deColorizedStr.charAt(deColorizedStr.length() - 2)).equalsIgnoreCase("&")) {
            input = input.substring(input.length() - 1);
            for (Map.Entry<Character, BungeeColorAPI> characterChatColorAPIEntry : COLORS_BY_CHAR.entrySet()) {
                if (!String.valueOf(characterChatColorAPIEntry.getKey()).equalsIgnoreCase(input)) {
                    continue;
                }
                return characterChatColorAPIEntry.getValue().isFormat() ? characterChatColorAPIEntry.getValue() : null;
            }
        }

        return null;

    }

    public static BungeeColorAPI getColor(String input) {
        if (input == null) {
            return null;
        }

        String decolorizedStr = deColorize(input);
        if (decolorizedStr.contains("{#")) {
            Matcher matcher = x2.matcher(decolorizedStr);
            if (matcher.find()) {
                return new BungeeColorAPI(matcher.group(2));
            }
        }

        input = deColorize(input).replace("&", "");

        if (decolorizedStr.length() > 1 && String.valueOf(decolorizedStr.charAt(decolorizedStr.length() - 2)).equalsIgnoreCase("&")) {
            input = input.substring(input.length() - 1);

            for (Map.Entry<Character, BungeeColorAPI> characterChatColorAPIEntry : COLORS_BY_CHAR.entrySet()) {
                if (!String.valueOf(characterChatColorAPIEntry.getKey()).equalsIgnoreCase(input)) {
                    continue;
                }
                return characterChatColorAPIEntry.getValue();
            }
        }

        return null;
    }

    public static BungeeColorAPI mixColors(BungeeColorAPI colorOne, BungeeColorAPI colorTwo, double coef) {
        coef /= 100.0D;
        double oppositeCoef = 1.0D - coef;
        int red = (int) ((double) colorTwo.getRed() * coef + (double) colorOne.getRed() * oppositeCoef);
        int green = (int) ((double) colorTwo.getGreen() * coef + (double) colorOne.getGreen() * oppositeCoef);
        int blue = (int) ((double) colorTwo.getBlue() * coef + (double) colorOne.getBlue() * oppositeCoef);
        String inHex = String.format("#%02x%02x%02x", red, green, blue);
        return new BungeeColorAPI(inHex);
    }

    public String toString() {
        return this.getBungeeColorCode();
    }

    public char getChar() {
        return this.character;
    }

    public boolean isFormat() {
        return !this.color && !this.isReset;
    }

    public String getHex() {
        return this.hexCode;
    }

    public int getRed() {
        return this.redChannel;
    }

    public int getGreen() {
        return this.greenChannel;
    }

    public int getBlue() {
        return this.blueChannel;
    }
}

