package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Pasajero;
import Entity.Reservacion;
import Entity.Vuelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservacionModelo implements CRUD {

    @Override
    public Object insert(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();
        //parsear el objeto a tipo Cita
        Reservacion objReservacion = (Reservacion) obj;

        try {
            //hacer la consulta
            String sql = "INSERT INTO reservacion (id_pasajero,id_vuelo,fecha_reservacion,asiento) VALUES (?,?,?,?)";

            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //setear los parametros
            objPrepare.setInt(1, objReservacion.getIdPasajero());
            objPrepare.setInt(2,objReservacion.getIdVuelo());
            objPrepare.setDate(3, Date.valueOf(objReservacion.getFechaReservacion()));
            objPrepare.setString(4,objReservacion.getAsiento());

            //ejecutar el statement
            objPrepare.execute();

            //obtener el id de la cita
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objReservacion.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Se han insertado los datos");

        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objReservacion;
    }

    @Override
    public List<Object> findAll() {
        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //crear un array para llenar con los datos
        List<Object> listaReservaciones  = new ArrayList<>();

        try {
            //Hacer la consulta
            String sql = "SELECT * FROM reservacion INNER JOIN pasajero ON reservacion.id = pasajero.id INNER JOIN vuelo ON pasajero.id = vuelo.id";

            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //ejecutar el statement
            ResultSet objResult = objPrepare.executeQuery();

            //Cada iteracion significa que hay valores generados
            while (objResult.next()){
                //objeto que lo vamos a llenar
                Reservacion objReservacion = new Reservacion();
                //para mostrar los datos que son necesarios de la relacion toca llamar los que estan dentro de la entidad
                Pasajero objPasajero = new Pasajero();
                Vuelo objVuelo = new Vuelo();


                //Vienen de la tabla Reservacion
                objReservacion.setId(objResult.getInt("id"));
                objReservacion.setFechaReservacion(objResult.getString("fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("asiento"));


                //Las que vienen de la tabla de pasajero
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumentoIdentidad(objResult.getInt("documento_identidad"));

                //Las que vienen de la tabla de vuelo
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));

                objReservacion.setObjPasajero(objPasajero);
                objReservacion.setObjVuelo(objVuelo);

                listaReservaciones.add(objReservacion);
            }

        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listaReservaciones;
    }

    @Override
    public boolean delete(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();
        //parsear el objeto a tipo Cita
        Reservacion objReservacion = (Reservacion) obj;

        //verificar si se elimino
        boolean borrado = false;

        try {
            //hacer la consulta
            String sql = "DELETE FROM reservacion WHERE id =?";

            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //setear los parametros
            objPrepare.setInt(1,objReservacion.getId());

            //ejecutar el statement
            int totalRows = objPrepare.executeUpdate();

            if (totalRows > 0){
                borrado = true;
                JOptionPane.showMessageDialog(null,"Se han eliminado los datos");
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
        //parsear el objeto a tipo Cita
        Reservacion objReservacion = (Reservacion) obj;

        //verificar si se elimino
        boolean Actualizado = false;

        try {
            //Hacer la consulta
            String sql = "UPDATE reservacion SET fecha_reservacion =?, asiento =? WHERE id = ?;";
            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //setear los parametros
            objPrepare.setString(1,objReservacion.getFechaReservacion());
            objPrepare.setString(2,objReservacion.getAsiento());
            objPrepare.setInt(3,objReservacion.getId());

            //ejecutar el statement
            int totalRows = objPrepare.executeUpdate();

            if (totalRows > 0){
                Actualizado = true;
                JOptionPane.showMessageDialog(null,"Se han actualizado los datos");
            }

        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return Actualizado;
    }


}
