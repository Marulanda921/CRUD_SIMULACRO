package Controller;

import Entity.Pasajero;
import Model.PasajeroModel;
import Utilies.Utils;

import javax.swing.*;
import java.util.List;

public class PasajeroController {

    public static PasajeroModel instanciaModelo() {
        return new PasajeroModel();
    }

    public static void insert() {
        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del paciente: ");
        String apellido = JOptionPane.showInputDialog("Ingresa los apellidos del paciente: ");
        int documentoId = Integer.parseInt(JOptionPane.showInputDialog("Ingresa tu documento de identidad: "));


        instanciaModelo().insert(new Pasajero(nombre, documentoId, apellido));
    }

    public static void getAll(){
        String lista = getAll(instanciaModelo().findAll());
        JOptionPane.showMessageDialog(null, lista);
    }

    public static String getAll(List<Object> list){
        String listaRegistros = "LISTA DE PASAJEROS: \n";
        for(Object Temporal : list){
            Pasajero objPaciente = (Pasajero) Temporal;
            listaRegistros += objPaciente.toString() + "\n";
        }
        return listaRegistros;
    }


    public static  void delete() {
        Object[] options = Utils.listToArray(instanciaModelo().findAll());
        Pasajero pasajeroSelecionado = (Pasajero) JOptionPane.showInputDialog(null, "Seleccionar paciente a eliminar", "", JOptionPane.QUESTION_MESSAGE,null, options, options[0]);

        instanciaModelo().delete(pasajeroSelecionado);
    }

    public static void update(){
        Object[] options = Utils.listToArray(instanciaModelo().findAll());
        Pasajero pasajeroSelecionado = (Pasajero) JOptionPane.showInputDialog(null, "Seleccionar paciente a Actualizar", "", JOptionPane.QUESTION_MESSAGE,null, options, options[0]);

        pasajeroSelecionado.setNombre(JOptionPane.showInputDialog(null, "Ingresa nuevo nombre: ", pasajeroSelecionado.getNombre()));
        pasajeroSelecionado.setApellido(JOptionPane.showInputDialog(null, "Ingresa nuevo apellido: ", pasajeroSelecionado.getApellido()));
        pasajeroSelecionado.setDocumentoIdentidad( Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa tu documento de identidad", pasajeroSelecionado.getDocumentoIdentidad())));



        instanciaModelo().update(pasajeroSelecionado);
    }

}
