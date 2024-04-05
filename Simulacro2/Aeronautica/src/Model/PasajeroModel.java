package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Pasajero;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //parsear el objeto a tipo medico
        Pasajero objPasajero = (Pasajero) obj;

        try {
            //Hacer sql
            String sql = "INSERT INTO pasajero (nombre,apellido,documento_identidad ) VALUES (?,?,?);";

            //Preparar el statement
            PreparedStatement objprepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //Setear los parametros o darle valor a los signos de interrgacion
            objprepare.setString(1,objPasajero.getNombre());
            objprepare.setString(2,objPasajero.getApellido());
            objprepare.setInt(3,objPasajero.getDocumentoIdentidad());


            //Ejecutar el statement
            objprepare.execute();

            //Obtener las llaves generadas
            ResultSet objResult = objprepare.getGeneratedKeys();

            while (objResult.next()){
                objPasajero.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Se inserto con exito");

        }catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objPasajero;
    }

    @Override
    public List<Object> findAll() {
        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //crear un array para llenar con los datos
        List<Object> listaPasajero  = new ArrayList<>();

        try {
            //Hacer el sql
            String sql = "SELECT * FROM pasajero;";

            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Resultado de la consulta y trae todos los datos
            ResultSet resultSet = objPrepare.executeQuery();

            while (resultSet.next()){
                Pasajero objPasajero = new Pasajero();
                objPasajero.setId(resultSet.getInt("id"));
                objPasajero.setNombre(resultSet.getString("nombre"));
                objPasajero.setApellido(resultSet.getString("apellido"));
                objPasajero.setDocumentoIdentidad(resultSet.getInt("documento_identidad"));
                listaPasajero.add(objPasajero);
            }


        }catch (SQLException e) {
            System.out.println("Error:" + e.getMessage() );
        }
        ConfigDB.closeConnection();
        return listaPasajero;
    }

    @Override
    public boolean delete(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //parsear el objeto a tipo Paciente
        Pasajero objPasajero = (Pasajero) obj;

        //Booleano para saber si se elimina
        boolean borrado = false;

        try {
            //Hacer la consulta
            String sql = "DELETE FROM pasajero WHERE id =?";

            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //añadir valores a los parentesis
            objPrepare.setInt(1, objPasajero.getId());

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
        Pasajero objPasajero = (Pasajero) obj;

        //Booleano para saber si se elimina
        boolean Actualizado = false;

        try {
            //Hacer la consulta
            String sql = "UPDATE paciente SET nombre =?, apellidos =?,documento_identidad = ? WHERE id=?;";
            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //añadir valores a los parentesis
            objPrepare.setString(1, objPasajero.getNombre());
            objPrepare.setString(2, objPasajero.getApellido());
            objPrepare.setInt(3, objPasajero.getDocumentoIdentidad());
            objPrepare.setInt(4, objPasajero.getId());

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
