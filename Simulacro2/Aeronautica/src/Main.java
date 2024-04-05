import Controller.AvionController;
import Controller.PasajeroController;
import Controller.VueloController;
import Database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        int option =0, option2 = 0;
        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Administrar Aviones
                    2. Administrar Vuelos
                    3. Administrar Pasajeros
                    4. Administrar Reservaciones 
                    5. salir
                    
                    Ingresa una opcion: 
                    """));
            switch (option) {
                case 1:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Listar Aviones
                    2. Crear Avion
                    3. Eliminar Avion
                    4. Actualizar Avion 
                    5. salir
                    
                    Ingresa una opcion: 
                    """));
                        switch (option2) {
                            case 1:
                                AvionController.getAll();
                                break;
                            case 2:
                                AvionController.insert();
                                break;
                            case 3:
                                 AvionController.delete();
                                break;
                            case 4:
                                 AvionController.update();
                                break;
                        }
                    }while (option2 != 5);
                    break;
                case 2:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Listar Vuelos
                    2. Crear Vuelos
                    3. Eliminar Vuelos
                    4. Actualizar Vuelos 
                    5. salir
                    
                    Ingresa una opcion: 
                                """));
                        switch (option2) {
                            case 1:
                               VueloController.getAll();
                                break;
                            case 2:
                                VueloController.insert();
                                break;
                            case 3:
                                VueloController.delete();
                                break;
                            case 4:
                                VueloController.update();
                                break;
                        }
                    }while (option2 != 5);
                    break;
                case 3:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Listar Pasajeros
                    2. Crear Pasajeros
                    3. Eliminar Pasajeros
                    4. Actualizar Pasajeros 
                    5. salir
                    
                    Ingresa una opcion: 
                                """));
                        switch (option2) {
                            case 1:
                                PasajeroController.getAll();
                                break;
                            case 2:
                                PasajeroController.insert();
                                break;
                            case 3:
                                 PasajeroController.delete();
                                break;
                            case 4:
                                PasajeroController.update();
                                break;
                        }

                    }while (option2 != 5);
                    break;
                case 4:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Listar Citas
                    2. Crear Citas
                    3. Eliminar Citas
                    4. Actualizar Citas 
                    5. salir
                    
                    Ingresa una opcion: 
                                """));
                        switch (option2) {
                            case 1:
                                //CitaController.getAll();
                                break;
                            case 2:
                                // CitaController.insert();
                                break;
                            case 3:
                                // CitaController.delete();
                                break;
                            case 4:
                                // CitaController.update();
                                break;
                        }

                    }while (option2 != 5);
                    break;
            }

        }while (option != 5);
    }
}
