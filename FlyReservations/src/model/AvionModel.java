package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AvionModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = (Avion) obj;

        try {
            String sql="INSERT INTO Avion (modelo,capacidad) VALUES (?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1,objAvion.getModelo());
            objPrepare.setInt(2,objAvion.getCapacidad());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objAvion.setId_avion(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Insert was succesful");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        };
        ConfigDB.closeConnection();
        return objAvion;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listEspecialidad = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM Avion;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Avion objAvion = new Avion();
                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));
                listEspecialidad.add(objAvion);
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
        Avion objAvion = (Avion) obj;
        boolean isUpdate = false;
        try {
            String sql = "UPDATE Avion SET modelo=?, capacidad=? WHERE id_avion = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, objAvion.getModelo());
            objPrepared.setInt(2, objAvion.getCapacidad());
            objPrepared.setInt(3,objAvion.getId_avion());
            int totalAffectedRows = objPrepared.executeUpdate();
            if (totalAffectedRows >0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null,"Update was succesfull");
                JOptionPane.showMessageDialog(null,objAvion.toString());
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Avion objAvion = (Avion) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM Avion WHERE id_avion=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objAvion.getId_avion());
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

    public ArrayList<Avion> findByModelo(String name) {
        ArrayList<Avion> listAvion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = null;
        try {
            String sql = "SELECT * FROM Avion WHERE modelo LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objAvion = new Avion();
                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));
                listAvion.add(objAvion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listAvion;
    }
    public ArrayList<Avion> findByCapacidad(int capacidad) {
        ArrayList<Avion> listAvion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = null;
        try {
            String sql = "SELECT * FROM Avion WHERE capacidad = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + capacidad + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objAvion = new Avion();
                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));
                listAvion.add(objAvion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listAvion;
    }

    public Avion findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = null;

        try {
            String sql = "SELECT * FROM Avion WHERE id_avion = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objAvion = new Avion();
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objAvion;
    }
}
