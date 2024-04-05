package Controller;

import Entity.Avion;
import Model.AvionModelo;
import Utilies.Utils;

import javax.swing.*;
import java.util.List;

public class AvionController {

    public static AvionModelo instanciaModelo(){
        return new AvionModelo();
    }

    public static void insert(){
        String modelo = JOptionPane.showInputDialog("Ingresa el nombre del modelo: ");
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la capacidad de la aeronave: : "));

        instanciaModelo().insert(new Avion(modelo,capacidad));
    }

    public static void getAll(){
        String lista = getAll(instanciaModelo().findAll());
        JOptionPane.showMessageDialog(null, lista);
    }

    public static String getAll(List<Object> list){
        String listaRegistros = "LISTA DE AVIONES: \n";
        for(Object Temporal : list){
            Avion objAvion = (Avion) Temporal;
            listaRegistros += objAvion.toString() + "\n";
        }
        return listaRegistros;
    }


    public static  void delete() {
        Object[] options = Utils.listToArray(instanciaModelo().findAll());
        Avion pacienteSelecionado = (Avion) JOptionPane.showInputDialog(null, "Seleccionar paciente a eliminar", "", JOptionPane.QUESTION_MESSAGE,null, options, options[0]);

        instanciaModelo().delete(pacienteSelecionado);
    }

    public static void update(){
        Object[] options = Utils.listToArray(instanciaModelo().findAll());
        Avion AvionSeleccionado = (Avion) JOptionPane.showInputDialog(null, "Seleccionar paciente a Actualizar", "", JOptionPane.QUESTION_MESSAGE,null, options, options[0]);

        AvionSeleccionado.setModelo(JOptionPane.showInputDialog(null, "Ingresa nuevo modelo: ", AvionSeleccionado.getModelo()));
        AvionSeleccionado.setCapacidad( Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la Nueva capacidad: ", AvionSeleccionado.getCapacidad())));



        instanciaModelo().update(AvionSeleccionado);
    }


}
