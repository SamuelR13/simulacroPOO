package model;

import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;
import entity.Medico;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = (Medico) obj;

        try {
            String sql="INSERT INTO Medico (nombre,apellidos,id_especialidad) VALUES (?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellidos());
            objPrepare.setInt(3,objMedico.getEspecialidad().getId_especialidad());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objMedico.setId_medico(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Insert was succesful");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        };
        ConfigDB.closeConnection();
        return objMedico;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listMedico = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM Medico INNER JOIN Especialidad ON Especialidad.id_especialidad=Medico.id_especialidad;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Medico objMedico = new Medico();
                Especialidad objEspecialidad = new Especialidad();
                objMedico.setId_medico(objResult.getInt("Medico.id_medico"));
                objMedico.setNombre(objResult.getString("Medico.nombre"));
                objMedico.setApellidos(objResult.getString("Medico.apellidos"));
                objEspecialidad.setId_especialidad(objResult.getInt("Especialidad.id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("Especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("Especialidad.descripcion"));
                objMedico.setEspecialidad(objEspecialidad);
                listMedico.add(objMedico);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listMedico;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = (Medico) obj;
        boolean isUpdate = false;
        try {
            String sql = "UPDATE Medico SET nombre=?,apellidos=?,id_especialidad=? WHERE id_medico = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, objMedico.getNombre());
            objPrepared.setString(2, objMedico.getApellidos());
            objPrepared.setInt(3, objMedico.getEspecialidad().getId_especialidad());
            objPrepared.setInt(4, objMedico.getId_medico());
            int totalAffectedRows = objPrepared.executeUpdate();
            if (totalAffectedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Update was succesfull");
                JOptionPane.showMessageDialog(null, objMedico.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Medico objMedico = (Medico) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM Medico WHERE id_medico=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objMedico.getId_medico());
            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows>0){
                isDeleted= true;
                JOptionPane.showMessageDialog(null,"Delete was succesfull");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }
    public Medico findById(int id) {
        Connection objConnection = ConfigDB.openConnection();

        Medico objMedico = null;

        try {
            String sql = "SELECT * FROM Medico INNER JOIN Especialidad ON Especialidad.id_especialidad=Medico.id_especialidad WHERE id_medico=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objMedico = new Medico();
                Especialidad objEspecialidad = new Especialidad();
                objMedico.setId_medico(objResult.getInt("Medico.id_medico"));
                objMedico.setNombre(objResult.getString("Medico.nombre"));
                objMedico.setApellidos(objResult.getString("Medico.apellidos"));
                objEspecialidad.setId_especialidad(objResult.getInt("Especialidad.id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("Especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("Especialidad.descripcion"));
                objMedico.setEspecialidad(objEspecialidad);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objMedico;
    }
}
