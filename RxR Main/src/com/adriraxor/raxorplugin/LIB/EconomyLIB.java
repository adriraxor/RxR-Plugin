package com.adriraxor.raxorplugin.LIB;

import com.adriraxor.raxorplugin.RaxorMain;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.logging.Logger;

import static org.bukkit.Bukkit.getServer;

public class EconomyLIB {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;

    public EconomyLIB(){ }

    public boolean setupEconomy() {
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

    public static Economy getEconomy() {
        return econ;
    }

    public Logger getLog(){
        return log;
    }
}
