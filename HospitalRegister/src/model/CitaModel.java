package model;

import database.CRUD;
import database.ConfigDB;
import entity.*;
import entity.Cita;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CitaModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) obj;

        try {
            String sql="INSERT INTO Cita (id_paciente,id_medico,fecha_cita,hora_cita,motivo) VALUES (?,?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,objCita.getPaciente().getId_paciente());
            objPrepare.setInt(2,objCita.getMedico().getId_medico());
            objPrepare.setString(3,objCita.getFecha_cita());
            objPrepare.setString(4,objCita.getHora_cita());
            objPrepare.setString(5,objCita.getMotivo());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objCita.setId_cita(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Insert was succesful");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        };
        ConfigDB.closeConnection();
        return objCita;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listCita = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM Cita INNER JOIN Paciente ON Paciente.id_paciente=Cita.id_paciente INNER JOIN Medico ON Medico.id_medico = Cita.id_medico INNER JOIN Especialidad ON Especialidad.id_especialidad=Medico.id_especialidad;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Cita objCita = new Cita();
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();
                Especialidad objEspecialidad = new Especialidad();

                objCita.setId_cita(objResult.getInt("Cita.id_cita"));
                objCita.setFecha_cita(objResult.getString("Cita.fecha_cita"));
                objCita.setHora_cita(objResult.getString("Cita.hora_cita"));

                objMedico.setId_medico(objResult.getInt("Medico.id_medico"));
                objMedico.setNombre(objResult.getString("Medico.nombre"));
                objMedico.setApellidos(objResult.getString("Medico.apellidos"));
                objEspecialidad.setId_especialidad(objResult.getInt("Especialidad.id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("Especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("Especialidad.descripcion"));
                objMedico.setEspecialidad(objEspecialidad);

                objPaciente.setId_paciente(objResult.getInt("Paciente.id_paciente"));
                objPaciente.setNombre(objResult.getString("Paciente.nombre"));
                objPaciente.setApellidos(objResult.getString("Paciente.apellidos"));
                objPaciente.setDocumento_identidad(objResult.getString("Paciente.documento_identidad"));
                objPaciente.setFecha_nacimiento(objResult.getString("Paciente.fecha_nacimiento"));


                objCita.setPaciente(objPaciente);
                objCita.setMedico(objMedico);
                listCita.add(objCita);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCita;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) obj;
        boolean isUpdate = false;
        try {
            String sql = "UPDATE Cita SET paciente=?,medico=?,fecha_cita=?,hora_cita=?,motivo=?, WHERE Cita.id_cita = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1, objCita.getPaciente().getId_paciente());
            objPrepared.setInt(2, objCita.getMedico().getId_medico());
            objPrepared.setString(3, objCita.getFecha_cita());
            objPrepared.setString(4, objCita.getHora_cita());
            objPrepared.setString(5, objCita.getMotivo());
            objPrepared.setInt(6, objCita.getId_cita());

            int totalAffectedRows = objPrepared.executeUpdate();
            if (totalAffectedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Update was succesfull");
                JOptionPane.showMessageDialog(null, objCita.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Cita objCita = (Cita) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM Cita WHERE id_cita=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objCita.getId_cita());
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
    public Cita findById(int id) {
        Connection objConnection = ConfigDB.openConnection();

        Cita objCita = null;

        try {
            String sql = "SELECT * FROM Cita INNER JOIN Paciente ON Paciente.id_paciente=Cita.id_paciente " +
                    "INNER JOIN Medico ON Medico.id_medico = Cita.id_medico " +
                    "INNER JOIN Especialidad ON Especialidad.id_especialidad=Medico.id_especialidad" +
                    " WHERE Cita.id_cita=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objCita = new Cita();
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();
                Especialidad objEspecialidad = new Especialidad();

                objCita.setId_cita(objResult.getInt("Cita.id_cita"));
                objCita.setFecha_cita(objResult.getString("Cita.fecha_cita"));
                objCita.setHora_cita(objResult.getString("Cita.hora_cita"));

                objMedico.setId_medico(objResult.getInt("Medico.id_medico"));
                objMedico.setNombre(objResult.getString("Medico.nombre"));
                objMedico.setApellidos(objResult.getString("Medico.apellidos"));
                objEspecialidad.setId_especialidad(objResult.getInt("Especialidad.id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("Especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("Especialidad.descripcion"));
                objMedico.setEspecialidad(objEspecialidad);

                objPaciente.setId_paciente(objResult.getInt("Paciente.id_paciente"));
                objPaciente.setNombre(objResult.getString("Paciente.nombre"));
                objPaciente.setApellidos(objResult.getString("Paciente.apellidos"));
                objPaciente.setDocumento_identidad(objResult.getString("Paciente.documento_identidad"));
                objPaciente.setFecha_nacimiento(objResult.getString("Paciente.fecha_nacimiento"));


                objCita.setPaciente(objPaciente);
                objCita.setMedico(objMedico);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objCita;
    }
    public Cita findByPacienteDocument(String document) {
        Connection objConnection = ConfigDB.openConnection();

        Cita objCita = null;

        try {
            String sql = "SELECT * FROM Cita INNER JOIN Paciente ON Paciente.id_paciente=Cita.id_paciente " +
                    "INNER JOIN Medico ON Medico.id_medico = Cita.id_medico " +
                    "INNER JOIN Especialidad ON Especialidad.id_especialidad=Medico.id_especialidad" +
                    " WHERE Paciente.documento_identidad=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, document);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objCita = new Cita();
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();
                Especialidad objEspecialidad = new Especialidad();

                objCita.setId_cita(objResult.getInt("Cita.id_cita"));
                objCita.setFecha_cita(objResult.getString("Cita.fecha_cita"));
                objCita.setHora_cita(objResult.getString("Cita.hora_cita"));

                objMedico.setId_medico(objResult.getInt("Medico.id_medico"));
                objMedico.setNombre(objResult.getString("Medico.nombre"));
                objMedico.setApellidos(objResult.getString("Medico.apellidos"));
                objEspecialidad.setId_especialidad(objResult.getInt("Especialidad.id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("Especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("Especialidad.descripcion"));
                objMedico.setEspecialidad(objEspecialidad);

                objPaciente.setId_paciente(objResult.getInt("Paciente.id_paciente"));
                objPaciente.setNombre(objResult.getString("Paciente.nombre"));
                objPaciente.setApellidos(objResult.getString("Paciente.apellidos"));
                objPaciente.setDocumento_identidad(objResult.getString("Paciente.documento_identidad"));
                objPaciente.setFecha_nacimiento(objResult.getString("Paciente.fecha_nacimiento"));


                objCita.setPaciente(objPaciente);
                objCita.setMedico(objMedico);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objCita;
    }
    public Cita findByMedicoId(int id) {
        Connection objConnection = ConfigDB.openConnection();

        Cita objCita = null;

        try {
            String sql = "SELECT * FROM Cita INNER JOIN Paciente ON Paciente.id_paciente=Cita.id_paciente " +
                    "INNER JOIN Medico ON Medico.id_medico = Cita.id_medico " +
                    "INNER JOIN Especialidad ON Especialidad.id_especialidad=Medico.id_especialidad" +
                    " WHERE Medico.id_medico=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objCita = new Cita();
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();
                Especialidad objEspecialidad = new Especialidad();

                objCita.setId_cita(objResult.getInt("Cita.id_cita"));
                objCita.setFecha_cita(objResult.getString("Cita.fecha_cita"));
                objCita.setHora_cita(objResult.getString("Cita.hora_cita"));

                objMedico.setId_medico(objResult.getInt("Medico.id_medico"));
                objMedico.setNombre(objResult.getString("Medico.nombre"));
                objMedico.setApellidos(objResult.getString("Medico.apellidos"));
                objEspecialidad.setId_especialidad(objResult.getInt("Especialidad.id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("Especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("Especialidad.descripcion"));
                objMedico.setEspecialidad(objEspecialidad);

                objPaciente.setId_paciente(objResult.getInt("Paciente.id_paciente"));
                objPaciente.setNombre(objResult.getString("Paciente.nombre"));
                objPaciente.setApellidos(objResult.getString("Paciente.apellidos"));
                objPaciente.setDocumento_identidad(objResult.getString("Paciente.documento_identidad"));
                objPaciente.setFecha_nacimiento(objResult.getString("Paciente.fecha_nacimiento"));


                objCita.setPaciente(objPaciente);
                objCita.setMedico(objMedico);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objCita;
    }
    public ArrayList<Cita> findByPacienteName(String name) {
        ArrayList<Cita> listCita = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = null;
        try {
            String sql = "SELECT * FROM Cita INNER JOIN Paciente ON Paciente.id_paciente=Cita.id_paciente " +
                    "INNER JOIN Medico ON Medico.id_medico = Cita.id_medico " +
                    "INNER JOIN Especialidad ON Especialidad.id_especialidad=Medico.id_especialidad" +
                    "WHERE Paciente.nombre LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objCita = new Cita();
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();
                Especialidad objEspecialidad = new Especialidad();

                objCita.setId_cita(objResult.getInt("Cita.id_cita"));
                objCita.setFecha_cita(objResult.getString("Cita.fecha_cita"));
                objCita.setHora_cita(objResult.getString("Cita.hora_cita"));

                objMedico.setId_medico(objResult.getInt("Medico.id_medico"));
                objMedico.setNombre(objResult.getString("Medico.nombre"));
                objMedico.setApellidos(objResult.getString("Medico.apellidos"));
                objEspecialidad.setId_especialidad(objResult.getInt("Especialidad.id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("Especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("Especialidad.descripcion"));
                objMedico.setEspecialidad(objEspecialidad);

                objPaciente.setId_paciente(objResult.getInt("Paciente.id_paciente"));
                objPaciente.setNombre(objResult.getString("Paciente.nombre"));
                objPaciente.setApellidos(objResult.getString("Paciente.apellidos"));
                objPaciente.setDocumento_identidad(objResult.getString("Paciente.documento_identidad"));
                objPaciente.setFecha_nacimiento(objResult.getString("Paciente.fecha_nacimiento"));


                objCita.setPaciente(objPaciente);
                objCita.setMedico(objMedico);
                listCita.add(objCita);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCita;
    }
    public ArrayList<Cita> findByPacienteLastname(String name) {
        ArrayList<Cita> listCita = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = null;
        try {
            String sql = "SELECT * FROM Cita INNER JOIN Medico ON Medico.id_especialidad=Cita.id_especialidad WHERE Paciente.apellidos LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objCita = new Cita();
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();
                Especialidad objEspecialidad = new Especialidad();

                objCita.setId_cita(objResult.getInt("Cita.id_cita"));
                objCita.setFecha_cita(objResult.getString("Cita.fecha_cita"));
                objCita.setHora_cita(objResult.getString("Cita.hora_cita"));

                objMedico.setId_medico(objResult.getInt("Medico.id_medico"));
                objMedico.setNombre(objResult.getString("Medico.nombre"));
                objMedico.setApellidos(objResult.getString("Medico.apellidos"));
                objEspecialidad.setId_especialidad(objResult.getInt("Especialidad.id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("Especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("Especialidad.descripcion"));
                objMedico.setEspecialidad(objEspecialidad);

                objPaciente.setId_paciente(objResult.getInt("Paciente.id_paciente"));
                objPaciente.setNombre(objResult.getString("Paciente.nombre"));
                objPaciente.setApellidos(objResult.getString("Paciente.apellidos"));
                objPaciente.setDocumento_identidad(objResult.getString("Paciente.documento_identidad"));
                objPaciente.setFecha_nacimiento(objResult.getString("Paciente.fecha_nacimiento"));


                objCita.setPaciente(objPaciente);
                objCita.setMedico(objMedico);
                listCita.add(objCita);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCita;
    }
    public ArrayList<Cita> findByMedicoName(String name) {
        ArrayList<Cita> listCita = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = null;
        try {
            String sql = "SELECT * FROM Cita INNER JOIN Paciente ON Paciente.id_paciente=Cita.id_paciente " +
                    "INNER JOIN Medico ON Medico.id_medico = Cita.id_medico " +
                    "INNER JOIN Especialidad ON Especialidad.id_especialidad=Medico.id_especialidad" +
                    "WHERE Medico.nombre LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objCita = new Cita();
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();
                Especialidad objEspecialidad = new Especialidad();

                objCita.setId_cita(objResult.getInt("Cita.id_cita"));
                objCita.setFecha_cita(objResult.getString("Cita.fecha_cita"));
                objCita.setHora_cita(objResult.getString("Cita.hora_cita"));

                objMedico.setId_medico(objResult.getInt("Medico.id_medico"));
                objMedico.setNombre(objResult.getString("Medico.nombre"));
                objMedico.setApellidos(objResult.getString("Medico.apellidos"));
                objEspecialidad.setId_especialidad(objResult.getInt("Especialidad.id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("Especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("Especialidad.descripcion"));
                objMedico.setEspecialidad(objEspecialidad);

                objPaciente.setId_paciente(objResult.getInt("Paciente.id_paciente"));
                objPaciente.setNombre(objResult.getString("Paciente.nombre"));
                objPaciente.setApellidos(objResult.getString("Paciente.apellidos"));
                objPaciente.setDocumento_identidad(objResult.getString("Paciente.documento_identidad"));
                objPaciente.setFecha_nacimiento(objResult.getString("Paciente.fecha_nacimiento"));


                objCita.setPaciente(objPaciente);
                objCita.setMedico(objMedico);
                listCita.add(objCita);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCita;
    }
    public ArrayList<Cita> findByMedicoLastname(String name) {
        ArrayList<Cita> listCita = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = null;
        try {
            String sql = "SELECT * FROM Cita INNER JOIN Medico ON Medico.id_especialidad=Cita.id_especialidad WHERE Medico.apellidos LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + name + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objCita = new Cita();
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();
                Especialidad objEspecialidad = new Especialidad();

                objCita.setId_cita(objResult.getInt("Cita.id_cita"));
                objCita.setFecha_cita(objResult.getString("Cita.fecha_cita"));
                objCita.setHora_cita(objResult.getString("Cita.hora_cita"));

                objMedico.setId_medico(objResult.getInt("Medico.id_medico"));
                objMedico.setNombre(objResult.getString("Medico.nombre"));
                objMedico.setApellidos(objResult.getString("Medico.apellidos"));
                objEspecialidad.setId_especialidad(objResult.getInt("Especialidad.id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("Especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("Especialidad.descripcion"));
                objMedico.setEspecialidad(objEspecialidad);

                objPaciente.setId_paciente(objResult.getInt("Paciente.id_paciente"));
                objPaciente.setNombre(objResult.getString("Paciente.nombre"));
                objPaciente.setApellidos(objResult.getString("Paciente.apellidos"));
                objPaciente.setDocumento_identidad(objResult.getString("Paciente.documento_identidad"));
                objPaciente.setFecha_nacimiento(objResult.getString("Paciente.fecha_nacimiento"));


                objCita.setPaciente(objPaciente);
                objCita.setMedico(objMedico);
                listCita.add(objCita);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCita;
    }

}
