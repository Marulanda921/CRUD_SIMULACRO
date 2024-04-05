package Model;

import Database.CRUD;
import Database.ConfigDb;
import Entity.Cita;
import Entity.Medico;
import Entity.Paciente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaModelo implements CRUD {

    @Override
    public Object insert(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDb.openConnection();
        //parsear el objeto a tipo Cita
        Cita objCita = (Cita) obj;

        try {
            //hacer la consulta
            String sql = "INSERT INTO cita (fecha,hora,motivo,id_paciente,id_medico) VALUES (?,?,?,?,?)";

            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //setear los parametros
            objPrepare.setDate(1, Date.valueOf(objCita.getDate()));
            objPrepare.setTime(2, Time.valueOf(objCita.getHora()));
            objPrepare.setString(3,objCita.getMotivo());
            objPrepare.setInt(4,objCita.getIdPaciente());
            objPrepare.setInt(5,objCita.getIdMedico());

            //ejecutar el statement
            objPrepare.execute();

            //obtener el id de la cita
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objCita.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Se han insertado los datos");

        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
        ConfigDb.closeConnection();
        return objCita;
    }

    @Override
    public List<Object> findAll() {
        //Abrir la conexion
        Connection objConnection = ConfigDb.openConnection();

        //crear un array para llenar con los datos
        List<Object> listaCitas  = new ArrayList<>();

        try {
            //Hacer la consulta
            String sql = "SELECT * FROM cita \n" +
                    "INNER JOIN paciente ON paciente.id = cita.id\n" +
                    "INNER JOIN medico ON medico.id = cita.id_medico";

            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //ejecutar el statement
            ResultSet objResult = objPrepare.executeQuery();

            //Cada iteracion significa que hay valores generados
            while (objResult.next()){
                //objeto que lo vamos a llenar
                Cita objCita = new Cita();
                //para mostrar los datos que son necesarios de la relacion toca llamar los que estan dentro de la entidad
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();

                objCita.setId(objResult.getInt("cita.id"));
                objCita.setDate(objResult.getString("cita.fecha"));
                objCita.setHora(objResult.getString("cita.hora"));
                objCita.setMotivo(objResult.getString("cita.motivo"));
                objCita.setIdPaciente(objResult.getInt("id_paciente"));
                objCita.setIdMedico(objResult.getInt("id_medico"));

                objMedico.setNombre(objResult.getString("medico.nombre"));
                objPaciente.setNombre(objResult.getString("paciente.nombre"));

                //lenar la cita con los valores de medico y paciente que son las tablas relacionadas
                objCita.setObjMedico(objMedico);
                objCita.setObjPaciente(objPaciente);

                listaCitas.add(objCita);
            }

        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
        ConfigDb.closeConnection();
        return listaCitas;
    }

    @Override
    public boolean delete(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDb.openConnection();
        //parsear el objeto a tipo Cita
        Cita objCita = (Cita) obj;

        //verificar si se elimino
        boolean borrado = false;

        try {
            //hacer la consulta
            String sql = "DELETE FROM cita WHERE id =?";

            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //setear los parametros
            objPrepare.setInt(1,objCita.getId());

            //ejecutar el statement
            int totalRows = objPrepare.executeUpdate();

            if (totalRows > 0){
                borrado = true;
                JOptionPane.showMessageDialog(null,"Se han eliminado los datos");
            }


        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
        ConfigDb.closeConnection();
        return borrado;
    }

    @Override
    public boolean update(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDb.openConnection();
        //parsear el objeto a tipo Cita
        Cita objCita = (Cita) obj;

        //verificar si se elimino
        boolean Actualizado = false;

        try {
            //Hacer la consulta
            String sql = "UPDATE cita SET fecha =?, hora =?, motivo=?, id_paciente =?, id_medico =? WHERE id = ?;";
            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //setear los parametros
            objPrepare.setDate(1, Date.valueOf(objCita.getDate()));
            objPrepare.setTime(2, Time.valueOf(objCita.getHora()));
            objPrepare.setString(3,objCita.getMotivo());
            objPrepare.setInt(4,objCita.getIdPaciente());
            objPrepare.setInt(5,objCita.getIdMedico());
            objPrepare.setInt(6,objCita.getId());

            //ejecutar el statement
            int totalRows = objPrepare.executeUpdate();

            if (totalRows > 0){
                Actualizado = true;
                JOptionPane.showMessageDialog(null,"Se han actualizado los datos");
            }

        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
        ConfigDb.closeConnection();
        return Actualizado;
    }
}
