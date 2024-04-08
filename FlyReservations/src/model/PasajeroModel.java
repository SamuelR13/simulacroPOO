package model;

import database.CRUD;
import database.ConfigDB;
import entity.Pasajero;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = (Pasajero) obj;
        try {
            String sql = "INSERT INTO Pasajero (nombre,apellido,documento_identidad) VALUES (?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objPasajero.getNombre());
            objPrepare.setString(2, objPasajero.getApellidos());
            objPrepare.setString(3, objPasajero.getDocumento_identidad());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()) {
                objPasajero.setId_pasajero(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Insert was succesful");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ;
        ConfigDB.closeConnection();
        return objPasajero;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listPaciente = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM Pasajero;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                Pasajero objPasajero = new Pasajero();
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellidos(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
                listPaciente.add(objPasajero);
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
        Pasajero objPasajero = (Pasajero) obj;
        boolean isUpdate = false;
        try {
            String sql = "UPDATE Pasajero SET nombre=?,apellido=?,=?,documento_identidad=? WHERE id_pasajero = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, objPasajero.getNombre());
            objPrepared.setString(2, objPasajero.getApellidos());
            objPrepared.setString(3, objPasajero.getDocumento_identidad());
            objPrepared.setInt(4, objPasajero.getId_pasajero());
            int totalAffectedRows = objPrepared.executeUpdate();
            if (totalAffectedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Update was succesfull");
                JOptionPane.showMessageDialog(null, objPasajero.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Pasajero objPasajero = (Pasajero) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM Pasajero WHERE id_pasajero=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objPasajero.getId_pasajero());
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

    public ArrayList<Pasajero> findByName(String name) {
        ArrayList<Pasajero> listPasajero = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = null;
        try {
            String sql = "SELECT * FROM Pasajero WHERE nombre LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objPasajero = new Pasajero();
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellidos(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
                listPasajero.add(objPasajero);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listPasajero;
    }
    public ArrayList<Pasajero> findByLastname(String name) {
        ArrayList<Pasajero> listPasajero = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = null;
        try {
            String sql = "SELECT * FROM Pasajero WHERE apellido LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objPasajero = new Pasajero();
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellidos(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
                listPasajero.add(objPasajero);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listPasajero;
    }
    public Pasajero findByDocument(int id) {
        Connection objConnection = ConfigDB.openConnection();

        Pasajero objPasajero = null;

        try {
            String sql = "SELECT * FROM Pasajero WHERE documento_identidad = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objPasajero = new Pasajero();
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellidos(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objPasajero;
    }
    public Pasajero findById(int id) {
        Connection objConnection = ConfigDB.openConnection();

        Pasajero objPasajero = null;

        try {
            String sql = "SELECT * FROM Pasajero WHERE id_pasajero = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objPasajero = new Pasajero();
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellidos(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objPasajero;
    }
}


