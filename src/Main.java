import javax.swing.*;
import java.text.BreakIterator;

public class Main {
    public static void main(String[] args) {

        String option = "";
        String optionEspe = "";

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
                                break;
                            case "2":
                                break;
                            case "3":
                                break;
                            case "4":
                                break;
                            case "5":
                                break;
                            case "6":
                                break;
                        }
                    }while (!optionEspe.equals("7"));
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
            }
        }while (!option.equals("5"));

    }
}
