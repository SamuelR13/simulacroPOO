package controller;

import entity.Paciente;
import model.PacienteModel;

import javax.swing.*;

public class PacienteController {
    public static void getAll() {
        PacienteModel objModel = new PacienteModel();
        String listPaciente = "LISTA DE PACIENTES"+"\n";

        for (Object iterador : objModel.findAll()) {
            Paciente objPaciente = (Paciente) iterador;
            listPaciente += objPaciente.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listPaciente);
    }

    public static String getAllString() {
        PacienteModel objModel = new PacienteModel();
        String listPaciente = "LISTA DE PACIENTES\n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Paciente
            Paciente objPaciente = (Paciente) iterador;
            listPaciente += objPaciente.toString() + "\n";
        }

        return listPaciente;
    }
    public static void create(){
        PacienteModel objModel = new PacienteModel();
        String nombre = JOptionPane.showInputDialog("Escribe el nombre del nuevo Paciente\n");
        String apellidos = JOptionPane.showInputDialog("Escribe los apellidos del nuevo Paciente\n");
        String fecha_nacimiento = JOptionPane.showInputDialog("Escribe la fecha de nacimiento del paciente\n"+"AAAA/MM/DD");
        String documento_identidad = JOptionPane.showInputDialog("Escribe el documento de identidad del paciente\n"+"Sin puntos ni comas ej:1000089781");
        Paciente objPaciente = new Paciente();
        objPaciente.setNombre(nombre);
        objPaciente.setApellidos(apellidos);
        objPaciente.setFecha_nacimiento(fecha_nacimiento);
        objPaciente.setDocumento_identidad(documento_identidad);
        objModel.insert(objPaciente);
        JOptionPane.showMessageDialog(null,objPaciente.toString());
    }
    public static void delete() {
        PacienteModel objPacienteModel = new PacienteModel();
        String listPaciente = getAllString();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPaciente + "\n Ingresa el ID del Paciente que deseas eliminar\n"));
        Paciente objPaciente = objPacienteModel.findById(idDelete);

        if (objPaciente== null){
            JOptionPane.showMessageDialog(null,"Paciente no encontrada");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null,"Estas seguro que deseas eliminar este Paciente?\nTambién se eliminara todos lo relacionado con este Paciente \n"+ objPaciente.toString());
            if (confirm == 0) objPacienteModel.delete(objPaciente);
        }
    }
    public static void update(){
        PacienteModel objModel = new PacienteModel();
        String listPaciente = getAllString();
        int searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listPaciente + "\nIngresa el ID del Paciente que deseas actualizar"));
        Paciente objPaciente = objModel.findById(searchId);

        if(objPaciente==null){
            JOptionPane.showMessageDialog(null,"Paciente no encontrado");
        }else{
            String nombre = JOptionPane.showInputDialog("Escribe el nuevo nombre del Paciente\n",objPaciente.getNombre());
            String apellidos = JOptionPane.showInputDialog("Escribe los nuevos apellidos del nuevo Paciente\n",objPaciente.getApellidos());
            String fecha_nacimiento = JOptionPane.showInputDialog("Escribe la nueva fecha de nacimiento del paciente\n"+"AAAA/MM/DD",objPaciente.getFecha_nacimiento());
            String documento_identidad = JOptionPane.showInputDialog("Escribe el nuevo documento de identidad del paciente\n"+"Sin puntos ni comas ej:1000089781",objPaciente.getDocumento_identidad());
            objPaciente.setId_paciente(objPaciente.getId_paciente());
            objPaciente.setNombre(nombre);
            objPaciente.setApellidos(apellidos);
            objPaciente.setFecha_nacimiento(fecha_nacimiento);
            objPaciente.setDocumento_identidad(documento_identidad);
            System.out.println(objPaciente);
            objModel.update(objPaciente);
        }

    }
    public static void getByName(){
        PacienteModel objModel = new PacienteModel();
        String name = JOptionPane.showInputDialog("Ingresa el nombre");
        String Paciente = "";
        for (Paciente temporal :objModel.findByName(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Paciente no encontrado");
            }else{
                Paciente+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Paciente);
    }
    public static void getByLastname(){
        PacienteModel objModel = new PacienteModel();
        String name = JOptionPane.showInputDialog("Ingresa los apellidos");
        String Paciente = "";
        for (Paciente temporal :objModel.findByLastname(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Apellidos no encontrados");
            }else{
                Paciente+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Paciente);
    }


    public static void getByDocument(){
        PacienteModel objModel = new PacienteModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el documento"+"\n"+"Sin puntos ni comas ej:1000089781\n"));
        Paciente objPaciente = new Paciente();
        objPaciente = objModel.findByDocument(id);
        if (objPaciente==null){
            JOptionPane.showMessageDialog(null,"Documento no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objPaciente.toString());
        }
    }
    public static void getByBirthday(){
        PacienteModel objModel = new PacienteModel();
        String name = JOptionPane.showInputDialog("Ingresa la fecha de nacimiento\n"+"AAAA-MM-DD");
        String Paciente = "";
        for (Paciente temporal :objModel.findByBirthdate(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Fecha no encontrada");
            }else{
                Paciente+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Paciente);
    }
}
