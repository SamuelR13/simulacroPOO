package model;

import database.CRUD;
import database.ConfigDB;
import entity.*;
import entity.Vuelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VueloModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = (Vuelo) obj;

        try {
            String sql="INSERT INTO Vuelo (destino,fecha_salida,hora_salida,avion) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objVuelo.getDestino());
            objPrepare.setString(2, objVuelo.getFecha_salida());
            objPrepare.setString(3, objVuelo.getHora_salida());
            objPrepare.setInt(4,objVuelo.getAvion().getId_avion());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objVuelo.setId_vuelo(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Insert was succesful");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        };
        ConfigDB.closeConnection();
        return objVuelo;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listMedico = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM Vuelo INNER JOIN Avion ON Avion.id_avion=Vuelo.avion;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Vuelo objVuelo = new Vuelo();
                Avion objAvion = new Avion();
                objVuelo.setId_vuelo(objResult.getInt("Vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("Vuelo.destino"));
                objVuelo.setDestino(objResult.getString("Vuelo.fecha_salida"));
                objVuelo.setDestino(objResult.getString("Vuelo.hora_salida"));

                objAvion.setId_avion(objResult.getInt("Avion.id_avion"));
                objAvion.setModelo(objResult.getString("Avion.destino"));
                objAvion.setCapacidad(objResult.getInt("Avion.capacidad"));
                objVuelo.setAvion(objAvion);
                listMedico.add(objVuelo);
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
        Vuelo objVuelo = (Vuelo) obj;
        boolean isUpdate = false;
        try {
            String sql = "UPDATE Vuelo SET destino=?,fecha_salida=?,hora_salida=?,avion=? WHERE Vuelo.id_vuelo = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, objVuelo.getDestino());
            objPrepared.setString(2, objVuelo.getFecha_salida());
            objPrepared.setString(3, objVuelo.getHora_salida());
            objPrepared.setInt(4,objVuelo.getAvion().getId_avion());
            objPrepared.setInt(5,objVuelo.getId_vuelo());
            int totalAffectedRows = objPrepared.executeUpdate();
            if (totalAffectedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Update was succesfull");
                JOptionPane.showMessageDialog(null, objVuelo.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Vuelo objVuelo = (Vuelo) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM Vuelo WHERE id_vuelo=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objVuelo.getId_vuelo());
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
    public Vuelo findById(int id) {
        Connection objConnection = ConfigDB.openConnection();

        Vuelo objVuelo = null;

        try {
            String sql = "SELECT * FROM Vuelo INNER JOIN Avion ON Avion.id_avion=Vuelo.avion WHERE Vuelo.id_vuelo=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objVuelo = new Vuelo();
                Avion objAvion = new Avion();
                objVuelo.setId_vuelo(objResult.getInt("Vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("Vuelo.destino"));
                objAvion.setId_avion(objResult.getInt("Avion.id_avion"));
                objAvion.setModelo(objResult.getString("Avion.destino"));
                objAvion.setCapacidad(objResult.getInt("Avion.capacidad"));
                objVuelo.setAvion(objAvion);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objVuelo;
    }

    public ArrayList<Vuelo> findByDestino(String name) {
        ArrayList<Vuelo> listVuelo = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = null;
        try {
            String sql = "SELECT * FROM Vuelo INNER JOIN Avion ON Avion.id_avion=Vuelo.avion WHERE Vuelo.destino LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objVuelo = new Vuelo();
                Avion objAvion = new Avion();
                objVuelo.setId_vuelo(objResult.getInt("Vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("Vuelo.destino"));
                objAvion.setId_avion(objResult.getInt("Avion.id_avion"));
                objAvion.setModelo(objResult.getString("Avion.destino"));
                objAvion.setCapacidad(objResult.getInt("Avion.capacidad"));
                objVuelo.setAvion(objAvion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVuelo;
    }
    public ArrayList<Vuelo> findByFecha(String name) {
        ArrayList<Vuelo> listVuelo = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = null;
        try {
            String sql = "SELECT * FROM Vuelo INNER JOIN Avion ON Avion.id_avion=Vuelo.avion WHERE Vuelo.fecha_salida LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objVuelo = new Vuelo();
                Avion objAvion = new Avion();
                objVuelo.setId_vuelo(objResult.getInt("Vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("Vuelo.destino"));
                objAvion.setId_avion(objResult.getInt("Avion.id_avion"));
                objAvion.setModelo(objResult.getString("Avion.destino"));
                objAvion.setCapacidad(objResult.getInt("Avion.capacidad"));
                objVuelo.setAvion(objAvion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVuelo;
    }

    public ArrayList<Vuelo> findByHora(String avion) {
        ArrayList<Vuelo> listVuelo = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = null;
        try {
            String sql = "SELECT * FROM Vuelo INNER JOIN Avion ON Avion.id_avion=Vuelo.avion WHERE Vuelo.hora_salida LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + avion + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objVuelo = new Vuelo();
                Avion objAvion = new Avion();
                objVuelo.setId_vuelo(objResult.getInt("Vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("Vuelo.destino"));
                objAvion.setId_avion(objResult.getInt("Avion.id_avion"));
                objAvion.setModelo(objResult.getString("Avion.destino"));
                objAvion.setCapacidad(objResult.getInt("Avion.capacidad"));
                objVuelo.setAvion(objAvion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVuelo;
    }

    public ArrayList<Vuelo> findByAvion(String avion) {
        ArrayList<Vuelo> listVuelo = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = null;
        try {
            String sql = "SELECT * FROM Vuelo INNER JOIN Avion ON Avion.id_avion=Vuelo.avion WHERE Vuelo.avion LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + avion + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objVuelo = new Vuelo();
                Avion objAvion = new Avion();
                objVuelo.setId_vuelo(objResult.getInt("Vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("Vuelo.destino"));
                objAvion.setId_avion(objResult.getInt("Avion.id_avion"));
                objAvion.setModelo(objResult.getString("Avion.destino"));
                objAvion.setCapacidad(objResult.getInt("Avion.capacidad"));
                objVuelo.setAvion(objAvion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVuelo;
    }

}
