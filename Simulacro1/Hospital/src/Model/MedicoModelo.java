package Model;

import Database.CRUD;
import Database.ConfigDb;
import Entity.Especialidad;
import Entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoModelo implements CRUD {

    @Override
    public Object insert(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDb.openConnection();

        //parsear el objeto a tipo medico
        Medico objMedico = (Medico) obj;

        try {
            //Hacer sql
            String sql = "INSERT INTO medico (nombre,apellidos,id_especialidad ) VALUES (?,?,?);";

            //Preparar el statement
            PreparedStatement objprepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //Setear los parametros o darle valor a los signos de interrgacion
            objprepare.setString(1,objMedico.getNombre());
            objprepare.setString(2,objMedico.getApellidos());
            objprepare.setInt(3,objMedico.getIdEspecialidad());

            //Ejecutar el statement
            objprepare.execute();

            //Obtener las llaves generadas
            ResultSet objResult = objprepare.getGeneratedKeys();

            while (objResult.next()){
                objMedico.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Se inserto con exito");

        }catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
        ConfigDb.closeConnection();
        return objMedico;
    }

    @Override
    public List<Object> findAll() {
        //Abrir la conexion
        Connection objConnection = ConfigDb.openConnection();

        //crear un array para llenar con los datos
        List<Object> listaMedicos  = new ArrayList<>();

        try {
            //Escribir sql
            //EN ESTE CASO QUIERO TRAER TAMBIEN LA INFORMACION DE LA TABLA LA ESPECIALIDAD PARA QUE QUEDE UNIDA
            String sql = "SELECT * FROM medico\n" +
                    "INNER JOIN especialidad ON especialidad.id = medico.id_especialidad;";

            //Preparar el statement
            PreparedStatement objprepare = objConnection.prepareStatement(sql);

            //Ejecutar el statement Y Obtener los resultados
            ResultSet objResult = objprepare.executeQuery();

            while (objResult.next()) {
                //Objeto para llenar
                Medico objMedico = new Medico();

                //como la entidad medico guarda un objeto especialidad
                Especialidad objEspecialidad = new Especialidad();


                objMedico.setId(objResult.getInt("medico.id"));
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellidos(objResult.getString("medico.apellidos"));
                objMedico.setIdEspecialidad(objResult.getInt("medico.id_especialidad"));

                objEspecialidad.setId(objResult.getInt("especialidad.id"));
                objEspecialidad.setNombre(objResult.getString("especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("especialidad.descripcion"));

                //PARA GUARDAR LOS VALORES QUE SETEAMOS EN EL OBJespecialidad usamos los getters y setters de la entidad
                    objMedico.setObjEspecialidad(objEspecialidad);

                listaMedicos.add(objMedico);
            }



        }catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
        ConfigDb.closeConnection();
        return listaMedicos;
    }

    @Override
    public boolean delete(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDb.openConnection();

        //parsear el objeto a tipo medico
        Medico objMedico = (Medico) obj;

        //Saber si se esta borrando
        boolean borrado = false;

        try {
            String sql = "DELETE FROM medico WHERE id = ?;";

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
        ConfigDb.closeConnection();
        return borrado;
    }

    @Override
    public boolean update(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDb.openConnection();

        //parsear el objeto a tipo medico
        Medico objMedico = (Medico) obj;

        //Saber si se esta actualizando
        boolean actualizado = false;

        try {
            //Hacer la sentencia
            String sql = "UPDATE medico SET nombre=?, apellidos=?, id_especialidad=?;";

            //prepare statement
            PreparedStatement objprepare = objConnection.prepareStatement(sql);

            //Setear los parametros o darle valor a los signos de interrgacion
            objprepare.setString(1,objMedico.getNombre());
            objprepare.setString(2,objMedico.getApellidos());
            objprepare.setInt(3,objMedico.getIdEspecialidad());

            //Obtener las llaves generadas
            int rowsAfected = objprepare.executeUpdate();

            if (rowsAfected > 0) actualizado = true;

            JOptionPane.showMessageDialog(null, "Se actualizó con exito");

        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());

        }
        ConfigDb.closeConnection();
        return actualizado;
    }
}
