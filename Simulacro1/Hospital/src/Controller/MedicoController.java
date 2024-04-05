package Controller;

import Entity.Especialidad;
import Entity.Medico;
import Model.MedicoModelo;
import Utiles.Utils;

import javax.swing.*;
import java.util.List;

public class MedicoController {


    public static void update(){
        Object[] Opciones = Utils.listToArray(intanceModel().findAll());
        Medico objMedico = (Medico) JOptionPane.showInputDialog(null, "Seleccione un medico a editar", "", JOptionPane.QUESTION_MESSAGE, null, Opciones, Opciones[0]);
        String nombre = JOptionPane.showInputDialog( null, "Ingrese el nombre del medico: ", objMedico.getNombre());
        String apellidos = JOptionPane.showInputDialog(null,"Ingrese los apellidos del medico: ", objMedico.getApellidos());

        Object[] opcionesEpecialidades = Utils.listToArray(EspecialidadController.instanciaModelo().findAll());

        Especialidad objEspecialidad = (Especialidad) JOptionPane.showInputDialog(null, "Seleccione una especialidad", "", JOptionPane.QUESTION_MESSAGE, null, opcionesEpecialidades, opcionesEpecialidades[0]);

        //Instanciamos y creamos el nuevo medico
        intanceModel().update(new Medico(nombre,apellidos,objEspecialidad.getId(),objEspecialidad));

    }

    public static void delete(){
        Object[] Opciones = Utils.listToArray(intanceModel().findAll());
        Medico objMedico = (Medico) JOptionPane.showInputDialog(null, "Seleccione un medico a eliminar", "", JOptionPane.QUESTION_MESSAGE, null, Opciones, Opciones[0]);
        intanceModel().delete(objMedico);
    }

    public static void getAll(){
        String list = getAll(intanceModel().findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list){
        String listString = "Lista de registros \n";

        for(Object Temporal : list){
            Medico objMedico = (Medico) Temporal;
            listString += objMedico.toString() + "\n";
        }
        return listString;
    }


    public  static void insert(){
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del medico: ");
        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del medico: ");

        Object[] opcionesEpecialidades = Utils.listToArray(EspecialidadController.instanciaModelo().findAll());

        Especialidad objEspecialidad = (Especialidad) JOptionPane.showInputDialog(null, "Seleccione una especialidad", "", JOptionPane.QUESTION_MESSAGE, null, opcionesEpecialidades, opcionesEpecialidades[0]);

        //TIENE QUE LLEVAR UN OBJETO
        //1. nombre
        //2. apellidos
        //3. idEspecialidad
        //4. objEspecialidad

        intanceModel().insert(new Medico(nombre,apellidos,objEspecialidad.getId() ,objEspecialidad));
    }

    public static MedicoModelo intanceModel(){
        return new MedicoModelo();
    }
}
