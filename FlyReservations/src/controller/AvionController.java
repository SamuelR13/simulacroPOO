package controller;

import entity.Avion;
import model.AvionModel;

import javax.swing.*;

public class AvionController {

    public static void getAll() {
        AvionModel objModel = new AvionModel();
        String listAvion = "LISTA DE AVIONES"+"\n";

        for (Object iterador : objModel.findAll()) {
            Avion objAvion = (Avion) iterador;
            listAvion += objAvion.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listAvion);
    }

    public static String getAllString() {
        AvionModel objModel = new AvionModel();
        String listAvion = "LISTA DE AVIONES\n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Avion
            Avion objAvion = (Avion) iterador;
            listAvion += objAvion.toString() + "\n";
        }

        return listAvion;
    }
    public static void create(){
        AvionModel objModel = new AvionModel();
        String nombre = JOptionPane.showInputDialog("Escribe el nombre del nuevo Avion\n");
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Escribe la capacidad del Avion\n")) ;

        Avion objAvion = new Avion();
        objAvion.setModelo(nombre);
        objAvion.setCapacidad(capacidad);
        objModel.insert(objAvion);
        JOptionPane.showMessageDialog(null,objAvion.toString());

    }
    public static void delete() {
        AvionModel objAvionModel = new AvionModel();
        String listAvion = getAllString();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAvion + "\n Ingresa el ID del Avion que deseas eliminar\n"));
        Avion objAvion = objAvionModel.findById(idDelete);

        if (objAvion == null){
            JOptionPane.showMessageDialog(null,"Avion no encontrada");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null,"Estas seguro que deseas eliminar este avion?\nTambién se eliminaran todo lo relacionado con este avion \n"+ objAvion.toString());
            if (confirm == 0) objAvionModel.delete(objAvion);
        }
    }
    public static void update(){
        AvionModel objModel = new AvionModel();
        String listAvion = getAllString();
        int searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listAvion + "\nIngresa el ID de la Avion que deseas actualizar"));
        Avion objAvion = objModel.findById(searchId);
        System.out.println(objAvion);

        if(objAvion ==null){
            JOptionPane.showMessageDialog(null,"Avion no encontrada");
        }else{
            String nombre = JOptionPane.showInputDialog(null,"Ingresa el nuevo nombre del Avion", objAvion.getModelo());
            int capacidad =Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa la nueva capacidad del Avion", objAvion.getCapacidad())) ;
            objAvion.setId_avion(objAvion.getId_avion());
            objAvion.setModelo(nombre);
            objAvion.setCapacidad(capacidad);
            System.out.println(objAvion);
            objModel.update(objAvion);
        }

    }
    public static void getByModelo(){
        AvionModel objModel = new AvionModel();
        String name = JOptionPane.showInputDialog("Ingresa el modelo");
        String Avion = "";
        for (Avion temporal :objModel.findByModelo(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Avion no encontrada");
            }else{
                Avion+=temporal.toString();
            }
        }
        JOptionPane.showMessageDialog(null,Avion);
    }
    public static void getByCapacidad(){
        AvionModel objModel = new AvionModel();
        int capacidad =Integer.parseInt(JOptionPane.showInputDialog("Ingresa la capacidad"));
        String Avion = "";
        for (Avion temporal :objModel.findByCapacidad(capacidad)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Avion no encontrada");
            }else{
                Avion+=temporal.toString();
            }
        }
        JOptionPane.showMessageDialog(null,Avion);
    }

    public static void getById(){
        AvionModel objModel = new AvionModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID"));
        Avion objAvion = new Avion();
        objAvion = objModel.findById(id);
        if (objAvion==null){
            JOptionPane.showMessageDialog(null,"ID no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objAvion.toString());
        }
    }
}
