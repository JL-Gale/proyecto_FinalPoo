
package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database {
    
    protected Connection conexion;
    
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String BD_URL = "jdbc:mysql://localhost:3306/hospital_test";
    private final String USER = "root";
    private final String PASS = "";
    
    public void conectar() throws ClassNotFoundException{
        try {
            conexion = DriverManager.getConnection(BD_URL, USER, PASS);
            Class.forName(JDBC_DRIVER);

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarConexion() throws SQLException{
        if (conexion != null) {
            if (!conexion.isClosed()) {
                conexion.close();
            }
        }
    }
}
