import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;

import javax.swing.*;
import java.text.BreakIterator;

public class Main {
    public static void main(String[] args) {

        String option = "";
        String optionEspe = "";
        String optionPaciente = "";
        String optionMedico = "";
        String optionCita = "";


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
                    do {
                        optionMedico = JOptionPane.showInputDialog("""
                                1. Listar todos los Medicos
                                2. Agregar Medico
                                3. Actualizar Medico
                                4. Eliminar Medico
                                5. Buscar Medico por Nombre
                                6. Buscar Medico por Apellidos        
                                7. Buscar Medico por Especialidad
                                8. Buscar Medico por ID
                                
                                
                                9. Regresar
                                
                                Ingresa una opción:
                            
                                """);
                        switch (optionMedico){
                            case "1":
                                MedicoController.getAll();
                                break;
                            case "2":
                                MedicoController.create();
                                break;
                            case "3":
                                MedicoController.update();
                                break;
                            case "4":
                                MedicoController.delete();
                                break;
                            case "5":
                                MedicoController.getByName();
                                break;
                            case "6":
                                MedicoController.getByLastname();
                                break;
                            case "7":
                                MedicoController.getByEspe();
                                break;
                            case "8":
                                MedicoController.getById();
                                break;
                        }
                    }while (!optionMedico.equals("9"));
                    break;
                case "4":
                    do {
                        optionCita = JOptionPane.showInputDialog("""
                                1. Listar todas las Citas
                                2. Agregar Cita
                                3. Actualizar Cita
                                4. Eliminar Cita
                                5. Buscar Cita por Nombre del Paciente 
                                6. Buscar Cita por Apellidos del Paciente        
                                7. Buscar Cita por Documento del Paciente
                                8. Buscar Cita por Nombre del Medico 
                                9. Buscar Cita por Apellidos del Medico        
                                10. Buscar Cita por ID del Medico
                                11. Buscar Cita por ID
                                
                                
                                12. Regresar
                                
                                Ingresa una opción:
                            
                                """);
                        switch (optionCita){
                            case "1":
                                CitaController.getAll();
                                break;
                            case "2":
                                CitaController.create();
                                break;
                            case "3":
                                CitaController.update();
                                break;
                            case "4":
                                CitaController.delete();
                                break;
                            case "5":
                                CitaController.getByPacienteName();
                                break;
                            case "6":
                                CitaController.getByPacienteLastname();
                                break;
                            case "7":
                                CitaController.getByPacienteId();
                                break;
                            case "8":
                                CitaController.getByMedicoName();
                                break;
                            case "9":
                                CitaController.getByMedicoLastname();
                                break;
                            case "10":
                                CitaController.getByMedicoId();
                                break;
                            case "11":
                                CitaController.getById();
                                break;
                        }
                    }while (!optionCita.equals("12"));
                    break;
            }
        }while (!option.equals("5"));

    }
}
