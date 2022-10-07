package com.adriraxor.raxorplugin.Event;

import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Event implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        System.out.println("PLAYER JOINED");
        e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100D);
    }

}
