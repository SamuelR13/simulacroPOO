package model;

import database.CRUD;
import database.ConfigDB;
import entity.*;
import entity.Reservacion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservacionModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Reservacion objReservacion = (Reservacion) obj;

        try {
            String sql="INSERT INTO Reservacion (id_pasajero,id_vuelo,fecha_reservacion,asiento) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,objReservacion.getPasajero().getId_pasajero());
            objPrepare.setInt(2,objReservacion.getVuelo().getId_vuelo());
            objPrepare.setString(3,objReservacion.getFecha_reservacion());
            objPrepare.setString(4,objReservacion.getAsiento());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objReservacion.setId_reservacion(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Insert was succesful");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        };
        ConfigDB.closeConnection();
        return objReservacion;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listReservacion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM Reservacion INNER JOIN Pasajero ON Pasajero.id_pasajero=Reservacion.id_pasajero INNER JOIN Vuelo ON Vuelo.id_vuelo = Reservacion.id_vuelo INNER JOIN Avion ON Avion.id_avion=Vuelo.id_avion;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Reservacion objReservacion = new Reservacion();
                Vuelo objVuelo = new Vuelo();
                Pasajero objPasajero = new Pasajero();
                Avion objAvion = new Avion();

                objReservacion.setId_reservacion(objResult.getInt("Reservacion.id_reservacion"));
                objReservacion.setFecha_reservacion(objResult.getString("Reservacion.fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("Reservacion.asiento"));


                objVuelo.setId_vuelo(objResult.getInt("Vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("Vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getString("Vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("Vuelo.fecha_salida"));

                objAvion.setId_avion(objResult.getInt("Avion.id_avion"));
                objAvion.setModelo(objResult.getString("Avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("Avion.capacidad"));
                objVuelo.setAvion(objAvion);

                objPasajero.setId_pasajero(objResult.getInt("Pasajero.id_pasajero"));
                objPasajero.setNombre(objResult.getString("Pasajero.nombre"));
                objPasajero.setApellidos(objResult.getString("Pasajero.apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("Pasajero.documento_identidad"));



                objReservacion.setPasajero(objPasajero);
                objReservacion.setVuelo(objVuelo);
                listReservacion.add(objReservacion);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listReservacion;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Reservacion objReservacion = (Reservacion) obj;
        boolean isUpdate = false;
        try {
            String sql = "UPDATE Reservacion SET pasajero=?,vuelo=?,fecha_reservacion=?,asiento=? WHERE Reservacion.id_reservacion = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1, objReservacion.getPasajero().getId_pasajero());
            objPrepared.setInt(2, objReservacion.getVuelo().getId_vuelo());
            objPrepared.setString(3, objReservacion.getFecha_reservacion());
            objPrepared.setString(4, objReservacion.getAsiento());
            objPrepared.setInt(5, objReservacion.getId_reservacion());

            int totalAffectedRows = objPrepared.executeUpdate();
            if (totalAffectedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Update was succesfull");
                JOptionPane.showMessageDialog(null, objReservacion.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Reservacion objReservacion = (Reservacion) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM Reservacion WHERE id_reservacion=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objReservacion.getId_reservacion());
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
    public Reservacion findById(int id) {
        Connection objConnection = ConfigDB.openConnection();

        Reservacion objReservacion = null;

        try {
            String sql = "SELECT * FROM Reservacion INNER JOIN Pasajero ON Pasajero.id_pasajero=Reservacion.id_pasajero " +
                    "INNER JOIN Vuelo ON Vuelo.id_vuelo = Reservacion.id_vuelo " +
                    "INNER JOIN Avion ON Avion.id_avion=Vuelo.id_avion" +
                    " WHERE Reservacion.id_reservacion=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objReservacion = new Reservacion();
                Vuelo objVuelo = new Vuelo();
                Pasajero objPasajero = new Pasajero();
                Avion objAvion = new Avion();

                objReservacion.setId_reservacion(objResult.getInt("Reservacion.id_reservacion"));
                objReservacion.setFecha_reservacion(objResult.getString("Reservacion.fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("Reservacion.asiento"));


                objVuelo.setId_vuelo(objResult.getInt("Vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("Vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getString("Vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("Vuelo.fecha_salida"));

                objAvion.setId_avion(objResult.getInt("Avion.id_avion"));
                objAvion.setModelo(objResult.getString("Avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("Avion.capacidad"));
                objVuelo.setAvion(objAvion);

                objPasajero.setId_pasajero(objResult.getInt("Pasajero.id_pasajero"));
                objPasajero.setNombre(objResult.getString("Pasajero.nombre"));
                objPasajero.setApellidos(objResult.getString("Pasajero.apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("Pasajero.documento_identidad"));



                objReservacion.setPasajero(objPasajero);
                objReservacion.setVuelo(objVuelo);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objReservacion;
    }
    public Reservacion findByPasajeroDocument(String document) {
        Connection objConnection = ConfigDB.openConnection();

        Reservacion objReservacion = null;

        try {
            String sql = "SELECT * FROM Reservacion INNER JOIN Pasajero ON Pasajero.id_pasajero=Reservacion.id_pasajero " +
                    "INNER JOIN Vuelo ON Vuelo.id_vuelo = Reservacion.id_vuelo " +
                    "INNER JOIN Avion ON Avion.id_avion=Vuelo.id_avion" +
                    " WHERE Pasajero.documento_identidad=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, document);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objReservacion = new Reservacion();
                Vuelo objVuelo = new Vuelo();
                Pasajero objPasajero = new Pasajero();
                Avion objAvion = new Avion();

                objReservacion.setId_reservacion(objResult.getInt("Reservacion.id_reservacion"));
                objReservacion.setFecha_reservacion(objResult.getString("Reservacion.fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("Reservacion.asiento"));


                objVuelo.setId_vuelo(objResult.getInt("Vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("Vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getString("Vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("Vuelo.fecha_salida"));

                objAvion.setId_avion(objResult.getInt("Avion.id_avion"));
                objAvion.setModelo(objResult.getString("Avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("Avion.capacidad"));
                objVuelo.setAvion(objAvion);

                objPasajero.setId_pasajero(objResult.getInt("Pasajero.id_pasajero"));
                objPasajero.setNombre(objResult.getString("Pasajero.nombre"));
                objPasajero.setApellidos(objResult.getString("Pasajero.apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("Pasajero.documento_identidad"));



                objReservacion.setPasajero(objPasajero);
                objReservacion.setVuelo(objVuelo);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objReservacion;
    }
    public Reservacion findByVueloId(int id) {
        Connection objConnection = ConfigDB.openConnection();

        Reservacion objReservacion = null;

        try {
            String sql = "SELECT * FROM Reservacion INNER JOIN Pasajero ON Pasajero.id_pasajero=Reservacion.id_pasajero " +
                    "INNER JOIN Vuelo ON Vuelo.id_vuelo = Reservacion.id_vuelo " +
                    "INNER JOIN Avion ON Avion.id_avion=Vuelo.id_avion" +
                    " WHERE Vuelo.id_vuelo=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objReservacion = new Reservacion();
                Vuelo objVuelo = new Vuelo();
                Pasajero objPasajero = new Pasajero();
                Avion objAvion = new Avion();

                objReservacion.setId_reservacion(objResult.getInt("Reservacion.id_reservacion"));
                objReservacion.setFecha_reservacion(objResult.getString("Reservacion.fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("Reservacion.asiento"));


                objVuelo.setId_vuelo(objResult.getInt("Vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("Vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getString("Vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("Vuelo.fecha_salida"));

                objAvion.setId_avion(objResult.getInt("Avion.id_avion"));
                objAvion.setModelo(objResult.getString("Avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("Avion.capacidad"));
                objVuelo.setAvion(objAvion);

                objPasajero.setId_pasajero(objResult.getInt("Pasajero.id_pasajero"));
                objPasajero.setNombre(objResult.getString("Pasajero.nombre"));
                objPasajero.setApellidos(objResult.getString("Pasajero.apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("Pasajero.documento_identidad"));



                objReservacion.setPasajero(objPasajero);
                objReservacion.setVuelo(objVuelo);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objReservacion;
    }
    public ArrayList<Reservacion> findByPasajeroName(String name) {
        ArrayList<Reservacion> listReservacion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Reservacion objReservacion = null;
        try {
            String sql = "SELECT * FROM Reservacion INNER JOIN Pasajero ON Pasajero.id_pasajero=Reservacion.id_pasajero " +
                    "INNER JOIN Vuelo ON Vuelo.id_vuelo = Reservacion.id_vuelo " +
                    "INNER JOIN Avion ON Avion.id_avion=Vuelo.id_avion" +
                    "WHERE Pasajero.nombre LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objReservacion = new Reservacion();
                Vuelo objVuelo = new Vuelo();
                Pasajero objPasajero = new Pasajero();
                Avion objAvion = new Avion();

                objReservacion.setId_reservacion(objResult.getInt("Reservacion.id_reservacion"));
                objReservacion.setFecha_reservacion(objResult.getString("Reservacion.fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("Reservacion.asiento"));


                objVuelo.setId_vuelo(objResult.getInt("Vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("Vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getString("Vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("Vuelo.fecha_salida"));

                objAvion.setId_avion(objResult.getInt("Avion.id_avion"));
                objAvion.setModelo(objResult.getString("Avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("Avion.capacidad"));
                objVuelo.setAvion(objAvion);

                objPasajero.setId_pasajero(objResult.getInt("Pasajero.id_pasajero"));
                objPasajero.setNombre(objResult.getString("Pasajero.nombre"));
                objPasajero.setApellidos(objResult.getString("Pasajero.apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("Pasajero.documento_identidad"));



                objReservacion.setPasajero(objPasajero);
                objReservacion.setVuelo(objVuelo);
                listReservacion.add(objReservacion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listReservacion;
    }
    public ArrayList<Reservacion> findByPasajeroLastname(String name) {
        ArrayList<Reservacion> listReservacion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Reservacion objReservacion = null;
        try {
            String sql = "SELECT * FROM Reservacion INNER JOIN Vuelo ON Vuelo.id_avion=Reservacion.id_avion WHERE Pasajero.apellidos LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objReservacion = new Reservacion();
                Vuelo objVuelo = new Vuelo();
                Pasajero objPasajero = new Pasajero();
                Avion objAvion = new Avion();

                objReservacion.setId_reservacion(objResult.getInt("Reservacion.id_reservacion"));
                objReservacion.setFecha_reservacion(objResult.getString("Reservacion.fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("Reservacion.asiento"));


                objVuelo.setId_vuelo(objResult.getInt("Vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("Vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getString("Vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("Vuelo.fecha_salida"));

                objAvion.setId_avion(objResult.getInt("Avion.id_avion"));
                objAvion.setModelo(objResult.getString("Avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("Avion.capacidad"));
                objVuelo.setAvion(objAvion);

                objPasajero.setId_pasajero(objResult.getInt("Pasajero.id_pasajero"));
                objPasajero.setNombre(objResult.getString("Pasajero.nombre"));
                objPasajero.setApellidos(objResult.getString("Pasajero.apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("Pasajero.documento_identidad"));



                objReservacion.setPasajero(objPasajero);
                objReservacion.setVuelo(objVuelo);
                listReservacion.add(objReservacion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listReservacion;
    }
    public ArrayList<Reservacion> findByVueloName(String name) {
        ArrayList<Reservacion> listReservacion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Reservacion objReservacion = null;
        try {
            String sql = "SELECT * FROM Reservacion INNER JOIN Pasajero ON Pasajero.id_pasajero=Reservacion.id_pasajero " +
                    "INNER JOIN Vuelo ON Vuelo.id_vuelo = Reservacion.id_vuelo " +
                    "INNER JOIN Avion ON Avion.id_avion=Vuelo.id_avion" +
                    "WHERE Vuelo.nombre LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objReservacion = new Reservacion();
                Vuelo objVuelo = new Vuelo();
                Pasajero objPasajero = new Pasajero();
                Avion objAvion = new Avion();

                objReservacion.setId_reservacion(objResult.getInt("Reservacion.id_reservacion"));
                objReservacion.setFecha_reservacion(objResult.getString("Reservacion.fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("Reservacion.asiento"));


                objVuelo.setId_vuelo(objResult.getInt("Vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("Vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getString("Vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("Vuelo.fecha_salida"));

                objAvion.setId_avion(objResult.getInt("Avion.id_avion"));
                objAvion.setModelo(objResult.getString("Avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("Avion.capacidad"));
                objVuelo.setAvion(objAvion);

                objPasajero.setId_pasajero(objResult.getInt("Pasajero.id_pasajero"));
                objPasajero.setNombre(objResult.getString("Pasajero.nombre"));
                objPasajero.setApellidos(objResult.getString("Pasajero.apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("Pasajero.documento_identidad"));



                objReservacion.setPasajero(objPasajero);
                objReservacion.setVuelo(objVuelo);
                listReservacion.add(objReservacion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listReservacion;
    }
    public ArrayList<Reservacion> findByVueloLastname(String name) {
        ArrayList<Reservacion> listReservacion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Reservacion objReservacion = null;
        try {
            String sql = "SELECT * FROM Reservacion INNER JOIN Vuelo ON Vuelo.id_avion=Reservacion.id_avion WHERE Vuelo.apellidos LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objReservacion = new Reservacion();
                Vuelo objVuelo = new Vuelo();
                Pasajero objPasajero = new Pasajero();
                Avion objAvion = new Avion();

                objReservacion.setId_reservacion(objResult.getInt("Reservacion.id_reservacion"));
                objReservacion.setFecha_reservacion(objResult.getString("Reservacion.fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("Reservacion.asiento"));
                ;

                objVuelo.setId_vuelo(objResult.getInt("Vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("Vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getString("Vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("Vuelo.fecha_salida"));

                objAvion.setId_avion(objResult.getInt("Avion.id_avion"));
                objAvion.setModelo(objResult.getString("Avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("Avion.capacidad"));
                objVuelo.setAvion(objAvion);

                objPasajero.setId_pasajero(objResult.getInt("Pasajero.id_pasajero"));
                objPasajero.setNombre(objResult.getString("Pasajero.nombre"));
                objPasajero.setApellidos(objResult.getString("Pasajero.apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("Pasajero.documento_identidad"));



                objReservacion.setPasajero(objPasajero);
                objReservacion.setVuelo(objVuelo);
                listReservacion.add(objReservacion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listReservacion;
    }

}