package com.adriraxor.raxorplugin.economy;

import com.adriraxor.raxorplugin.RaxorMobKilledMain;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobKilled implements Listener {

    private int value = 0;
    private RaxorMobKilledMain raxorMobKilledMain;

   public MobKilled(RaxorMobKilledMain monPlugin) {
        this.raxorMobKilledMain = monPlugin;
    }

    @EventHandler
    public void onDeathMobs(EntityDeathEvent e) {

        Entity entity = e.getEntity();
        Player killer = e.getEntity().getKiller();
        Economy econ = RaxorMobKilledMain.getEconomy();

        //--- Configuration gain valeur mob kill

        if (killer instanceof Player) {
            switch (entity.getType()) {
                case ZOMBIE:
                    value = raxorMobKilledMain.getConfig().getInt("zombie");
                    break;
                case BAT:
                    value = raxorMobKilledMain.getConfig().getInt("bat");
                    break;
                case BLAZE:
                    value = raxorMobKilledMain.getConfig().getInt("blaze");
                    break;
                case CAVE_SPIDER:
                    value = raxorMobKilledMain.getConfig().getInt("cave_spider");
                    break;
                case CHICKEN:
                    value = raxorMobKilledMain.getConfig().getInt("chicken");
                    break;
                case COW:
                    value = raxorMobKilledMain.getConfig().getInt("cow");
                    break;
                case CREEPER:
                    value = raxorMobKilledMain.getConfig().getInt("creeper");
                    break;
                case ENDER_DRAGON:
                    value = raxorMobKilledMain.getConfig().getInt("ender_dragon");
                    break;
                case ENDERMAN:
                    value = raxorMobKilledMain.getConfig().getInt("enderman");
                    break;
                case ENDERMITE:
                    value = raxorMobKilledMain.getConfig().getInt("endermite");
                    break;
                case GHAST:
                    value = raxorMobKilledMain.getConfig().getInt("ghast");
                    break;
                case GUARDIAN:
                    value = raxorMobKilledMain.getConfig().getInt("guardian");
                    break;
                case IRON_GOLEM:
                    value = raxorMobKilledMain.getConfig().getInt("iron_golem");
                    break;
                case PIG:
                    value = raxorMobKilledMain.getConfig().getInt("pig");
                    break;
                case PIG_ZOMBIE:
                    value = raxorMobKilledMain.getConfig().getInt("pig_zombie");
                    break;
                case PLAYER:
                    value = raxorMobKilledMain.getConfig().getInt("player");
                    break;
                case SHEEP:
                    value = raxorMobKilledMain.getConfig().getInt("sheep");
                    break;
                case SKELETON:
                    value = raxorMobKilledMain.getConfig().getInt("skeleton");
                    break;
                case ZOMBIE_VILLAGER:
                    value = raxorMobKilledMain.getConfig().getInt("zombie_villager");
                    break;
                case ZOMBIE_HORSE:
                    value = raxorMobKilledMain.getConfig().getInt("zombie_horse");
                    break;
                case WITCH:
                    value = raxorMobKilledMain.getConfig().getInt("witch");
                    break;
                case WITHER:
                    value = raxorMobKilledMain.getConfig().getInt("wither");
                    break;
                case SPIDER:
                    value = raxorMobKilledMain.getConfig().getInt("spider");
                    break;
                case WOLF:
                    value = raxorMobKilledMain.getConfig().getInt("wolf");
                    break;
            }

            econ.depositPlayer(killer.getPlayer(), value);
            if (value != 0)
                killer.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[RaxorPlugin] " + ChatColor.GREEN + "" + ChatColor.BOLD + "Vous venez de gagner : " + value + "$");

            // --- Notif push sur l'ATH du joueur

            killer.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "" + ChatColor.BOLD + "+" + value));

        }
    }
}