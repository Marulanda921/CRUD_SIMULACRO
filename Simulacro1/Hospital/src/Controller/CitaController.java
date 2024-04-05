package Controller;

import Entity.Cita;
import Entity.Medico;
import Entity.Paciente;
import Model.CitaModelo;
import Utiles.Utils;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class CitaController {



    public static void update(){
        Object[] opciones = Utils.listToArray(intanciaModelo().findAll());
        Cita citaescogida = (Cita) JOptionPane.showInputDialog(null,
                "Seleccione la cita a modificar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        citaescogida.setDate(JOptionPane.showInputDialog(null,"Ingresa la fecha de la cita : YYYY-MM-DD",citaescogida.getDate()));
        citaescogida.setHora(JOptionPane.showInputDialog(null,"Ingresa la hora de la cita : HH:MM:ss",citaescogida.getHora()));
        citaescogida.setMotivo(JOptionPane.showInputDialog(null,"Ingresa el motivo de la cita : ", citaescogida.getMotivo()));

        Object[] opcionPacientes = Utils.listToArray(PacienteController.instanciaModelo().findAll());
        Object[] opcionMedico = Utils.listToArray(MedicoController.intanceModel().findAll());

        citaescogida.setObjPaciente((Paciente) JOptionPane.showInputDialog(null,
                "Seleccione el paciente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionPacientes,
                opcionPacientes[0]
        ));

        citaescogida.setIdPaciente(citaescogida.getObjPaciente().getId());

        citaescogida.setObjMedico((Medico) JOptionPane.showInputDialog(null,
                "Seleccione el paciente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionMedico,
                opcionMedico[0]
        ));

        citaescogida.setIdMedico(citaescogida.getObjMedico().getId());
        intanciaModelo().update(citaescogida);

    }


    public static void delete() {
        Object[] opciones = Utils.listToArray(intanciaModelo().findAll());
        Cita citaSeleccionada = (Cita) JOptionPane.showInputDialog(null,
                "Seleccione la cita a Eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        intanciaModelo().delete(citaSeleccionada);

    }

    public static void getAll(){
        String lista = getAll(intanciaModelo().findAll());
        JOptionPane.showMessageDialog(null, lista);
    }

    public static String getAll(List<Object> list){
        String listaRegistros = "LISTA DE REGISTROS: \n";
        for(Object Temporal : list){
            Cita objCita = (Cita) Temporal;
            listaRegistros += objCita.toString() + "\n";
        }
        return listaRegistros;

    }

    public static void insert(){
        String date = JOptionPane.showInputDialog("Ingresa la fecha de la cita : YYYY-MM-DD ");
        String hora = JOptionPane.showInputDialog("Ingresa la hora de la cita : HH:MM:ss ");
        String motivo = JOptionPane.showInputDialog("Ingresa el motivo de la cita : ");

        //como devuelve un arreglo de un generico se le pone OBJECT[*
       Object[] opcionPacientes = Utils.listToArray(PacienteController.instanciaModelo().findAll());
       Object[] opcionMedico = Utils.listToArray(MedicoController.intanceModel().findAll());


       Paciente pacienteSelecionado = (Paciente) JOptionPane.showInputDialog(null,
               "Seleccione el paciente",
               "",
               JOptionPane.QUESTION_MESSAGE,
               null,
               opcionPacientes,
               opcionPacientes[0]
               );

        Medico MedicoSelecionado = (Medico) JOptionPane.showInputDialog(null,
                "Seleccione el Medico",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionMedico,
                opcionMedico[0]
        );

        //AQUI SE INSERTA TODO

        intanciaModelo().insert(new Cita(date,hora,motivo,pacienteSelecionado.getId(),MedicoSelecionado.getId(), MedicoSelecionado, pacienteSelecionado));
        JOptionPane.showMessageDialog(null, "La cita se insertó correctamente");


    }

    public static CitaModelo intanciaModelo(){
        return new CitaModelo();
    }



}
