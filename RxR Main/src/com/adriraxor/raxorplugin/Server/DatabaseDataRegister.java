package com.adriraxor.raxorplugin.Server;

import com.adriraxor.raxorplugin.Api.DaoSQL;
import com.adriraxor.raxorplugin.LIB.EconomyLIB;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseDataRegister implements Listener {

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime dateNow = LocalDateTime.now();
    Economy econ = EconomyLIB.getEconomy();
    Player player;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent p) {
        player = p.getPlayer();
        System.out.println(ChatColor.RED + "" + ChatColor.BOLD + "[RaxorPlugin] " + player.getName() +" vient de rejoindre le serveur ! (uuid: "+ player.getUniqueId() +")");
        ResultSet rs = DaoSQL.getInstance().requeteSelection("SELECT * FROM Player WHERE uuid = '"+ player.getUniqueId()+"'");

        try {
            if (DaoSQL.getInstance() != null) {
                if (rs.next()) {
                    DaoSQL.getInstance().requeteAction("UPDATE Player SET last_joined = '" + dateFormat.format(dateNow) + "' WHERE uuid = '" + player.getUniqueId() + "'");
                } else {
                    DaoSQL.getInstance().requeteAction("INSERT INTO Player(username, money, last_joined, uuid, id_job) VALUES ('" + player.getName() + "', " + econ.getBalance(player.getName()) + ", '" + dateFormat.format(dateNow) + "', '" + player.getUniqueId() + "', 1)");
                }
            }

            System.out.println(player.getLocation());
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[RaxorPlugin] " + ChatColor.AQUA + "" + ChatColor.BOLD + "Bienvenue sur le serveur !");

            } catch(SQLException e){
                e.printStackTrace();
            }
            onlinePlayers();
        }

    @EventHandler
    public void onLeavePlayer(PlayerQuitEvent p){
        player = p.getPlayer();

        try {
            if (DaoSQL.getInstance() != null) {
                ResultSet resultSet = DaoSQL.getInstance().requeteSelection("SELECT * FROM Player WHERE uuid = '"+ player.getUniqueId()+"'");

                if (resultSet.next()) {
                    DaoSQL.getInstance().requeteAction("UPDATE Player SET money = " + econ.getBalance(player.getName()) + ", last_position = '"+ player.getLocation() +"', last_server = '"+ Bukkit.getServerName()+":"+ player.getAddress().getHostName() +"' WHERE uuid = '"+player.getUniqueId()+"'");
                    System.out.println(econ.getBalance(player.getName()));

                } else {
                    System.out.println("[RaxorPlugin] Erreur ~~~PlayerInfo.CLASS~~~");
                }
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        onlinePlayers();
    }

    public void onlinePlayers(){
        try {
            if (DaoSQL.getInstance() != null) {

                ResultSet resultSet = DaoSQL.getInstance().requeteSelection("SELECT * FROM ServerData WHERE description = 'ONLINE_PLAYERS'");

                if (resultSet.next()) {
                    DaoSQL.getInstance().requeteAction("UPDATE ServerData SET value = " + Bukkit.getOnlinePlayers().size() + " WHERE description = 'ONLINE_PLAYERS'");
                } else {
                    DaoSQL.getInstance().requeteAction("INSERT INTO ServerData(value, description) VALUES (" + Bukkit.getOnlinePlayers().size() + ", 'ONLINE_PLAYERS')");
                }
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}