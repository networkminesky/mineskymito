package net.minesky;

import net.minesky.commands.MitoCommand;
import net.minesky.commands.SetMitoCommand;
import net.minesky.events.Events;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class MineSkyMito extends JavaPlugin {

    public static FileConfiguration config;
    public static MineSkyMito instance;

    public static MineSkyMito getInstance() {
        return instance;
    }

    public void onEnable() {

        if (!new File(getDataFolder(), "config.yml").exists()) {
            this.saveDefaultConfig();
        }

        config = this.getConfig();
        instance = this;

        this.getCommand("mito").setExecutor(new MitoCommand());
        this.getCommand("setmito").setExecutor(new SetMitoCommand());

        getServer().getPluginManager().registerEvents(new Events(), this);

    }
}
