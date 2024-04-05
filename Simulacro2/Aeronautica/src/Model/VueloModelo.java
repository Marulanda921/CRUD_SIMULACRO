package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Avion;
import Entity.Vuelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VueloModelo implements CRUD {

    @Override
    public Object insert(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //parsear el objeto a tipo medico
        Vuelo objVuelo = (Vuelo) obj;

        try {
            //Hacer sql
            String sql = "INSERT INTO vuelo (destino,fecha_salida,hora_salida,id_avion ) VALUES (?,?,?,?);";

            //Preparar el statement
            PreparedStatement objprepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //Setear los parametros o darle valor a los signos de interrgacion
            objprepare.setString(1,objVuelo.getDestino());
            objprepare.setDate(2, Date.valueOf(objVuelo.getFecha_salida()));
            objprepare.setTime(3,Time.valueOf(objVuelo.getHora_salida()));
            objprepare.setInt(4,objVuelo.getIdAvion());

            //Ejecutar el statement
            objprepare.execute();

            //Obtener las llaves generadas
            ResultSet objResult = objprepare.getGeneratedKeys();

            while (objResult.next()){
                objVuelo.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Se inserto con exito");

        }catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objVuelo;
    }

    @Override
    public List<Object> findAll() {
        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //crear un array para llenar con los datos
        List<Object> listaMedicos  = new ArrayList<>();

        try {
            //Escribir sql
            //EN ESTE CASO QUIERO TRAER TAMBIEN LA INFORMACION DE LA TABLA LA ESPECIALIDAD PARA QUE QUEDE UNIDA
            String sql = "SELECT * FROM vuelo\n" +
                    "INNER JOIN avion ON avion.id = vuelo.id_avion;";

            //Preparar el statement
            PreparedStatement objprepare = objConnection.prepareStatement(sql);

            //Ejecutar el statement Y Obtener los resultados
            ResultSet objResult = objprepare.executeQuery();

            while (objResult.next()) {
                //Objeto para llenar
                Vuelo objVuelo = new Vuelo();

                //como la entidad medico guarda un objeto especialidad
                Avion objAvion = new Avion();


                objVuelo.setId(objResult.getInt("vuelo.id"));
                objVuelo.setDestino(objResult.getString("vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getString("vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("vuelo.hora_salida"));
                objVuelo.setIdAvion(objResult.getInt("vuelo.id_avion"));

                objAvion.setId(objResult.getInt("avion.id"));
                objAvion.setModelo(objResult.getString("avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("avion.capacidad"));

                //PARA GUARDAR LOS VALORES QUE SETEAMOS EN EL OBJespecialidad usamos los getters y setters de la entidad
                objVuelo.setObjAvion(objAvion);

                listaMedicos.add(objVuelo);
            }



        }catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listaMedicos;
    }

    @Override
    public boolean delete(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //parsear el objeto a tipo medico
        Vuelo objMedico = (Vuelo) obj;

        //Saber si se esta borrando
        boolean borrado = false;

        try {
            String sql = "DELETE FROM vuelo WHERE id = ?;";

            //prepare Statement
            PreparedStatement objprepare = objConnection.prepareStatement(sql);

            //Setear los parametros o darle valor a los signos de interrgacion
            objprepare.setInt(1,objMedico.getId());

            //Ejecutar el statement
            objprepare.execute();

            //Obtener las llaves generadas
            int rowsAfected = objprepare.executeUpdate();

            if (rowsAfected > 0) borrado = true;

            JOptionPane.showMessageDialog(null, "Se borró con exito");

        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return borrado;
    }

    @Override
    public boolean update(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //parsear el objeto a tipo medico
        Vuelo objVuelo = (Vuelo) obj;

        //Saber si se esta actualizando
        boolean actualizado = false;

        try {
            //Hacer la sentencia
            String sql = "UPDATE vuelo SET destino=?, fecha_salida=?, hora_salida =?,  id_avion=?;";

            //prepare statement
            PreparedStatement objprepare = objConnection.prepareStatement(sql);

            //Setear los parametros o darle valor a los signos de interrgacion
            objprepare.setString(1,objVuelo.getDestino());
            objprepare.setString(2,objVuelo.getFecha_salida());
            objprepare.setString(3,objVuelo.getHora_salida());
            objprepare.setInt(4,objVuelo.getIdAvion());

            //Obtener las llaves generadas
            int rowsAfected = objprepare.executeUpdate();

            if (rowsAfected > 0) actualizado = true;

            JOptionPane.showMessageDialog(null, "Se actualizó con exito");

        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());

        }
        ConfigDB.closeConnection();
        return actualizado;
    }
}
