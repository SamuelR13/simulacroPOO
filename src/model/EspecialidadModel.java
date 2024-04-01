package model;

import database.CRUD;
import database.ConfigDB;
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
            String sql="INSERT INTO Especialidad VALUES (?,?);";
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
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
