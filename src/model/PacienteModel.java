package model;

import database.CRUD;
import database.ConfigDB;
import entity.*;
import entity.Paciente;
import entity.Paciente;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PacienteModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = (Paciente) obj;
        try {
            String sql = "INSERT INTO Paciente VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objPaciente.getNombre());
            objPrepare.setString(2, objPaciente.getApellidos());
            objPrepare.setString(3, objPaciente.getFecha_nacimiento());
            objPrepare.setString(4, objPaciente.getDocumento_identidad());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()) {
                objPaciente.setId_paciente(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Insert was succesful");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ;
        ConfigDB.closeConnection();
        return objPaciente;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listPaciente = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM Paciente;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                Paciente objPaciente = new Paciente();
                objPaciente.setId_paciente(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));
                listPaciente.add(objPaciente);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listPaciente;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = new Paciente();
        boolean isUpdate = false;
        try {
            String sql = "UPDATE Paciente SET nombre=?,apellidos=?,fecha_nacimiento=?,documento_identidad=?, WHERE id_paciente = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, objPaciente.getNombre());
            objPrepared.setString(2, objPaciente.getApellidos());
            objPrepared.setString(3, objPaciente.getFecha_nacimiento());
            objPrepared.setString(4, objPaciente.getDocumento_identidad());
            objPrepared.setInt(5, objPaciente.getId_paciente());
            int totalAffectedRows = objPrepared.executeUpdate();
            if (totalAffectedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Update was succesfull");
                JOptionPane.showMessageDialog(null, objPaciente.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Paciente objPaciente = (Paciente) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM Paciente WHERE id_paciente=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objPaciente.getId_paciente());
            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Delete was succesfull");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public ArrayList<Paciente> findByName(String name) {
        ArrayList<Paciente> listPaciente = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = null;
        try {
            String sql = "SELECT * FROM Paciente WHERE nombre LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objPaciente = new Paciente();
                objPaciente.setId_paciente(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));
                listPaciente.add(objPaciente);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listPaciente;
    }
    public Paciente findById(int id) {
        Connection objConnection = ConfigDB.openConnection();

        Paciente objPaciente = null;

        try {
            String sql = "SELECT * FROM Paciente WHERE id_paciente = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objPaciente = new Paciente();
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objPaciente;
    }
}


