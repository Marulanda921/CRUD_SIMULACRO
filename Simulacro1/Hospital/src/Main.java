import Controller.CitaController;
import Controller.EspecialidadController;
import Controller.MedicoController;
import Controller.PacienteController;
import Database.ConfigDb;
import Model.CitaModelo;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {


        int option =0, option2 = 0;
        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Administrar Especializaciones
                    2. Administrar Medicos
                    3. Administrar Pacientes
                    4. Administrar Citas 
                    5. salir
                    
                    Ingresa una opcion: 
                    """));
            switch (option) {
                case 1:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Listar Especializaciones
                    2. Crear Especialidad
                    3. Eliminar Especialidad
                    4. Actualizar Especialidad 
                    5. salir
                    
                    Ingresa una opcion: 
                    """));
                    switch (option2) {
                        case 1:
                            EspecialidadController.getAll();
                            break;
                        case 2:
                            EspecialidadController.insert();
                            break;
                        case 3:
                            EspecialidadController.delete();
                            break;
                        case 4:
                            EspecialidadController.update();
                            break;
                    }
                    }while (option2 != 5);
                    break;
                case 2:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Listar Medicos
                    2. Crear Medicos
                    3. Eliminar Medicos
                    4. Actualizar Medicos 
                    5. salir
                    
                    Ingresa una opcion: 
                                """));
                        switch (option2) {
                            case 1:
                               MedicoController.getAll();
                                break;
                            case 2:
                                MedicoController.insert();
                                break;
                            case 3:
                                MedicoController.delete();
                                break;
                            case 4:
                                MedicoController.update();
                                break;
                        }
                    }while (option2 != 5);
                    break;
                case 3:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Listar Pacientes
                    2. Crear Pacientes
                    3. Eliminar Pacientes
                    4. Actualizar Pacientes 
                    5. salir
                    
                    Ingresa una opcion: 
                                """));
                        switch (option2) {
                            case 1:
                                PacienteController.getAll();
                                break;
                            case 2:
                                PacienteController.insert();
                                break;
                            case 3:
                                PacienteController.delete();
                                break;
                            case 4:
                                PacienteController.update();
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
                                CitaController.getAll();
                                break;
                            case 2:
                                CitaController.insert();
                                break;
                            case 3:
                                CitaController.delete();
                                break;
                            case 4:
                                CitaController.update();
                                break;
                        }

                    }while (option2 != 5);
                    break;
            }

        }while (option != 5);
    }
}