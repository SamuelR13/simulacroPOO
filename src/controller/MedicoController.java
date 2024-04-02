package controller;

import entity.Medico;
import model.MedicoModel;

import javax.swing.*;

public class MedicoController {
    public static void getAll() {
        MedicoModel objModel = new MedicoModel();
        String listMedico = "🤷LISTA DE MEDICOS"+"\n";

        for (Object iterador : objModel.findAll()) {
            Medico objMedico = (Medico) iterador;
            listMedico += objMedico.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listMedico);
    }

    public static String getAllString() {
        MedicoModel objModel = new MedicoModel();
        String listMedico = "🤷LISTA DE MEDICOS\n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Medico
            Medico objMedico = (Medico) iterador;
            listMedico += objMedico.toString() + "\n";
        }

        return listMedico;
    }
//    public static void create(){
//        MedicoModel objModel = new MedicoModel();
//        String nombre = JOptionPane.showInputDialog("Escribe el nombre del nuevo Medico\n");
//        String apellidos = JOptionPane.showInputDialog("Escribe los apellidos del nuevo Medico\n");
//        String fecha_nacimiento = JOptionPane.showInputDialog("Escribe la fecha de nacimiento del paciente\n"+"AAAA/MM/DD");
//        String documento_identidad = JOptionPane.showInputDialog("Escribe el documento de identidad del paciente\n"+"Sin puntos ni comas ej:1000089781");
//        Medico objMedico = new Medico();
//        objMedico.setNombre(nombre);
//        objMedico.setApellidos(apellidos);
//        objMedico.setFecha_nacimiento(fecha_nacimiento);
//        objMedico.setDocumento_identidad(documento_identidad);
//        objModel.insert(objMedico);
//        JOptionPane.showMessageDialog(null,objMedico.toString());
//    }
//    public static void delete() {
//        MedicoModel objMedicoModel = new MedicoModel();
//        String listMedico = getAllString();
//        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listMedico + "\n Ingresa el ID del Medico que deseas eliminar\n"));
//        Medico objMedico = MedicoModel.findById(idDelete);
//
//        if (objMedico== null){
//            JOptionPane.showMessageDialog(null,"Medico no encontrada");
//        }else {
//            int confirm = JOptionPane.showConfirmDialog(null,"Estas seguro que deseas eliminar este Medico?\nTambién se eliminara todos lo relacionado con este Medico \n"+ objMedico.toString());
//            if (confirm == 0) MedicoModel.delete(objMedico);
//        }
//    }
//    public static void update(){
//        MedicoModel objModel = new MedicoModel();
//        String listMedico = getAllString();
//        int searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listMedico + "\nIngresa el ID del Medico que deseas actualizar"));
//        Medico objMedico = objModel.findById(searchId);
//
//        if(objMedico==null){
//            JOptionPane.showMessageDialog(null,"Medico no encontrado");
//        }else{
//            String nombre = JOptionPane.showInputDialog("Escribe el nuevo nombre del Medico\n",objMedico.getNombre());
//            String apellidos = JOptionPane.showInputDialog("Escribe los nuevos apellidos del nuevo Medico\n",objMedico.getApellidos());
//            String fecha_nacimiento = JOptionPane.showInputDialog("Escribe la nueva fecha de nacimiento del paciente\n"+"AAAA/MM/DD",objMedico.getFecha_nacimiento());
//            String documento_identidad = JOptionPane.showInputDialog("Escribe el nuevo documento de identidad del paciente\n"+"Sin puntos ni comas ej:1000089781",objMedico.getDocumento_identidad());
//            objMedico.setId_paciente(objMedico.getId_paciente());
//            objMedico.setNombre(nombre);
//            objMedico.setApellidos(apellidos);
//            objMedico.setFecha_nacimiento(fecha_nacimiento);
//            objMedico.setDocumento_identidad(documento_identidad);
//            System.out.println(objMedico);
//            objModel.update(objMedico);
//        }
//
//    }
}
