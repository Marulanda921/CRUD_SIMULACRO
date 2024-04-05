package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Avion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvionModelo implements CRUD {

    @Override
    public Object insert(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //parsear el objeto a tipo Paciente
        Avion objAvion = (Avion) obj;

        try {
            //Hacer la consulta
            String sql = "INSERT INTO avion (modelo,capacidad) VALUES (?,?);";

            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //añadir valores a los parentesis
            objPrepare.setString(1, objAvion.getModelo());
            objPrepare.setInt(2, objAvion.getCapacidad());


            //ejecutar consulta
            objPrepare.execute();

            //traer las llaves generadas
            ResultSet objResult = objPrepare.getGeneratedKeys();

            //Cada iteracion significa que hay valores generados
            while (objResult.next()) {
                objAvion.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Datos insertados Correctamente");


        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAvion;
    }

    @Override
    public List<Object> findAll() {
        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //crear un array para llenar con los datos
        List<Object> listaAviones  = new ArrayList<>();

        try {
            //Hacer el sql
            String sql = "SELECT * FROM avion;";

            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Resultado de la consulta y trae todos los datos
            ResultSet resultSet = objPrepare.executeQuery();

            while (resultSet.next()){
                Avion objAvion = new Avion();
                objAvion.setId(resultSet.getInt("id"));
                objAvion.setModelo(resultSet.getString("modelo"));
                objAvion.setCapacidad(resultSet.getInt("capacidad"));
                listaAviones.add(objAvion);
            }


        }catch (SQLException e) {
            System.out.println("Error:" + e.getMessage() );
        }
        ConfigDB.closeConnection();
        return listaAviones;
    }

    @Override
    public boolean delete(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //parsear el objeto a tipo Paciente
        Avion objAvion = (Avion) obj;

        //Booleano para saber si se elimina
        boolean borrado = false;

        try {
            //Hacer la consulta
            String sql = "DELETE FROM avion WHERE id =?";

            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //añadir valores a los parentesis
            objPrepare.setInt(1, objAvion.getId());

            //Filas afectadas
            int rowsAfected = objPrepare.executeUpdate();

            if (rowsAfected > 0){
                borrado = true;
                JOptionPane.showMessageDialog(null,"Datos eliminados Correctamente");
            }

        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return borrado;
    }

    @Override
    public boolean update(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //parsear el objeto a tipo Paciente
        Avion objAvion = (Avion) obj;

        //Booleano para saber si se elimina
        boolean Actualizado = false;

        try {
            //Hacer la consulta
            String sql = "UPDATE avion SET modelo =?, capacidad =? WHERE id=?;";
            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //añadir valores a los parentesis
            objPrepare.setString(1, objAvion.getModelo());
            objPrepare.setInt(2, objAvion.getCapacidad());
            objPrepare.setInt(3, objAvion.getId());

            //Filas afectadas
            int rowsAfected = objPrepare.executeUpdate();

            if (rowsAfected > 0){
                Actualizado = true;
                JOptionPane.showMessageDialog(null,"Datos actualizados Correctamente");
            }

        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return Actualizado;
    }
}
