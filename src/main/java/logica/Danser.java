package logica;

import data.DataLayer;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * projectv1 : danser
 *
 * @author : Toon Van Havermaet
 * @version : 04/08/2022
 */

public class Danser {
    private String voornaam;
    private String familienaam;
    private int geboortejaar;
    private int id;


    public Danser(int id, String voornaam, String familienaam, int geboortejaar) {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.geboortejaar = geboortejaar;
    }
//    public Danser(int id, String voornaam, String familienaam) {
//        this.id = id;
//        this.voornaam = voornaam;
//        this.familienaam = familienaam;
//    }
    public String getFamilienaam() {
        return familienaam;
    }
    public String getVoornaam() {
        return voornaam;
    }
    public int getGeboortejaar() {
        return geboortejaar;
    }

    @Override
    public String toString() {
        return id + "-" + voornaam + "-" + familienaam + "-" + geboortejaar;
    }

}
