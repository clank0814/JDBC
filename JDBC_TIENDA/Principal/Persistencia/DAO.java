/*
 */
package Principal.Persistencia;


import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mario
 */
public abstract class DAO {
    
    // ADMINISTRA NUESTRA CONEXION
    protected Connection conexion = null;
    // INSTRUCCION DE CONSULTA
    protected ResultSet resultado = null;
    // MANIPULA LOS RESULTADOS
    protected Statement sentencia = null;
    
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "tienda";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    protected void conectar() throws ClassNotFoundException, SQLException{
        try {
            // CARGA EL CONTROLADOR
            Class.forName(DRIVER);
            // UBICACION DE LA BASE DE DATOS (TIENDA)
            String urlBaseDeDatos = "jdbc:mysql://localhost:3306/tienda?zeroDateTimeBehavior=CONVERT_TO_NULL";
            // ESTABLECE LA CIONECCION
            conexion = DriverManager.getConnection(urlBaseDeDatos, USER, PASSWORD);
            
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        
    }

    protected  void desconectar() throws Exception{
        
        try {
            if (conexion != null) {
                conexion.close();
            }
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            
        } catch (Exception e) {
            throw new Exception ("ERROR AL DESCONECTAR");
            
        }
        
    }
    
    protected void Modificar(String sql) throws Exception{
        
        try {
            conectar();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
           throw e;
        }
    /*    
        try {
            conexion.rollback();
        } catch (SQLException e) {
            
            throw new Exception ("ERROR DE ROLLBACK");
        }
      */  
        finally{
            desconectar();
        }
    }
    
    protected void Mostrar(String sql) throws Exception{
        
        try {
            conectar();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    
}
