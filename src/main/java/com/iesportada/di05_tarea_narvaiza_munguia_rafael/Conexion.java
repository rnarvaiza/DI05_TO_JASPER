
package com.iesportada.di05_tarea_narvaiza_munguia_rafael;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafa Narvaiza
 */
public class Conexion {
    
    private final String user = "rnarvaiza";
    private final String password = "032699aA$";
    private final String url ="jdbc:mysql://10.242.27.110:3306/REPORTE";
    private Connection con = null;
    
    public Connection getConexion(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
        
    }
    
}
