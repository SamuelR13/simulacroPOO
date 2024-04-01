package model;

import database.CRUD;
import database.ConfigDB;
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
            String sql="INSERT INTO Paciente VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1,objPaciente.getNombre());
            objPrepare.setString(2,objPaciente.getApellidos());
            objPrepare.setString(3,objPaciente.getFecha_nacimiento());
            objPrepare.setString(4,objPaciente.getDocumento_identidad());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objPaciente.setId_paciente(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Insert was succesful");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        };
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
            while (objResult.next()){
                Paciente objPaciente = new Paciente();
                objPaciente.setId_paciente(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));
                listPaciente.add(objPaciente);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listPaciente;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
