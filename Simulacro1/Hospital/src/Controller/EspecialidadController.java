package Controller;

import Entity.Especialidad;
import Model.EspecialidadModelo;
import Utiles.Utils;

import javax.swing.*;
import java.util.List;

public class EspecialidadController {


    //intanciar el modelo siempre
    public  static EspecialidadModelo instanciaModelo (){
        return new EspecialidadModelo();
    }


    public static void insert(){
        //pedir datos
        String name = JOptionPane.showInputDialog("Ingresa el nombre de la especialidad");
        String descripcion = JOptionPane.showInputDialog("Ingresa la descripcion de la especialidad");

        //insertar datos
        instanciaModelo().insert(new Especialidad(name, descripcion));
    }
    //muestra toda la lista aplicando sobre escritura
    public static void getAll(){
        String list = getAll(instanciaModelo().findAll());
        JOptionPane.showMessageDialog(null, list);
    }


    public static String getAll(List<Object> list){
        String listaRegistros = "LISTA DE REGISTROS: \n";
        for(Object Temporal : list){
            Especialidad objEspecialidad = (Especialidad) Temporal;
            listaRegistros += objEspecialidad.toString() + "\n";
        }
        return listaRegistros;
    }
//selecionar el elemento a eliminar
    public static void delete(){
        Object[] options = Utils.listToArray(instanciaModelo().findAll());
       Especialidad objSeleccionado = (Especialidad)  JOptionPane.showInputDialog(null,
                "Selecciona una especialidad a eliminar",
                "", JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

       instanciaModelo().delete(objSeleccionado);
    }

    public static void update(){
        Object[] options = Utils.listToArray(instanciaModelo().findAll());
        Especialidad objSeleccionado = (Especialidad)  JOptionPane.showInputDialog(null,
                "Selecciona una especialidad para actualizar",
                "", JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        //setear valores y mostrarlos en el JOP
       objSeleccionado.setNombre(JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre: " , objSeleccionado.getNombre()));
        objSeleccionado.setDescripcion(JOptionPane.showInputDialog(null, "Ingresa la nueva descripcion: " , objSeleccionado.getDescripcion()));

        //actualizar
        instanciaModelo().update(objSeleccionado);
    }

}

