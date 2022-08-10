package data;

import logica.Danser;
import logica.DataLayerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * projectv1 : danserDAO
 *
 * @author : Toon Van Havermaet
 * @version : 04/08/2022
 */

public class DataLayer {
    //    private String dbName;
    private final String login = "dansschool";
    private final String pass = "Azerty123!";
    private Connection con;

    public DataLayer(Connection con) {
        this.con = con;
    }

    public ArrayList<Danser> getDansersList() throws SQLException {
        Statement stmt = null;
        ArrayList<Danser> dansersList = null;
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//TO DO: DB aanpassen, query fixen
            ResultSet rs = stmt.executeQuery("SELECT * FROM leden");
            dansersList = new ArrayList<Danser>();
            //rs.first();
            while (rs.next()) {
                int id = rs.getInt("idleden");
                String voornaam = rs.getString("voornaam");
                String familienaam = rs.getString("familienaam");
                int geboortejaar = rs.getInt("geboortejaar");
                Danser danser = new Danser(id, voornaam, familienaam, geboortejaar);
                dansersList.add(danser);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return dansersList;
    }

    public void insertDanser(String voornaam, String familienaam, int geboortejaar) throws SQLException, DataLayerException {

        PreparedStatement stmt = null;
        try {
            stmt = this.con.prepareStatement("INSERT INTO leden (voornaam, familienaam, geboortejaar) VALUES (?,?,?)");
            stmt.setString(1, voornaam);
            stmt.setString(2, familienaam);
            stmt.setInt(3, geboortejaar);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataLayer.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataLayerException("fout in DataLayer");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void updateDanser(int id, String voornaam, String familienaam, int geboortejaar) throws DataLayerException, SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = this.con.prepareStatement("UPDATE leden set voornaam = ?, familienaam = ?,geboortejaar = ? WHERE idleden =?");
            stmt.setString(1, voornaam);
            stmt.setString(2, familienaam);
            stmt.setInt(3, geboortejaar);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataLayer.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataLayerException("fout in DataLayer");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }


//    public Danser getDanser(int id) throws SQLException {
//        PreparedStatement stmt = null;
//        Danser danser = null;
//        try {
//            stmt = this.con.prepareStatement("SELECT * FROM leden where idleden = ?");
//            stmt.setInt(1, id);
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                int index = rs.getInt("idleden");
//                String voornaam = rs.getString("voornaam");
//                String familienaam = rs.getString("familienaam");
//                int geboortejaar = rs.getInt("geboortejaar");
//                danser = new Danser(id, voornaam, familienaam, geboortejaar);
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(DataLayer.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            if (stmt != null) {
//                stmt.close();
//            }
//        }
//        return danser;
//    }
}