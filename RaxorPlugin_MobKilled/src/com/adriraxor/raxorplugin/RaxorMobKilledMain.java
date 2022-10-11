package com.adriraxor.raxorplugin;

import com.adriraxor.raxorplugin.economy.MobKilled;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.logging.Logger;

public class RaxorMobKilledMain extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;

    @Override
    public void onEnable() {
        super.onEnable();
        saveDefaultConfig();

        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found! (Please install Vault!)", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if(!setupRaxorMain()){
            log.severe(ChatColor.RED + "RaxorMain non détecté, le plugin a été desactivé.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        System.out.println(ChatColor.RED + "" + ChatColor.BOLD + "#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#\n");
        System.out.println(ChatColor.GOLD + "" + ChatColor.BOLD + "[RaxorPlugin - MobKilled] Le plugin vient de s'allumé\n");
        System.out.println(ChatColor.GOLD + "" + ChatColor.BOLD + "@Author : Adriraxor\n");
        System.out.println(ChatColor.GOLD + "" + ChatColor.BOLD + "@Desc : Plugin economie\n");
        System.out.println(ChatColor.RED + "" + ChatColor.BOLD + "#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#\n");

        getServer().getPluginManager().registerEvents(new Event(), this);
        getServer().getPluginManager().registerEvents(new MobKilled(this), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        System.out.println("#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#\n");
        System.out.println("[RaxorPlugin - MobKilled] Le plugin vient de s'éteindre\n");
        System.out.println("#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#\n");
    }

    /**
     * Methodes pour init l'economie de Vault
     */
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    /**
     * Methodes pour init l'economie de Vault
     */
    private boolean setupRaxorMain() {
        if (getServer().getPluginManager().getPlugin("RaxorMain") == null) {
            return false;
        }
        return true;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
