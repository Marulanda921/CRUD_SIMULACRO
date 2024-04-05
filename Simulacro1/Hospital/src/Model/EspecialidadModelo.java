package Model;

import Database.CRUD;
import Database.ConfigDb;
import Entity.Especialidad;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadModelo implements CRUD {


    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDb.openConnection();

        //parsear el objeto a tipo especialidad
        Especialidad objEspecialidad = (Especialidad) obj;

        try {

            //Escribir el Sql
            String sql = "INSERT INTO especialidad (nombre, descripcion) VALUES (?,?);";

            //preparar el statement
            PreparedStatement objprepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //setear los parametros
            objprepare.setString(1,objEspecialidad.getNombre());
            objprepare.setString(2,objEspecialidad.getDescripcion());

            //ejecutar el statement
            objprepare.execute();

            //traer las llaves generadas
            ResultSet objresult = objprepare.getGeneratedKeys();

            //Cada iteracion significa que hay valores generados
            while (objresult.next()){
                //el objeto es la respuesta obtenemos un entero por el indice 1 que siempre va a ser el id de la basde de datos
                objEspecialidad.setId(objresult.getInt(1));

            }

            JOptionPane.showMessageDialog(null, "La especialidad se genero correctamente");



        }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        ConfigDb.closeConnection();
        return objEspecialidad;
    }

    @Override
    public List<Object> findAll() {
        //Abrir la conexion
        Connection objConnection = ConfigDb.openConnection();

        //crear un array para llenar con los datos
        List<Object> listaEspecialidades  = new ArrayList<>();

        try {
            //Escribir el Sql
            String sql = "SELECT * FROM especialidad;";

            //preparar el statement
            PreparedStatement objprepare = objConnection.prepareStatement(sql);

            //ejecutar el statement, devuelve todos los datos
            ResultSet objresult = objprepare.executeQuery();

            //Cada iteracion significa que hay valores generados
            while (objresult.next()){
                //objeto que lo vamos a llenar
                Especialidad objEspecialidad = new Especialidad();
                objEspecialidad.setId(objresult.getInt("id"));
                objEspecialidad.setNombre(objresult.getString("nombre"));
                objEspecialidad.setDescripcion(objresult.getString("descripcion"));
                listaEspecialidades.add(objEspecialidad);
            }

        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
        ConfigDb.closeConnection();
        return listaEspecialidades;
    }

    @Override
    public boolean delete(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDb.openConnection();

        //parsear el objeto a tipo especialidad
        Especialidad objEspecialidad = (Especialidad) obj;

        //saber si se elimino o no
        boolean borrado = false;

        try {
            //Escribir el Sql
            String sql = "DELETE FROM especialidad WHERE id = ?;";

            //preparar el statement
            PreparedStatement objprepare = objConnection.prepareStatement(sql);

            //setear los parametros
            objprepare.setInt(1,objEspecialidad.getId());

            //ejecutar el statement, execute update nos devuelve un numero de filas afectadas
            int filasAfectadas =  objprepare.executeUpdate();

            if(filasAfectadas > 0){
                borrado = true;
            }
            JOptionPane.showMessageDialog(null, "La especialidad se eliminó correctamente");

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

        //parsear el objeto a tipo especialidad
        Especialidad objEspecialidad = (Especialidad) obj;

        //saber si se actualizo o no
        boolean actualizado = false;

        try {
            //Escribir el Sql
            String sql = "UPDATE especialidad SET nombre =?, descripcion =?;";

            //preparar el statement
            PreparedStatement objprepare = objConnection.prepareStatement(sql);

            //setear los parametros
            objprepare.setString(1,objEspecialidad.getNombre());
            objprepare.setString(2,objEspecialidad.getDescripcion());


            //ejecutar el statement, execute update nos devuelve un numero de filas afectadas
            int filasAfectadas =  objprepare.executeUpdate();

            if(filasAfectadas > 0){
                actualizado = true;
            }
            JOptionPane.showMessageDialog(null, "La especialidad se actualizó correctamente");

        }catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
        ConfigDb.closeConnection();
        return  actualizado;
    }
}
