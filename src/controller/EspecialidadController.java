package controller;

import entity.Especialidad;
import model.EspecialidadModel;

import javax.swing.*;
import java.nio.file.WatchService;

public class EspecialidadController {

    public static void getAll() {
        EspecialidadModel objModel = new EspecialidadModel();
        String listEspecialidad = "LISTA DE ESPECIALIDADES"+"\n";

        for (Object iterador : objModel.findAll()) {
            Especialidad objEspecialidad = (Especialidad) iterador;
            listEspecialidad += objEspecialidad.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listEspecialidad);
    }

    public static String getAllString() {
        EspecialidadModel objModel = new EspecialidadModel();
        String listEspecialidad = "LISTA DE ESPECIALIDADES\n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Especialidad
            Especialidad objEspecialidad = (Especialidad) iterador;
            listEspecialidad += objEspecialidad.toString() + "\n";
        }

        return listEspecialidad;
    }
    public static void create(){
        EspecialidadModel objModel = new EspecialidadModel();
        String nombre = JOptionPane.showInputDialog("Escribe el nombre de la nueva especialidad\n");
        String descripcion = JOptionPane.showInputDialog("Escribe una descripcion de la nueva especialidad\n");

        Especialidad objEspe = new Especialidad();
        objEspe.setNombre(nombre);
        objEspe.setDescripcion(descripcion);
        objModel.insert(objEspe);
        JOptionPane.showMessageDialog(null,objEspe.toString());

    }
    public static void delete() {
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        String listEspecialidad = getAllString();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listEspecialidad + "\n Ingresa el ID de la especialidad que deseas eliminar\n"));
        Especialidad objEspecialidad = objEspecialidadModel.findById(idDelete);

        if (objEspecialidad== null){
            JOptionPane.showMessageDialog(null,"Especialidad no encontrada");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null,"Estas seguro que deseas eliminar esta especialidad?\nTambién se eliminaran todos los Medicos con esta especialidad \n"+ objEspecialidad.toString());
            if (confirm == 0) objEspecialidadModel.delete(objEspecialidad);
        }
    }
    public static void update(){
        EspecialidadModel objModel = new EspecialidadModel();
        String listEspecialidad = getAllString();
        int searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listEspecialidad + "\nIngresa el ID de la Especialidad que deseas actualizar"));
        Especialidad objEspecialidad = objModel.findById(searchId);
        System.out.println(objEspecialidad);

        if(objEspecialidad==null){
            JOptionPane.showMessageDialog(null,"Especialidad no encontrada");
        }else{
            String nombre = JOptionPane.showInputDialog(null,"Ingresa el nuevo nombre de la Especialidad",objEspecialidad.getNombre());
            String descripcion = JOptionPane.showInputDialog(null,"Ingresa la nueva descripcion de la Especialidad",objEspecialidad.getDescripcion());
            objEspecialidad.setId_especialidad(objEspecialidad.getId_especialidad());
            objEspecialidad.setNombre(nombre);
            objEspecialidad.setDescripcion(descripcion);
            System.out.println(objEspecialidad);
            objModel.update(objEspecialidad);
        }

    }
    public static void getByName(){
        EspecialidadModel objModel = new EspecialidadModel();
        String name = JOptionPane.showInputDialog("Ingresa el nombre");
        String Especialidad = "";
        for (Especialidad temporal :objModel.findByName(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Especialidad no encontrada");
            }else{
                Especialidad+=temporal.toString();
            }
        }
        JOptionPane.showMessageDialog(null,Especialidad);
    }

    public static void getById(){
        EspecialidadModel objModel = new EspecialidadModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID"));
        Especialidad objEspe = new Especialidad();
        objEspe = objModel.findById(id);
        if (objEspe==null){
            JOptionPane.showMessageDialog(null,"ID no encontrado");
        }else{
            JOptionPane.showMessageDialog(null,objEspe.toString());
        }
    }
}
