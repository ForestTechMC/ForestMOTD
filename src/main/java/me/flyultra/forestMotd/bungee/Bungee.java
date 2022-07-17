package me.flyultra.forestMotd.bungee;


import com.tchristofferson.configupdater.ConfigUpdater;
import lombok.SneakyThrows;
import me.flyultra.forestMotd.bungee.command.BungeeMOTDCommand;
import me.flyultra.forestMotd.bungee.controller.BungeeMOTDController;
import me.flyultra.forestMotd.bungee.listener.BungeeMOTDListener;
import me.flyultra.forestMotd.bungee.manager.BungeeMOTDManager;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import org.bstats.bungeecord.Metrics;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Bungee extends Plugin {
    private static Bungee instance;


    private BungeeMOTDManager bungeeMOTDManager;
    private BungeeMOTDController bungeeMOTDController;
    private Favicon favicon;


    @Override
    public void onEnable() {
        instance = this;
        createNewYamlFiles("config");
        controllers();
        bStats();
        listeners();
        commands();
        faviconLoader();

        getLogger().info(" ");
        getLogger().info(" ForestMOTD v1.0.1 | Enabled");
        getLogger().info("  ");
        getLogger().info("   Author: Fly_Ultra");
        getLogger().info("   Version: 1.0.1");
        getLogger().info("   Bungee");
        getLogger().info(" ");
    }

    public void faviconLoader() {
        faviconSetup(getBungeeMOTDManager().getIconName());
    }

    @Override
    public void onDisable() {
        getLogger().warning(" ");
        getLogger().warning(" ForestMOTD v1.0.1 | Disabled");
        getLogger().warning("  ");
        getLogger().warning("   Author: Fly_Ultra");
        getLogger().warning("   Version: 1.0.1");
        getLogger().warning("   Bungee");
        getLogger().warning(" ");
    }

    /*-----------------------------------------------------------------------------*/

    public void controllers() {
        bungeeMOTDManager = new BungeeMOTDManager();
        bungeeMOTDController = new BungeeMOTDController();
    }

    /*-----------------------------------------------------------------------------*/

    public void commands() {
        registerCommands(new BungeeMOTDCommand());
    }

    /*-----------------------------------------------------------------------------*/

    public void listeners() {
        registerListeners(new BungeeMOTDListener());
    }

    /*-----------------------------------------------------------------------------*/

    /**
     * Method for load icon for server
     *
     * @param faviconName name of icon
     */

    public void faviconSetup(String faviconName) {
        try {
            favicon = Favicon.create(ImageIO.read(new File(faviconName)));
            if (favicon == null) {
                getLogger().warning("Icon cant be loaded! (Wrong input)");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*-----------------------------------------------------------------------------*/

    /**
     * You can use it for copy to Bungee folder
     * createNewYamlFiles(files names | "config", "messages", "motd")
     *
     * @param fileNameData name of file
     */
    public void createNewYamlFiles(String... fileNameData) {
        if (!Bungee.getInstance().getDataFolder().exists())
            Bungee.getInstance().getDataFolder().mkdir();

        for (String fileName : fileNameData) {
            File file = new File(Bungee.getInstance().getDataFolder(), fileName + ".yml");
            if (!file.exists()) {
                try (InputStream in = Bungee.getInstance().getResourceAsStream(fileName + ".yml")) {
                    Files.copy(in, file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                saveYamlFiles("config");
            }
            saveYamlFiles("config");
        }
    }

    /*-----------------------------------------------------------------------------*/

    /**
     * Here you can save config
     * <p>
     * saveYamlFiles()
     *
     * @param fileNames names of files
     */
    @SneakyThrows
    public void saveYamlFiles(String... fileNames) {
        for (String fileName : fileNames) {
            ConfigurationProvider
                    .getProvider(YamlConfiguration.class)
                    .load(new File(Bungee.getInstance().getDataFolder(), fileName + ".yml"));
        }
    }

    /*-----------------------------------------------------------------------------*/

    /**
     * This can call file
     * <p>
     * getYamlFile("config").getString("")
     *
     * @param fileName file name of file you want to operate
     * @return
     */
    @SneakyThrows
    public Configuration getYamlFile(String fileName) {
        Configuration config = ConfigurationProvider
                .getProvider(YamlConfiguration.class)
                .load(new File(Bungee.getInstance().getDataFolder(), fileName + ".yml"));
        return config;
    }

    /*-----------------------------------------------------------------------------*/

    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            ProxyServer.getInstance().getPluginManager().registerListener(this, listener);
        }
    }

    /*-----------------------------------------------------------------------------*/

    public void registerCommands(Command... commands) {
        for (Command command : commands) {
            ProxyServer.getInstance().getPluginManager().registerCommand(this, command);
        }
    }

    /*-----------------------------------------------------------------------------*/


    public void bStats() {
        int pluginId = 14541;
        Metrics metrics = new Metrics(this, pluginId);
    }

    public static Bungee getInstance() {
        return instance;
    }

    public BungeeMOTDManager getBungeeMOTDManager() {
        return bungeeMOTDManager;
    }

    public BungeeMOTDController getBungeeMOTDController() {
        return bungeeMOTDController;
    }

    public Favicon getFavicon() {
        return favicon;
    }
}
