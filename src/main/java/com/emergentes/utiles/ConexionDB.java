
package com.emergentes.utiles;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB {

    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/bd_eventos";
    static String usuario = "root";
    static String password = "";

    Connection conn = null; // contructor para la BD, importar libreria

    public ConexionDB() {
        try {
            Class.forName(driver); // especifica el driver, por sugerencia colocar dentro de un bloque TRY CATCH
            conn = DriverManager.getConnection(url, usuario, password); //conexio bd y envolber dentro un un CATCH

            if (conn != null) { // verifica conexion 
                System.out.println("EXISTE CONEXION!!!!" + conn);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection conectar() {
        return conn;
    }
    public void desconectar(){
    
        try {
            conn.close(); // encerrar dentro de un TRY CATCH
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
