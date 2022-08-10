package presentatie;

import data.DataLayer;
import logica.Controller;
import logica.Danser;

import javax.naming.ldap.Control;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * projectv1 : Startup
 *
 * @author : Toon Van Havermaet
 * @version : 09/08/2022
 */

public class Startup {
    public static void main(String[] args) {

        try {
            DataLayer dl = new DataLayer(DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "dansschool", "Azerty123!"));
            Controller dc = new Controller(dl);
            new DataEditor(dc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}