package com.example.DAOImpl;

import com.example.db.Database;
import com.example.DAO.DAOCitas;
import com.example.models.Citas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOCitasImpl extends Database implements DAOCitas {

    @Override
    public void registrar(Citas cita) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO citas(id_medico, id_paciente, nomMedico, nomPaciente, especialidad) VALUES(?,?,?,?,?);");
            st.setInt(1, cita.getId_medico());
            st.setInt(2, cita.getId_paciente());
            st.setString(3, cita.getNomMedico());
            st.setString(4, cita.getNomPaciente());
            st.setString(5, cita.getEspecialidad());

            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public void eliminar(int citaId) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM citas WHERE id = ?;");
            st.setInt(1, citaId);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrarConexion();
        }

    }

    @Override
    public List<Citas> listar(String nombre) throws Exception {
        List<Citas> lista = null;

        try {
            this.conectar();
            String query = nombre.isEmpty() ? "SELECT * FROM citas;" : "SELECT * FROM citas WHERE nomPaciente LIKE '%" + nombre + "%';";
            PreparedStatement st = this.conexion.prepareStatement(query);
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Citas cita = new Citas();
                cita.setId(rs.getInt("id"));
                cita.setId_medico(rs.getInt("id_medico"));
                cita.setId_paciente(rs.getInt("id_paciente"));
                cita.setNomMedico(rs.getString("nomMedico"));
                cita.setNomPaciente(rs.getString("nomPaciente"));
                cita.setEspecialidad(rs.getString("especialidad"));

                lista.add(cita);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return lista;

    }

}
