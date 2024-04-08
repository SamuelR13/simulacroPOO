package controller;

import entity.Especialidad;
import entity.Cita;
import entity.Cita;
import model.*;
import model.CitaModel;

import javax.swing.*;

public class CitaController {
    public static void getAll() {
        CitaModel objCitaModel = new CitaModel();
        String listCita = "LISTA DE CITAS"+"\n";

        for (Object iterador : objCitaModel.findAll()) {
            Cita objCita = (Cita) iterador;
            listCita += objCita.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listCita);
    }

    public static String getAllString() {
        CitaModel objCitaModel = new CitaModel();
        String listCita = "LISTA DE CITAS"+"\n";

        for (Object iterador : objCitaModel.findAll()) {
            //Convertimos del Object a Cita
            Cita objCita = (Cita) iterador;
            listCita += objCita.toString() + "\n";
        }

        return listCita;
    }
    public static void create(){
        CitaModel objCitaModel = new CitaModel();
        MedicoModel objMedicoModel = new MedicoModel();
        PacienteModel objPacienteModel = new PacienteModel();
        EspecialidadModel objEspeModel = new EspecialidadModel();


        int paciente = Integer.parseInt(JOptionPane.showInputDialog(PacienteController.getAllString()+
                "\nIngresa el ID del Paciente que pidio su Cita"));
        int medico = Integer.parseInt(JOptionPane.showInputDialog(MedicoController.getAllString()+
                "\nIngresa el ID del Medico que se asignara a la Cita"));
        String fecha = JOptionPane.showInputDialog("Ingresa la fecha de la cita\nAAAA-MM-DD");
        String hora = JOptionPane.showInputDialog("Ingresa la hora de la cita\n hh:mm:ss");
        String motivo = JOptionPane.showInputDialog("Ingresa el motivo de la cita");

        Cita objCita = new Cita();
        objCita.setPaciente(objPacienteModel.findById(paciente));
        objCita.setMedico(objMedicoModel.findById(medico));
        objCita.setFecha_cita(fecha);
        objCita.setHora_cita(hora);
        objCita.setMotivo(motivo);
        objCitaModel.insert(objCita);
        JOptionPane.showMessageDialog(null,objCita.toString());
    }
    public static void delete() {
        CitaModel objCitaModel = new CitaModel();
        String listCita = getAllString();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listCita + "\n Ingresa el ID del Cita que deseas eliminar\n"));
        Cita objCita = objCitaModel.findById(idDelete);

        if (objCita== null){
            JOptionPane.showMessageDialog(null,"Cita no encontrada");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null,"Estas seguro que deseas eliminar esta Cita?\nTambién se eliminara todos lo relacionado con esta Cita \n"+ objCita.toString());
            if (confirm == 0) objCitaModel.delete(objCita);
        }
    }
    public static void update(){
        CitaModel objCitaModel = new CitaModel();
        MedicoModel objMedicoModel = new MedicoModel();
        PacienteModel objPacienteModel = new PacienteModel();
        EspecialidadModel objEspeModel = new EspecialidadModel();
        String listCita = getAllString();
        int searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listCita + "\nIngresa el ID del Cita que deseas actualizar"));
        Cita objCita = objCitaModel.findById(searchId);

        if(objCita==null){
            JOptionPane.showMessageDialog(null,"Cita no encontrado");
        }else{
            int paciente = Integer.parseInt(JOptionPane.showInputDialog(PacienteController.getAllString()+
                    "\nIngresa el ID del Paciente que pidio su Cita",objCita.getPaciente().getId_paciente()));
            int medico = Integer.parseInt(JOptionPane.showInputDialog(MedicoController.getAllString()+
                    "\nIngresa el ID del Medico que se asignara a la Cita",objCita.getMedico().getId_medico()));
            String fecha = JOptionPane.showInputDialog("Ingresa la fecha de la cita\nAAAA-MM-DD",objCita.getFecha_cita());
            String hora = JOptionPane.showInputDialog("Ingresa la hora de la cita\n hh:mm:ss",objCita.getHora_cita());
            String motivo = JOptionPane.showInputDialog("Ingresa el motivo de la cita",objCita.getMotivo());

            objCita.setPaciente(objPacienteModel.findById(paciente));
            objCita.setMedico(objMedicoModel.findById(medico));
            objCita.setFecha_cita(fecha);
            objCita.setHora_cita(hora);
            objCita.setMotivo(motivo);

            objCitaModel.update(objCita);
            JOptionPane.showMessageDialog(null,objCita.toString());
        }

    }
    public static void getByPacienteName(){
        CitaModel objCitaModel = new CitaModel();
        String name = JOptionPane.showInputDialog("Ingresa el nombre");
        String Cita = "";
        for (Cita temporal :objCitaModel.findByPacienteName(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Cita no encontrado");
            }else{
                Cita+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Cita);
    }
    public static void getByPacienteLastname(){
        CitaModel objCitaModel = new CitaModel();
        String name = JOptionPane.showInputDialog("Ingresa los apellidos");
        String Cita = "";
        for (Cita temporal :objCitaModel.findByPacienteLastname(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Apellidos no encontrados");
            }else{
                Cita+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Cita);
    }
    public static void getByMedicoName(){
        CitaModel objCitaModel = new CitaModel();
        String name = JOptionPane.showInputDialog("Ingresa el nombre");
        String Cita = "";
        for (Cita temporal :objCitaModel.findByMedicoName(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Cita no encontrado");
            }else{
                Cita+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Cita);
    }
    public static void getByMedicoLastname(){
        CitaModel objCitaModel = new CitaModel();
        String name = JOptionPane.showInputDialog("Ingresa los apellidos");
        String Cita = "";
        for (Cita temporal :objCitaModel.findByMedicoLastname(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Apellidos no encontrados");
            }else{
                Cita+=temporal.toString()+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,Cita);
    }
    public static void getByPacienteId(){
        CitaModel objCitaModel = new CitaModel();
        String id = JOptionPane.showInputDialog("Ingresa el Documento del paciente sin puntos ni comas ej: 1000089781");
        Cita objEspe = new Cita();
        objEspe = objCitaModel.findByPacienteDocument(id);
        if (objEspe==null){
            JOptionPane.showMessageDialog(null,"ID no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objEspe.toString());
        }
    }
    public static void getByMedicoId(){
        CitaModel objCitaModel = new CitaModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID"));
        Cita objEspe = new Cita();
        objEspe = objCitaModel.findByMedicoId(id);
        if (objEspe==null){
            JOptionPane.showMessageDialog(null,"ID no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objEspe.toString());
        }
    }
    public static void getById(){
        CitaModel objCitaModel = new CitaModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID"));
        Cita objEspe = new Cita();
        objEspe = objCitaModel.findById(id);
        if (objEspe==null){
            JOptionPane.showMessageDialog(null,"ID no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objEspe.toString());
        }
    }
}

