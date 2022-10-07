package com.adriraxor.raxorplugin;

import com.adriraxor.raxorplugin.Api.DaoSQL;
import com.adriraxor.raxorplugin.LIB.EconomyLIB;
import com.adriraxor.raxorplugin.Server.ServerEvent;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class RaxorMain extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        saveDefaultConfig();

        EconomyLIB economyLIB = new EconomyLIB();
        DaoSQL daoSQL = new DaoSQL(this);

        if (!economyLIB.setupEconomy()) {
            economyLIB.getLog().severe(ChatColor.RED + "[RaxorPlugin - Main] - Désactivation du plugin car la dépendance Vault n'est pas présente! (Install Vault!)");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        System.out.println(ChatColor.RED  + "" + ChatColor.BOLD + "#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#\n");
        System.out.println(ChatColor.GOLD + "" + ChatColor.BOLD + "[RaxorPlugin - Main] Le plugin vient de s'allumé\n");
        System.out.println(ChatColor.GOLD + "" + ChatColor.BOLD + "@Author : Adriraxor\n");
        System.out.println(ChatColor.GOLD + "" + ChatColor.BOLD + "@Desc : plugin database\n");
        System.out.println(ChatColor.RED  + "" + ChatColor.BOLD + "#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#\n");

        getServer().getPluginManager().registerEvents(new ServerEvent(), this);


        if(daoSQL.getInstance() == null){
            System.out.println(ChatColor.GOLD + "[RaxorPlugin - Main] Database disconnected.");
        } else {
            System.out.println(ChatColor.GOLD + "[RaxorPlugin - Main] Database connected.");
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        System.out.println("#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#\n");
        System.out.println("[RaxorPlugin - Main] Le plugin vient de s'éteindre\n");
        System.out.println("#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#~~~#\n");
    }
}