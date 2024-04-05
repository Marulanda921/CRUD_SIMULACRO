package Controller;

import Entity.Avion;
import Entity.Vuelo;
import Model.VueloModelo;
import Utilies.Utils;

import javax.swing.*;
import java.util.List;

public class VueloController {

    public static VueloModelo intanceModel(){
        return new VueloModelo();
    }

    public  static void insert(){
        String destino = JOptionPane.showInputDialog("Ingrese el nombre del destino: ");
        String fechaSalida = JOptionPane.showInputDialog("Ingresa la fecha del vuelo : YYYY-MM-DD: ");
        String horaSalida = JOptionPane.showInputDialog("Ingresa la hora de la cita : HH:MM:ss: ");



        Object[] opcionesVuelos = Utils.listToArray(AvionController.instanciaModelo().findAll());

        Avion objAvion = (Avion) JOptionPane.showInputDialog(null, "Seleccione un Vuelo", "", JOptionPane.QUESTION_MESSAGE, null, opcionesVuelos, opcionesVuelos[0]);

        //TIENE QUE LLEVAR UN OBJETO
        //1. nombre
        //2. apellidos
        //3. idEspecialidad
        //4. objEspecialidad

        intanceModel().insert(new Vuelo(destino,fechaSalida,horaSalida,objAvion.getId(), objAvion));
    }

    public static void getAll(){
        String list = getAll(intanceModel().findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list){
        String listString = "Lista de Vuelos \n";

        for(Object Temporal : list){
            Vuelo objMedico = (Vuelo) Temporal;
            listString += objMedico.toString() + "\n";
        }
        return listString;
    }


    public static void delete(){
        Object[] Opciones = Utils.listToArray(intanceModel().findAll());
        Vuelo objMedico = (Vuelo) JOptionPane.showInputDialog(null, "Seleccione un vuelo a eliminar", "", JOptionPane.QUESTION_MESSAGE, null, Opciones, Opciones[0]);
        intanceModel().delete(objMedico);
    }


    public static void update(){
        Object[] Opciones = Utils.listToArray(intanceModel().findAll());
        Vuelo objVuelo = (Vuelo) JOptionPane.showInputDialog(null, "Seleccione un vuelo a editar", "", JOptionPane.QUESTION_MESSAGE, null, Opciones, Opciones[0]);
        String destino = JOptionPane.showInputDialog( null, "Ingrese el nombre del destino: ", objVuelo.getDestino());
        String fechaSalida = JOptionPane.showInputDialog(null,"Ingrese la fecha de salida del vuelo: ", objVuelo.getFecha_salida());
        String horaSalida = JOptionPane.showInputDialog(null,"Ingrese la hora de salida vuelo: ", objVuelo.getHora_salida());


        Object[] opcionesAvion = Utils.listToArray(AvionController.instanciaModelo().findAll());

        Avion objAvion = (Avion) JOptionPane.showInputDialog(null, "Seleccione una especialidad", "", JOptionPane.QUESTION_MESSAGE, null, opcionesAvion, opcionesAvion[0]);

        //Instanciamos y creamos el nuevo medico
        intanceModel().update(new Vuelo(destino,fechaSalida,horaSalida, objAvion.getId(),objAvion));

    }


}
