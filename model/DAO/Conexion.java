package polo.model.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    
    private Connection conexion;
    private Statement sentencia;

    public void conectarBaseDatos(String url, String user, String password){
        try{
            this.conexion = DriverManager.getConnection(url, user, password);
            this.sentencia = this.conexion.createStatement();
            System.out.println("Conexión exitosa a la BD");
        }catch(SQLException ex){
            System.out.println("Error conectando a la BD\n" + ex.getMessage());
        }
    }
    
    public void desconectarBaseDatos(){
        try{
            if(conexion != null){
                sentencia.close();
                conexion.close();
            }
        }catch(SQLException ex){
            System.out.println("Error desconectando de la BD\n" + ex.getMessage());
        }
    }
    
    public boolean querysDDL(String query){
        if (conexion != null){
            try {
                return sentencia.execute(query);
            } catch (SQLException ex) {
                System.out.println("Error ejecutando query: "+ query+"\n"+ex.getMessage());
            }
        }
        return false;
    }
    
    public ResultSet querysDML(String query){
        if (conexion != null){
            try {
                return sentencia.executeQuery(query);
            } catch (SQLException ex) {
                System.out.println("Error ejecutando query: "+ query+"\n"+ex.getMessage());
            }
        }
        return null;
    }
}
