package Model;

import Database.CRUD;
import Database.ConfigDb;
import Entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteModelo implements CRUD {
    @Override
    public Object insert(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDb.openConnection();

        //parsear el objeto a tipo Paciente
        Paciente objPaciente = (Paciente) obj;

        try {
            //Hacer la consulta
            String sql = "INSERT INTO paciente (nombre,apellidos,fecha_nacimiento,documento_identidad) VALUES (?,?,?,?);";

            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //añadir valores a los parentesis
            objPrepare.setString(1, objPaciente.getNombre());
            objPrepare.setString(2, objPaciente.getApellidos());
            objPrepare.setString(3, objPaciente.getFechaNacimiento());
            objPrepare.setString(4, objPaciente.getDocumentoIdentidad());

            //ejecutar consulta
            objPrepare.execute();

            //traer las llaves generadas
            ResultSet objResult = objPrepare.getGeneratedKeys();

            //Cada iteracion significa que hay valores generados
            while (objResult.next()) {
                objPaciente.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Datos insertados Correctamente");


        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
        ConfigDb.closeConnection();
        return objPaciente;

    }

    @Override
    public List<Object> findAll() {
       //Abrir la conexion
        Connection objConnection = ConfigDb.openConnection();

        //crear un array para llenar con los datos
        List<Object> listaPacientes  = new ArrayList<>();

        try {
            //Hacer el sql
            String sql = "SELECT * FROM paciente;";

            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Resultado de la consulta y trae todos los datos
            ResultSet resultSet = objPrepare.executeQuery();

            while (resultSet.next()){
                Paciente objPaciente = new Paciente();
                objPaciente.setId(resultSet.getInt("id"));
                objPaciente.setNombre(resultSet.getString("nombre"));
                objPaciente.setApellidos(resultSet.getString("apellidos"));
                objPaciente.setFechaNacimiento(resultSet.getString("fecha_nacimiento"));
                objPaciente.setDocumentoIdentidad(resultSet.getString("documento_identidad"));
                listaPacientes.add(objPaciente);
            }


        }catch (SQLException e) {
            System.out.println("Error:" + e.getMessage() );
        }
        ConfigDb.closeConnection();
        return listaPacientes;
    }



    @Override
    public boolean delete(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDb.openConnection();

        //parsear el objeto a tipo Paciente
        Paciente objPaciente = (Paciente) obj;

        //Booleano para saber si se elimina
        boolean borrado = false;

        try {
            //Hacer la consulta
            String sql = "DELETE FROM paciente WHERE id =?";

            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //añadir valores a los parentesis
            objPrepare.setInt(1, objPaciente.getId());

            //Filas afectadas
            int rowsAfected = objPrepare.executeUpdate();

            if (rowsAfected > 0){
                borrado = true;
                JOptionPane.showMessageDialog(null,"Datos eliminados Correctamente");
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

        //parsear el objeto a tipo Paciente
        Paciente objPaciente = (Paciente) obj;

        //Booleano para saber si se elimina
        boolean Actualizado = false;

        try {
            //Hacer la consulta
            String sql = "UPDATE paciente SET nombre =?, apellidos =?, fecha_nacimiento =?, documento_identidad = ? WHERE id=?;";
            //preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //añadir valores a los parentesis
            objPrepare.setString(1, objPaciente.getNombre());
            objPrepare.setString(2, objPaciente.getApellidos());
            objPrepare.setString(3, objPaciente.getFechaNacimiento());
            objPrepare.setString(4, objPaciente.getDocumentoIdentidad());
            objPrepare.setInt(5, objPaciente.getId());

            //Filas afectadas
            int rowsAfected = objPrepare.executeUpdate();

            if (rowsAfected > 0){
                Actualizado = true;
                JOptionPane.showMessageDialog(null,"Datos actualizados Correctamente");
            }

        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
        ConfigDb.closeConnection();
        return Actualizado;
    }
}
