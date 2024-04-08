import controller.AvionController;
import controller.ReservacionController;
import controller.VueloController;
import controller.PasajeroController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String option = "";
        String optionAvion = "";
        String optionPasajero   = "";
        String optionVuelo = "";
        String optionRe = "";


        do {
            option = JOptionPane.showInputDialog("""
                    1. Menú Aviones
                    2. Menú Pasajeros 
                    3. Menú Vuelos
                    4. Menú Reservaciones
                    
                    5. Salir
                    
                    Ingresa un opción:
                    
                    """);
            switch (option){
                case "1":
                    do {
                        optionAvion = JOptionPane.showInputDialog("""
                                1. Listar todas los Aviones
                                2. Agregar Avion
                                3. Actualizar Avion
                                4. Eliminar Avion
                                5. Buscar Avion por Modelo
                                6. Buscar Avion por Capacidad
                                7. Buscar Avion por ID
                                
                                
                                8. Regresar
                                
                                Ingresa una opción:
                            
                                """);
                        switch (optionAvion){
                            case "1":
                                AvionController.getAll();
                                break;
                            case "2":
                                AvionController.create();
                                break;
                            case "3":
                                AvionController.update();
                                break;
                            case "4":
                                AvionController.delete();
                                break;
                            case "5":
                                AvionController.getByModelo();
                                break;
                            case "6":
                                AvionController.getByCapacidad();
                                break;
                            case "7":
                                AvionController.getById();
                                break;
                        }
                    }while (!optionAvion.equals("8"));
                    break;
                case "2":
                    do {
                        optionPasajero  = JOptionPane.showInputDialog("""
                                1. Listar todos los Pasajeros
                                2. Agregar Pasajeros
                                3. Actualizar Pasajeros
                                4. Eliminar Pasajeros
                                5. Buscar Pasajeros por Nombre
                                6. Buscar Pasajeros por Apellido      
                                7. Buscar Pasajeros por Documento de identidad
                                
                                
                                
                                8. Regresar
                                
                                Ingresa una opción:
                            
                                """);
                        switch (optionPasajero ){
                            case "1":
                                PasajeroController.getAll();
                                break;
                            case "2":
                                PasajeroController.create();
                                break;
                            case "3":
                                PasajeroController.update();
                                break;
                            case "4":
                                PasajeroController.delete();
                                break;
                            case "5":
                                PasajeroController.getByName();
                                break;
                            case "6":
                                PasajeroController.getByLastname();
                                break;
                            case "7":
                                PasajeroController.getByDocument();
                                break;
                        }
                    }while (!optionPasajero.equals("8"));
                    break;
                case "3":
                    do {
                        optionVuelo = JOptionPane.showInputDialog("""
                                1. Listar todos los Vuelos
                                2. Agregar Vuelos
                                3. Actualizar Vuelos
                                4. Eliminar Vuelos
                                5. Buscar Vuelos por Destino
                                6. Buscar Vuelos por Fecha        
                                7. Buscar Vuelos por Hora
                                8. Buscar Vuelos por ID
                                9. Buscar Vuelos por Avion
                                
                                
                                10. Regresar
                                
                                Ingresa una opción:
                            
                                """);
                        switch (optionVuelo){
                            case "1":
                                VueloController.getAll();
                                break;
                            case "2":
                                VueloController.create();
                                break;
                            case "3":
                                VueloController.update();
                                break;
                            case "4":
                                VueloController.delete();
                                break;
                            case "5":
                                VueloController.getByDestino();
                                break;
                            case "6":
                                VueloController.getByFecha();
                                break;
                            case "7":
                                VueloController.getByHora();
                                break;
                            case "8":
                                VueloController.getById();
                                break;
                            case "9":
                                VueloController.getByAvion();
                                break;
                        }
                    }while (!optionVuelo.equals("10"));
                    break;
                case "4":
                    do {
                        optionRe = JOptionPane.showInputDialog("""
                                1. Listar todas las Reservaciones 
                                2. Agregar Reservacion
                                3. Actualizar Reservacion
                                4. Eliminar Reservacion
                                5. Buscar Reservacion por Nombre del Pasajero 
                                6. Buscar Reservacion por Apellidos del Pasajero        
                                7. Buscar Reservacion por Documento del Pasajero
                                8. Buscar Reservacion por ID del Vuelo 
                                9. Buscar Reservacion por ID
                                
                                
                                12. Regresar
                                
                                Ingresa una opción:
                            
                                """);
                        switch (optionRe){
                            case "1":
                                ReservacionController.getAll();
                                break;
                            case "2":
                                ReservacionController.create();
                                break;
                            case "3":
                                ReservacionController.update();
                                break;
                            case "4":
                                ReservacionController.delete();
                                break;
                            case "5":
                                ReservacionController.getByPasajeroName();
                                break;
                            case "6":
                                ReservacionController.getByPasajeroLastname();
                                break;
                            case "7":
                                ReservacionController.getByPasajeroDocument();
                                break;
                            case "8":
                                ReservacionController.getByVueloId();
                                break;
                            case "9":
                                ReservacionController.getById();
                                break;

                        }
                    }while (!optionRe.equals("12"));
                    break;
            }
        }while (!option.equals("5"));

    }
}
