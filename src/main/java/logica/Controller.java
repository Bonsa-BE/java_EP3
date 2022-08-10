package logica;

import data.DataLayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * projectv1 : DanserController
 *
 * @author : Toon Van Havermaet
 * @version : 04/08/2022
 */

public class Controller {
    private DataLayer DataLayer;

    public Controller(DataLayer DataLayer) {

        this.DataLayer = DataLayer;
    }

    public ArrayList<Danser>  getDansers() {
        try {
            return DataLayer.getDansersList();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("");
        }
    }
    public void insertDanser(String voornaam, String familienaam, int geboortejaar) throws SQLException, DataLayerException {
        DataLayer.insertDanser(voornaam,familienaam,geboortejaar);
    }
    public void updateDanser(int id, String voornaam, String familienaam, int geboortejaar) throws SQLException, DataLayerException {
        DataLayer.updateDanser(id,voornaam,familienaam,geboortejaar);
    }
//    public Danser getDanser(int id) throws SQLException {
//        return DataLayer.getDanser(id);
//    }
}