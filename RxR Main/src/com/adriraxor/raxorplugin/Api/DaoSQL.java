package com.adriraxor.raxorplugin.Api;

import com.adriraxor.raxorplugin.RaxorMain;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cette classe est une DataAccessObject qui crée la connexion à la base de données
 */
public class DaoSQL {

    private static RaxorMain main;
    private static String chaineConnexion;
    private Connection connexion;
    private static DaoSQL daoSQL = null;

    /**
     * Constructeur public
     * @param plugin
     */
    public DaoSQL(RaxorMain plugin){
        main = plugin;
    }

    /**
     * Constructeur privé
     */
    private DaoSQL() {
        try {
            DaoSQL.chaineConnexion = "jdbc:mysql://" + main.getConfig().getString("host") + ":" + main.getConfig().getString("port") + "/" + main.getConfig().getString("db_name") + "?useUnicode=true"
                    + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
                    + "useSSL=false&"
                    + "serverTimezone=UTC";

            this.connexion = DriverManager.getConnection(DaoSQL.chaineConnexion, DaoSQL.main.getConfig().getString("username"), DaoSQL.main.getConfig().getString("password"));
        } catch (SQLException ex) {
            Logger.getLogger(DaoSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DaoSQL getInstance() {

        if (DaoSQL.daoSQL == null) {
            DaoSQL.daoSQL = new DaoSQL();
        } else {
            if (!DaoSQL.daoSQL.connexionActive()) {
                DaoSQL.daoSQL = new DaoSQL();
            }
        }
        return DaoSQL.daoSQL;
    }

    public Boolean connexionActive() {
        boolean connexionActive = true;
        try {
            if (this.connexion.isClosed()) {
                connexionActive = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connexionActive;
    }

    public ResultSet requeteSelection(String sql) {
        try {
            Statement requete = new DaoSQL().connexion.createStatement();
            return requete.executeQuery(sql);

        } catch (SQLException ex) {
            Logger.getLogger(DaoSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Integer requeteAction(String sql) {
        try {
            Statement requete = new DaoSQL().connexion.createStatement();
            return requete.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(DaoSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
