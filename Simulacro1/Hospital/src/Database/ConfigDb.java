package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class ConfigDb {

    public static Connection objectConnection  = null;

    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://urdudkcffcko0lrd:A6TVXLX7kFeurvaK2PKT@bskou3zeqkwqcveshgiy-mysql.services.clever-cloud.com:3306/bskou3zeqkwqcveshgiy";
            String user = "urdudkcffcko0lrd";
            String password = "A6TVXLX7kFeurvaK2PKT";

            //establecer la conexion
            objectConnection = DriverManager.getConnection(url, user, password);
            System.out.println("Me conecte perfectamente");


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Driver no instalado: " + e.getMessage());
        }
        return objectConnection;
    }

    //es importante cerrar la conexion para no tener problemas
    //statica para poder utilizarla donde sea
    public static void closeConnection(){
        try {
            if(objectConnection != null)
                objectConnection.close();
            System.out.println("Me desconecte perfectamente");
        }catch (SQLException e){
            System.out.println("Error al cerrar la conexion: " + e.getMessage());
        }
    }

}
