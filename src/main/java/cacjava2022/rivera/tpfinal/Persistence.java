package cacjava2022.rivera.tpfinal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ana
 */
public class Persistence {
    private Connection conn;
    private ResultSet res;
    private PreparedStatement prepStatement;
    private ResultSetMetaData rsm;

    public String servidor, basededatos, user, pass, ejecutar;

    public Connection conectarse() {
        servidor = "localhost:3306/";
        basededatos = "cac_tpfinal_d";
        user = "basico";
        pass = "bas2022cacjfs";

        try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://" + servidor + basededatos+"?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true", user, pass);
    
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;
    }
    
    public ResultSet consultaSQL(String busqueda) {
        try {
            prepStatement = conectarse().prepareStatement(busqueda);
            res = (ResultSet) prepStatement.executeQuery();
            rsm = res.getMetaData();
        } catch (SQLException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }
}
