package controller;

import entity.Especialidad;
import entity.Medico;
import entity.Medico;
import model.EspecialidadModel;
import model.MedicoModel;
import model.MedicoModel;

import javax.swing.*;

public class MedicoController {
    public static void getAll() {
        MedicoModel objModel = new MedicoModel();
        String listMedico = "LISTA DE MEDICOS"+"\n";

        for (Object iterador : objModel.findAll()) {
            Medico objMedico = (Medico) iterador;
            listMedico += objMedico.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listMedico);
    }

    public static String getAllString() {
        MedicoModel objModel = new MedicoModel();
        String listMedico = "LISTA DE MEDICOS"+"\n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Medico
            Medico objMedico = (Medico) iterador;
            listMedico += objMedico.toString() + "\n";
        }

        return listMedico;
    }
    public static void create(){
        MedicoModel objModel = new MedicoModel();
        String nombre = JOptionPane.showInputDialog("Escribe el nombre del nuevo Medico\n");
        String apellidos = JOptionPane.showInputDialog("Escribe los apellidos del nuevo Medico\n");
        int id_especialidad = Integer.parseInt(JOptionPane.showInputDialog(EspecialidadController.getAllString()+"\n"+"Ingresa el ID de la Especialidad del nuevo Medico"));
        EspecialidadModel objEspe = new EspecialidadModel();
        Medico objMedico = new Medico();
        objMedico.setNombre(nombre);
        objMedico.setApellidos(apellidos);
        objMedico.setEspecialidad(objEspe.findById(id_especialidad));
        objModel.insert(objMedico);
        JOptionPane.showMessageDialog(null,objMedico.toString());
    }
    public static void delete() {
        MedicoModel objMedicoModel = new MedicoModel();
        String listMedico = getAllString();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listMedico + "\n Ingresa el ID del Medico que deseas eliminar\n"));
        Medico objMedico = objMedicoModel.findById(idDelete);

        if (objMedico== null){
            JOptionPane.showMessageDialog(null,"Medico no encontrada");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null,"Estas seguro que deseas eliminar este Medico?\nTambién se eliminara todos lo relacionado con este Medico \n"+ objMedico.toString());
            if (confirm == 0) objMedicoModel.delete(objMedico);
        }
    }
    public static void update(){
        MedicoModel objModel = new MedicoModel();
        String listMedico = getAllString();
        int searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listMedico + "\nIngresa el ID del Medico que deseas actualizar"));
        Medico objMedico = objModel.findById(searchId);

        if(objMedico==null){
            JOptionPane.showMessageDialog(null,"Medico no encontrado");
        }else{
            String nombre = JOptionPane.showInputDialog("Escribe el nuevo nombre del Medico\n",objMedico.getNombre());
            String apellidos = JOptionPane.showInputDialog("Escribe los nuevos apellidos del Medico\n",objMedico.getApellidos());
            int id_especialidad = Integer.parseInt(JOptionPane.showInputDialog(EspecialidadController.getAllString()+"\n"+"Ingresa el ID de la Especialidad del nuevo Medico\n",objMedico.getEspecialidad().getId_especialidad()));
            EspecialidadModel objEspe = new EspecialidadModel();
            objMedico.setNombre(nombre);
            objMedico.setApellidos(apellidos);
            objMedico.setEspecialidad(objEspe.findById(id_especialidad));
            System.out.println(objMedico);
            objModel.update(objMedico);
        }

    }
    public static void getByName(){
        MedicoModel objModel = new MedicoModel();
        String name = JOptionPane.showInputDialog("Ingresa el nombre");
        String Medico = "";
        for (Medico temporal :objModel.findByName(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Medico no encontrado");
            }else{
                Medico+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Medico);
    }
    public static void getByLastname(){
        MedicoModel objModel = new MedicoModel();
        String name = JOptionPane.showInputDialog("Ingresa los apellidos");
        String Medico = "";
        for (Medico temporal :objModel.findByLastname(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Apellidos no encontrados");
            }else{
                Medico+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Medico);
    }


    public static void getByEspe(){
        MedicoModel objModel = new MedicoModel();
        int name = Integer.parseInt(JOptionPane.showInputDialog(EspecialidadController.getAllString(), "Ingresa el ID de la especialidad que deseas Buscar"));
        String Medico = "";
        for (Medico temporal :objModel.findByEspe(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Medicos no encontrados");
            }else{
                Medico+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Medico);
    }
    public static void getById(){
        MedicoModel objModel = new MedicoModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID"));
        Medico objEspe = new Medico();
        objEspe = objModel.findById(id);
        if (objEspe==null){
            JOptionPane.showMessageDialog(null,"ID no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objEspe.toString());
        }
    }
}
