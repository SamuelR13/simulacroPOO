import controller.EspecialidadController;
import controller.PacienteController;

import javax.swing.*;
import java.text.BreakIterator;

public class Main {
    public static void main(String[] args) {

        String option = "";
        String optionEspe = "";
        String optionPaciente = "";

        do {
            option = JOptionPane.showInputDialog("""
                    1. Menú Especialidades
                    2. Menú Pacientes 
                    3. Menú Medicos
                    4. Menú Citas
                    
                    5. Salir
                    
                    Ingresa un opción:
                    
                    """);
            switch (option){
                case "1":
                    do {
                        optionEspe = JOptionPane.showInputDialog("""
                                1. Listar todas las Especialidades
                                2. Agregar Especialidad
                                3. Actualizar Especialidad
                                4. Eliminar Especialidad
                                5. Buscar Especialidad por Nombre
                                6. Buscar Especialidad por ID
                                
                                
                                7. Regresar
                                
                                Ingresa una opción:
                            
                                """);
                        switch (optionEspe){
                            case "1":
                                EspecialidadController.getAll();
                                break;
                            case "2":
                                EspecialidadController.create();
                                break;
                            case "3":
                                EspecialidadController.update();
                                break;
                            case "4":
                                EspecialidadController.delete();
                                break;
                            case "5":
                                EspecialidadController.getByName();
                                break;
                            case "6":
                                EspecialidadController.getById();
                                break;
                        }
                    }while (!optionEspe.equals("7"));
                    break;
                case "2":
                    do {
                        optionPaciente = JOptionPane.showInputDialog("""
                                1. Listar todos los Pacientes
                                2. Agregar Paciente
                                3. Actualizar Paciente
                                4. Eliminar Paciente
                                5. Buscar Paciente por Nombre
                                6. Buscar Paciente por Apellidos        
                                7. Buscar Paciente por Documento de identidad
                                8. BUscar Paciente por Fecha de nacimiento
                                
                                
                                9. Regresar
                                
                                Ingresa una opción:
                            
                                """);
                        switch (optionPaciente){
                            case "1":
                                PacienteController.getAll();
                                break;
                            case "2":
                                PacienteController.create();
                                break;
                            case "3":
                                PacienteController.update();
                                break;
                            case "4":
                                PacienteController.delete();
                                break;
                            case "5":
                                PacienteController.getByName();
                                break;
                            case "6":
                                PacienteController.getByLastname();
                                break;
                            case "7":
                                PacienteController.getByDocument();
                                break;
                            case "8":
                                PacienteController.getByBirthday();
                                break;
                        }
                    }while (!optionPaciente.equals("9"));
                    break;
                case "3":
                    break;
                case "4":
                    break;
            }
        }while (!option.equals("5"));

    }
}
