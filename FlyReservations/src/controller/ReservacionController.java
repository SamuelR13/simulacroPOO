package controller;

import entity.Reservacion;
import model.*;
import model.ReservacionModel;

import javax.swing.*;

public class ReservacionController {
    public static void getAll() {
        ReservacionModel objReservacionModel = new ReservacionModel();
        String listReservacion = "LISTA DE RESERVACIONES"+"\n";

        for (Object iterador : objReservacionModel.findAll()) {
            Reservacion objReservacion = (Reservacion) iterador;
            listReservacion += objReservacion.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listReservacion);
    }

    public static String getAllString() {
        ReservacionModel objReservacionModel = new ReservacionModel();
        String listReservacion = "LISTA DE RESERVACIONES"+"\n";

        for (Object iterador : objReservacionModel.findAll()) {
            //Convertimos del Object a Reservacion
            Reservacion objReservacion = (Reservacion) iterador;
            listReservacion += objReservacion.toString() + "\n";
        }

        return listReservacion;
    }
    public static void create(){
        ReservacionModel objReservacionModel = new ReservacionModel();
        VueloModel objVueloModel = new VueloModel();
        PasajeroModel objPasajeroModel = new PasajeroModel();
        AvionModel objEspeModel = new AvionModel();


        int pasajero = Integer.parseInt(JOptionPane.showInputDialog(PasajeroController.getAllString()+
                "\nIngresa el ID del Pasajero que pidio su Reservacion"));
        int vuelo = Integer.parseInt(JOptionPane.showInputDialog(VueloController.getAllString()+
                "\nIngresa el ID del Vuelo que se asignara a la Reservacion"));
        String fecha = JOptionPane.showInputDialog("Ingresa la fecha de la Reservacion\nAAAA-MM-DD");
        String asiento = JOptionPane.showInputDialog("Ingresa el asiento de la Reservacion");

        Reservacion objReservacion = new Reservacion();
        objReservacion.setPasajero(objPasajeroModel.findById(pasajero));
        objReservacion.setVuelo(objVueloModel.findById(vuelo));
        objReservacion.setFecha_reservacion(fecha);
        objReservacion.setAsiento(asiento);
        objReservacionModel.insert(objReservacion);
        JOptionPane.showMessageDialog(null,objReservacion.toString());
    }
    public static void delete() {
        ReservacionModel objReservacionModel = new ReservacionModel();
        String listReservacion = getAllString();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listReservacion + "\n Ingresa el ID del Reservacion que deseas eliminar\n"));
        Reservacion objReservacion = objReservacionModel.findById(idDelete);

        if (objReservacion== null){
            JOptionPane.showMessageDialog(null,"Reservacion no encontrada");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null,"Estas seguro que deseas eliminar esta Reservacion?\nTambién se eliminara todos lo relacionado con esta Reservacion \n"+ objReservacion.toString());
            if (confirm == 0) objReservacionModel.delete(objReservacion);
        }
    }
    public static void update(){
        ReservacionModel objReservacionModel = new ReservacionModel();
        VueloModel objVueloModel = new VueloModel();
        PasajeroModel objPasajeroModel = new PasajeroModel();
        AvionModel objEspeModel = new AvionModel();
        String listReservacion = getAllString();
        int searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listReservacion + "\nIngresa el ID del Reservacion que deseas actualizar"));
        Reservacion objReservacion = objReservacionModel.findById(searchId);

        if(objReservacion==null){
            JOptionPane.showMessageDialog(null,"Reservacion no encontrado");
        }else{
            int pasajero = Integer.parseInt(JOptionPane.showInputDialog(PasajeroController.getAllString()+
                    "\nIngresa el ID del Pasajero que pidio su Reservacion",objReservacion.getPasajero().getId_pasajero()));
            int vuelo = Integer.parseInt(JOptionPane.showInputDialog(VueloController.getAllString()+
                    "\nIngresa el ID del Vuelo que se asignara a la Reservacion",objReservacion.getVuelo().getId_vuelo()));
            String fecha = JOptionPane.showInputDialog("Ingresa la fecha de la Reservacion\nAAAA-MM-DD",objReservacion.getFecha_reservacion());
            String motivo = JOptionPane.showInputDialog("Ingresa el asiento de la Reservacion",objReservacion.getAsiento());

            objReservacion.setPasajero(objPasajeroModel.findById(pasajero));
            objReservacion.setVuelo(objVueloModel.findById(vuelo));
            objReservacion.setFecha_reservacion(fecha);
                objReservacion.setAsiento(motivo);

            objReservacionModel.update(objReservacion);
            JOptionPane.showMessageDialog(null,objReservacion.toString());
        }

    }
    public static void getByPasajeroName(){
        ReservacionModel objReservacionModel = new ReservacionModel();
        String name = JOptionPane.showInputDialog("Ingresa el nombre");
        String Reservacion = "";
        for (Reservacion temporal :objReservacionModel.findByPasajeroName(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Reservacion no encontrado");
            }else{
                Reservacion+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Reservacion);
    }
    public static void getByPasajeroLastname(){
        ReservacionModel objReservacionModel = new ReservacionModel();
        String name = JOptionPane.showInputDialog("Ingresa los apellidos");
        String Reservacion = "";
        for (Reservacion temporal :objReservacionModel.findByPasajeroLastname(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Apellidos no encontrados");
            }else{
                Reservacion+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Reservacion);
    }


    public static void getByPasajeroDocument(){
        ReservacionModel objReservacionModel = new ReservacionModel();
        String id = JOptionPane.showInputDialog("Ingresa el Documento del pasajero sin puntos ni comas ej: 1000089781");
        Reservacion objEspe = new Reservacion();
        objEspe = objReservacionModel.findByPasajeroDocument(id);
        if (objEspe==null){
            JOptionPane.showMessageDialog(null,"ID no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objEspe.toString());
        }
    }
    public static void getByVueloId(){
        ReservacionModel objReservacionModel = new ReservacionModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID"));
        Reservacion objEspe = new Reservacion();
        objEspe = objReservacionModel.findByVueloId(id);
        if (objEspe==null){
            JOptionPane.showMessageDialog(null,"ID no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objEspe.toString());
        }
    }
    public static void getById(){
        ReservacionModel objReservacionModel = new ReservacionModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID"));
        Reservacion objEspe = new Reservacion();
        objEspe = objReservacionModel.findById(id);
        if (objEspe==null){
            JOptionPane.showMessageDialog(null,"ID no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objEspe.toString());
        }
    }

}
