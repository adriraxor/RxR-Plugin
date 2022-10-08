package com.adriraxor.raxorplugin.Server;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerCombat implements Listener {

    @EventHandler
    public void onPlayerJoin(Player player){
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(1000);
    }
}
