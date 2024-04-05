package model;

import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;
import entity.Especialidad;

import javax.print.attribute.standard.JobPriority;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspe = (Especialidad) obj;

        try {
            String sql="INSERT INTO Especialidad (nombre,descripcion) VALUES (?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1,objEspe.getNombre());
            objPrepare.setString(2,objEspe.getDescripcion());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objEspe.setId_especialidad(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Insert was succesful");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        };
        ConfigDB.closeConnection();
        return objEspe;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listEspecialidad = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM Especialidad;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Especialidad objEspecialidad = new Especialidad();
                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
                listEspecialidad.add(objEspecialidad);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listEspecialidad;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspe = (Especialidad) obj;
        boolean isUpdate = false;
        try {
            String sql = "UPDATE Especialidad SET nombre=?, descripcion=? WHERE id_especialidad = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, objEspe.getNombre());
            objPrepared.setString(2, objEspe.getDescripcion());
            objPrepared.setInt(3,objEspe.getId_especialidad());
            int totalAffectedRows = objPrepared.executeUpdate();
            if (totalAffectedRows >0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null,"Update was succesfull");
                JOptionPane.showMessageDialog(null,objEspe.toString());
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Especialidad objEspe = (Especialidad) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM Especialidad WHERE id_especialidad=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objEspe.getId_especialidad());
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

    public ArrayList<Especialidad> findByName(String name) {
        ArrayList<Especialidad> listEspecialidad = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspecialidad = null;
        try {
            String sql = "SELECT * FROM Especialidad WHERE nombre LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objEspecialidad = new Especialidad();
                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
                listEspecialidad.add(objEspecialidad);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listEspecialidad;
    }

    public Especialidad findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspecialidad = null;

        try {
            String sql = "SELECT * FROM Especialidad WHERE id_especialidad = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objEspecialidad = new Especialidad();
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objEspecialidad;
    }
}
