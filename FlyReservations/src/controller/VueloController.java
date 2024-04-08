package controller;

import entity.Vuelo;
import model.AvionModel;
import model.VueloModel;

import javax.swing.*;

public class VueloController {
    public static void getAll() {
        VueloModel objModel = new VueloModel();
        String listVuelos = "LISTA DE VUELOS"+"\n";

        for (Object iterador : objModel.findAll()) {
            Vuelo objVuelo = (Vuelo) iterador;
            listVuelos += objVuelo.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listVuelos);
    }

    public static String getAllString() {
        VueloModel objModel = new VueloModel();
        String listVuelos = "LISTA DE VUELOS"+"\n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Vuelo
            Vuelo objVuelo = (Vuelo) iterador;
            listVuelos += objVuelo.toString() + "\n";
        }

        return listVuelos;
    }
    public static void create(){
        VueloModel objModel = new VueloModel();
        String destino = JOptionPane.showInputDialog("Escribe el destino del nuevo Vuelo\n");
        String fecha_salida = JOptionPane.showInputDialog("Escribe la fecha de salida del nuevo Vuelo\n"+"AAAA-MM-DD");
        String hora_salida = JOptionPane.showInputDialog("Escribe la hora de salida del nuevo Vuelo\n"+"hh:mm:ss");
        int id_avion = Integer.parseInt(JOptionPane.showInputDialog(AvionController.getAllString()+"\n"+"Ingresa el ID del Avion del nuevo Vuelo"));
        AvionModel objEspe = new AvionModel();
        Vuelo objVuelo = new Vuelo();
        objVuelo.setDestino(destino);
        objVuelo.setFecha_salida(fecha_salida);
        objVuelo.setHora_salida(hora_salida);
        objVuelo.setAvion(objEspe.findById(id_avion));
        objModel.insert(objVuelo);
        JOptionPane.showMessageDialog(null, objVuelo.toString());
    }
    public static void delete() {
        VueloModel objVueloModel = new VueloModel();
        String listVuelos = getAllString();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listVuelos + "\n Ingresa el ID del Vuelo que deseas eliminar\n"));
        Vuelo objVuelo = objVueloModel.findById(idDelete);

        if (objVuelo == null){
            JOptionPane.showMessageDialog(null,"Vuelo no encontrada");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null,"Estas seguro que deseas eliminar este Vuelo?\nTambién se eliminara todos lo relacionado con este Vuelo \n"+ objVuelo.toString());
            if (confirm == 0) objVueloModel.delete(objVuelo);
        }
    }
    public static void update(){
        VueloModel objModel = new VueloModel();
        String listVuelos = getAllString();
        int searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listVuelos + "\nIngresa el ID del Vuelo que deseas actualizar"));
        Vuelo objVuelo = objModel.findById(searchId);

        if(objVuelo ==null){
            JOptionPane.showMessageDialog(null,"Vuelo no encontrado");
            String destino = JOptionPane.showInputDialog("Escribe el destino del nuevo Vuelo\n");
            String fecha_salida = JOptionPane.showInputDialog("Escribe la fecha de salida del nuevo Vuelo\n"+"AAAA-MM-DD");
            String hora_salida = JOptionPane.showInputDialog("Escribe la hora de salida del nuevo Vuelo\n"+"hh:mm:ss");
            int id_avion = Integer.parseInt(JOptionPane.showInputDialog(AvionController.getAllString()+"\n"+"Ingresa el ID del Avion del nuevo Vuelo"));
            objVuelo.setDestino(destino);
            objVuelo.setFecha_salida(fecha_salida);
            objVuelo.setHora_salida(hora_salida);
            AvionModel objEspe = new AvionModel();
            objVuelo.setAvion(objEspe.findById(id_avion));
            objModel.update(objVuelo);
        }

    }
    public static void getByDestino(){
        VueloModel objModel = new VueloModel();
        String name = JOptionPane.showInputDialog("Ingresa el destino");
        String Vuelo = "";
        for (Vuelo temporal :objModel.findByDestino(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Vuelo no encontrado");
            }else{
                Vuelo+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Vuelo);
    }
    public static void getByFecha(){
        VueloModel objModel = new VueloModel();
        String name = JOptionPane.showInputDialog("Ingresa los apellidos");
        String Vuelo = "";
        for (Vuelo temporal :objModel.findByFecha(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Apellidos no encontrados");
            }else{
                Vuelo+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Vuelo);
    }


    public static void getByHora(){
        VueloModel objModel = new VueloModel();
        String name =JOptionPane.showInputDialog("Ingresa la hora que deseas Buscar");
        String Vuelo = "";
        for (Vuelo temporal :objModel.findByHora(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Vuelos no encontrados");
            }else{
                Vuelo+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Vuelo);
    }
    public static void getByAvion(){
        VueloModel objModel = new VueloModel();
        String name =JOptionPane.showInputDialog(AvionController.getAllString(), "Ingresa el ID del Avion que deseas Buscar");
        String Vuelo = "";
        for (Vuelo temporal :objModel.findByAvion(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Vuelos no encontrados");
            }else{
                Vuelo+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Vuelo);
    }
    public static void getById(){
        VueloModel objModel = new VueloModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID"));
        Vuelo objEspe = new Vuelo();
        objEspe = objModel.findById(id);
        if (objEspe==null){
            JOptionPane.showMessageDialog(null,"ID no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objEspe.toString());
        }
    }
}
