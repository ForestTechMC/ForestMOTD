package me.flyultra.forestMotd.spigot;

import com.tchristofferson.configupdater.ConfigUpdater;
import lombok.SneakyThrows;
import me.flyultra.forestMotd.spigot.command.MOTDCommand;
import me.flyultra.forestMotd.spigot.completer.MOTDCompleter;
import me.flyultra.forestMotd.spigot.controller.MOTDController;
import me.flyultra.forestMotd.spigot.listener.MOTDListener;
import me.flyultra.forestMotd.spigot.manager.MOTDManager;
import net.md_5.bungee.api.Favicon;
import org.bstats.bukkit.Metrics;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Spigot extends JavaPlugin {
    private static Spigot instance;

    private MOTDManager motdManager;
    private MOTDController motdController;

    /*-----------------------------------------------------------------------------*/

    @Override
    public void onEnable() {
        instance = this;
        bStats();
        configSetup();
        controllers();
        commands();
        listeners();


        getLogger().info(" ");
        getLogger().info(" ForestMOTD v1.0.1 | Enabled");
        getLogger().info("  ");
        getLogger().info("   Author: Fly_Ultra");
        getLogger().info("   Version: 1.0.1");
        getLogger().info("   Spigot");
        getLogger().info(" ");
    }

    /*-----------------------------------------------------------------------------*/


    @Override
    public void onDisable() {
        getLogger().warning(" ");
        getLogger().warning(" ForestMOTD v1.0.1 | Enabled");
        getLogger().warning("  ");
        getLogger().warning("   Author: Fly_Ultra");
        getLogger().warning("   Version: 1.0.1");
        getLogger().warning("   Spigot");
        getLogger().warning(" ");
    }

    /*-----------------------------------------------------------------------------*/

    public void controllers() {
        motdManager = new MOTDManager();
        motdController = new MOTDController();
    }

    /*-----------------------------------------------------------------------------*/

    public void commands() {
        registerCommand(new MOTDCommand(), "forestmotd");
        registerCompleter(new MOTDCompleter(), "forestmotd");
    }

    /*-----------------------------------------------------------------------------*/

    public void listeners() {
        registerListener(new MOTDListener());
    }

    /*-----------------------------------------------------------------------------*/

    public void configSetup() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        saveDefaultConfig();
        File file = new File(getInstance().getDataFolder() + "/config.yml");
        try {
            ConfigUpdater.update(this, "config.yml", file, new ArrayList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        reloadConfig();
    }

    /*-----------------------------------------------------------------------------*/

    public void bStats() {
        int pluginId = 15803;
        Metrics metrics = new Metrics(this, pluginId);
    }

    /*-----------------------------------------------------------------------------*/

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener,this);

    }

    /*-----------------------------------------------------------------------------*/

    public void registerCommand(CommandExecutor command, String commandName) {
        getCommand(commandName).setExecutor(command);

    }

    /*-----------------------------------------------------------------------------*/

    public void registerCompleter(TabCompleter tabCompleter, String commandName) {
        getCommand(commandName).setTabCompleter(tabCompleter);

    }

    /*-----------------------------------------------------------------------------*/

    public static Spigot getInstance() {
        return instance;
    }

    public MOTDManager getMotdManager() {
        return motdManager;
    }

    public MOTDController getMotdController() {
        return motdController;
    }
}
