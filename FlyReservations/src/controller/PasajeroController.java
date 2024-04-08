package controller;

import entity.Pasajero;
import model.PasajeroModel;

import javax.swing.*;

public class PasajeroController {
    public static void getAll() {
        PasajeroModel objModel = new PasajeroModel();
        String listPaciente = "LISTA DE PACIENTES"+"\n";

        for (Object iterador : objModel.findAll()) {
            Pasajero objPasajero = (Pasajero) iterador;
            listPaciente += objPasajero.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listPaciente);
    }

    public static String getAllString() {
        PasajeroModel objModel = new PasajeroModel();
        String listPaciente = "LISTA DE PACIENTES\n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Pasajero
            Pasajero objPasajero = (Pasajero) iterador;
            listPaciente += objPasajero.toString() + "\n";
        }

        return listPaciente;
    }
    public static void create(){
        PasajeroModel objModel = new PasajeroModel();
        String nombre = JOptionPane.showInputDialog("Escribe el nombre del nuevo Pasajero\n");
        String apellidos = JOptionPane.showInputDialog("Escribe el apellido del nuevo Pasajero\n");
        String documento_identidad = JOptionPane.showInputDialog("Escribe el documento de identidad del paciente\n"+"Sin puntos ni comas ej:1000089781");
        Pasajero objPasajero = new Pasajero();
        objPasajero.setNombre(nombre);
        objPasajero.setApellidos(apellidos);
        objPasajero.setDocumento_identidad(documento_identidad);
        objModel.insert(objPasajero);
        JOptionPane.showMessageDialog(null, objPasajero.toString());
    }
    public static void delete() {
        PasajeroModel objPasajeroModel = new PasajeroModel();
        String listPaciente = getAllString();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPaciente + "\n Ingresa el ID del Pasajero que deseas eliminar\n"));
        Pasajero objPasajero = objPasajeroModel.findById(idDelete);

        if (objPasajero == null){
            JOptionPane.showMessageDialog(null,"Pasajero no encontrada");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null,"Estas seguro que deseas eliminar este Pasajero?\nTambién se eliminara todos lo relacionado con este Pasajero \n"+ objPasajero.toString());
            if (confirm == 0) objPasajeroModel.delete(objPasajero);
        }
    }
    public static void update(){
        PasajeroModel objModel = new PasajeroModel();
        String listPaciente = getAllString();
        int searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listPaciente + "\nIngresa el ID del Pasajero que deseas actualizar"));
        Pasajero objPasajero = objModel.findById(searchId);

        if(objPasajero ==null){
            JOptionPane.showMessageDialog(null,"Pasajero no encontrado");
        }else{
            String nombre = JOptionPane.showInputDialog("Escribe el nuevo nombre del Pasajero\n", objPasajero.getNombre());
            String apellidos = JOptionPane.showInputDialog("Escribe los nuevos apellidos del nuevo Pasajero\n", objPasajero.getApellidos());
            String documento_identidad = JOptionPane.showInputDialog("Escribe el nuevo documento de identidad del paciente\n"+"Sin puntos ni comas ej:1000089781", objPasajero.getDocumento_identidad());
            objPasajero.setId_pasajero(objPasajero.getId_pasajero());
            objPasajero.setNombre(nombre);
            objPasajero.setApellidos(apellidos);
            objPasajero.setDocumento_identidad(documento_identidad);
            System.out.println(objPasajero);
            objModel.update(objPasajero);
        }

    }
    public static void getByName(){
        PasajeroModel objModel = new PasajeroModel();
        String name = JOptionPane.showInputDialog("Ingresa el nombre");
        String Paciente = "";
        for (Pasajero temporal :objModel.findByName(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Pasajero no encontrado");
            }else{
                Paciente+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Paciente);
    }
    public static void getByLastname(){
        PasajeroModel objModel = new PasajeroModel();
        String name = JOptionPane.showInputDialog("Ingresa el apellido");
        String Paciente = "";
        for (Pasajero temporal :objModel.findByLastname(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Apellidos no encontrados");
            }else{
                Paciente+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Paciente);
    }


    public static void getByDocument(){
        PasajeroModel objModel = new PasajeroModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el documento"+"\n"+"Sin puntos ni comas ej:1000089781\n"));
        Pasajero objPasajero = new Pasajero();
        objPasajero = objModel.findByDocument(id);
        if (objPasajero ==null){
            JOptionPane.showMessageDialog(null,"Documento no encontrado");
        }else{
            JOptionPane.showMessageDialog(null, objPasajero.toString());
        }
    }
}
