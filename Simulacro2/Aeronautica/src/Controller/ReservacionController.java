package Controller;

import Entity.Pasajero;
import Entity.Reservacion;
import Entity.Vuelo;
import Model.ReservacionModelo;
import Utilies.Utils;

import javax.swing.*;
import java.util.List;

public class ReservacionController {

    public static ReservacionModelo instanciaModelo() {
        return new ReservacionModelo();
    }


    public static void insert() {
        String fecha_reservacion = JOptionPane.showInputDialog("Ingresa la fecha de el vuelo : YYYY-MM-DD");
        String asiento = JOptionPane.showInputDialog("Ingresa el asiento que te corresponde: ");


        //como devuelve un arreglo de un generico se le pone OBJECT[*
        Object[] opcionPasajero = Utils.listToArray(PasajeroController.instanciaModelo().findAll());
        Object[] opcionVuelo = Utils.listToArray(VueloController.intanceModel().findAll());


        Pasajero PasajeroSelecionado = (Pasajero) JOptionPane.showInputDialog(null,
                "Seleccione el Pasajero",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionPasajero,
                opcionPasajero[0]
        );

        Vuelo VueloSelecionado = (Vuelo) JOptionPane.showInputDialog(null,
                "Seleccione el Vuelo",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionVuelo,
                opcionVuelo[0]
        );

        //AQUI SE INSERTA TODO

        instanciaModelo().insert(new Reservacion(PasajeroSelecionado.getId(), VueloSelecionado.getId(), fecha_reservacion, asiento, PasajeroSelecionado, VueloSelecionado));
        JOptionPane.showMessageDialog(null, "La reservacion se inserto correctamente se insertó correctamente");

    }

    public static void getAll() {
        String lista = getAll(instanciaModelo().findAll());
        JOptionPane.showMessageDialog(null, lista);
    }

    public static String getAll(List<Object> list) {
        String listaRegistros = "LISTA DE REGISTROS: \n";
        for (Object Temporal : list) {
            Reservacion objReservacion = (Reservacion) Temporal;
            listaRegistros += objReservacion.toString() + "\n";
        }
        return listaRegistros;

    }

    public static void delete() {
        Object[] opciones = Utils.listToArray(instanciaModelo().findAll());
        Reservacion ReservacionSeleccionada = (Reservacion) JOptionPane.showInputDialog(null,
                "Seleccione la Reservacion a Eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        instanciaModelo().delete(ReservacionSeleccionada);

    }


    public static void update(){
        Object[] opciones = Utils.listToArray(instanciaModelo().findAll());
        Reservacion Reservacionescogida = (Reservacion) JOptionPane.showInputDialog(null,
                "Seleccione la cita a modificar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        Reservacionescogida.setFechaReservacion(JOptionPane.showInputDialog(null,"Ingresa la fecha de la cita : YYYY-MM-DD",Reservacionescogida.getFechaReservacion()));
        Reservacionescogida.setAsiento(JOptionPane.showInputDialog(null,"Ingresa la hora de la cita : HH:MM:ss",Reservacionescogida.getAsiento()));

        Object[] opcionPacientes = Utils.listToArray(PasajeroController.instanciaModelo().findAll());
        Object[] opcionMedico = Utils.listToArray(VueloController.intanceModel().findAll());

        Reservacionescogida.setObjPasajero((Pasajero) JOptionPane.showInputDialog(null,
                "Seleccione el pasajero",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionPacientes,
                opcionPacientes[0]
        ));

        Reservacionescogida.setIdPasajero(Reservacionescogida.getObjPasajero().getId());

        Reservacionescogida.setObjVuelo((Vuelo) JOptionPane.showInputDialog(null,
                "Seleccione el Vuelo",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionMedico,
                opcionMedico[0]
        ));

        Reservacionescogida.setIdVuelo(Reservacionescogida.getObjVuelo().getId());
        instanciaModelo().update(Reservacionescogida);

    }




}






