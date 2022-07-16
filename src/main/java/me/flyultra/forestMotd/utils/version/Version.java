package me.flyultra.forestMotd.utils.version;

import org.bukkit.Bukkit;

public enum Version {

    v1_7_R1,
    v1_7_R2,
    v1_7_R3,
    v1_7_R4,
    v1_8_R1,
    v1_8_R2,
    v1_8_R3,
    v1_9_R1,
    v1_9_R2,
    v1_10_R1,
    v1_11_R1,
    v1_12_R1,
    v1_13_R1,
    v1_13_R2,
    v1_13_R3,
    v1_14_R1,
    v1_14_R2,
    v1_15_R1,
    v1_15_R2,
    v1_16_R1,
    v1_16_R2,
    v1_16_R3,
    v1_17_R1,
    v1_17_R2,
    v1_18_R1,
    v1_18_R2,
    v1_19_R1,
    v1_19_R2,
    v1_20_R1,
    v1_20_R2;

    private static Version currentVersion = null;
    private Integer value;

    static {
        loadCurrentVersion();
    }

    public static void loadCurrentVersion() {
        if (currentVersion != null) {
            return;
        }
        String[] packagePathComponents = Bukkit.getServer().getClass().getPackage().getName().split("\\.");
        String currentVersionName = packagePathComponents[packagePathComponents.length - 1];

        for (Version version : values()) {
            if (!version.name().equalsIgnoreCase(currentVersionName)) {
                continue;
            }
            currentVersion = version;
            break;
        }
    }

    public static boolean isCurrentVersionLowerThan(Version checkedVersion) {
        return currentVersion.getValue() < checkedVersion.getValue();
    }

    Version() {
        try {
            this.value = Integer.valueOf(this.name().replaceAll("[^\\d.]", ""));
        } catch (Exception ignored) {
        }
    }

    public Integer getValue() {
        return this.value;
    }

}

