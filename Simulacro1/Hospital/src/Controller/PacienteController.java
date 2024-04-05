package Controller;

import Entity.Paciente;
import Model.PacienteModelo;
import Utiles.Utils;

import javax.swing.*;
import java.util.List;

public class PacienteController {


    public static void update(){
        Object[] options = Utils.listToArray(instanciaModelo().findAll());
        Paciente pacienteSelecionado = (Paciente) JOptionPane.showInputDialog(null, "Seleccionar paciente a Actualizar", "", JOptionPane.QUESTION_MESSAGE,null, options, options[0]);

        pacienteSelecionado.setNombre(JOptionPane.showInputDialog(null, "Ingresa nuevo nomnbre: ", pacienteSelecionado.getNombre()));
        pacienteSelecionado.setApellidos(JOptionPane.showInputDialog(null, "Ingresa nuevo apellido: ", pacienteSelecionado.getApellidos()));
        pacienteSelecionado.setFechaNacimiento(JOptionPane.showInputDialog(null, "Ingresa nueva fecha de nacimiento: YYYY-MM-dd", pacienteSelecionado.getFechaNacimiento()));
        pacienteSelecionado.setDocumentoIdentidad(JOptionPane.showInputDialog(null, "Ingresa nuevo documento: ", pacienteSelecionado.getDocumentoIdentidad()));


        instanciaModelo().update(pacienteSelecionado);
    }

    public static  void delete() {
     Object[] options = Utils.listToArray(instanciaModelo().findAll());
     Paciente pacienteSelecionado = (Paciente) JOptionPane.showInputDialog(null, "Seleccionar paciente a eliminar", "", JOptionPane.QUESTION_MESSAGE,null, options, options[0]);

     instanciaModelo().delete(pacienteSelecionado);
    }


    public static void getAll(){
        String lista = getAll(instanciaModelo().findAll());
        JOptionPane.showMessageDialog(null, lista);
    }

    public static String getAll(List<Object> list){
            String listaRegistros = "LISTA DE REGISTROS: \n";
            for(Object Temporal : list){
                Paciente objPaciente = (Paciente) Temporal;
                listaRegistros += objPaciente.toString() + "\n";
            }
            return listaRegistros;
    }



    public static void insert(){
        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del paciente: ");
        String apellido = JOptionPane.showInputDialog("Ingresa los apellidos del paciente: ");
        String fechaNacimiento = JOptionPane.showInputDialog("Ingresa la fecha de nacimiento del paciente: YYYY-MM-dd");
        String documento = JOptionPane.showInputDialog("Ingresa el documento del paciente: ");

        instanciaModelo().insert(new Paciente(nombre, apellido, fechaNacimiento,documento));
    }


    public static PacienteModelo instanciaModelo(){
        return new PacienteModelo();
    }
}
