package com.example.DAOImpl;

import com.example.db.Database;
import com.example.DAO.DAOMedicos;
import com.example.models.Medicos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOMedicosImpl extends Database implements DAOMedicos {

    @Override
    public void registrar(Medicos medico) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO medicos(nombre, apellido_p, apellido_m, especialidad, telefono) VALUES(?,?,?,?,?);");
            st.setString(1, medico.getNombre());
            st.setString(2, medico.getApellido_p());
            st.setString(3, medico.getApellido_m());
            st.setString(5, medico.getTelefono());
            st.setString(4, medico.getEspecialidad());

            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public void modificar(Medicos medico) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE medicos SET nombre = ?, apellido_p = ?, apellido_m = ?, especialidad = ?, telefono = ? WHERE id = ?");
            st.setString(1, medico.getNombre());
            st.setString(2, medico.getApellido_p());
            st.setString(3, medico.getApellido_m());
            st.setString(5, medico.getTelefono());
            st.setString(4, medico.getEspecialidad());
            st.setInt(6, medico.getId());

            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrarConexion();
        }

    }

    @Override
    public void eliminar(int medicoId) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM medicos WHERE id = ?;");
            st.setInt(1, medicoId);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public List<Medicos> listar(String nombre) throws Exception {
        List<Medicos> lista = null;

        try {
            this.conectar();
            String query = nombre.isEmpty() ? "SELECT * FROM medicos;" : "SELECT * FROM medicos WHERE nombre LIKE '%" + nombre + "%';";

            PreparedStatement st = this.conexion.prepareStatement(query);
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Medicos medico = new Medicos();
                medico.setId(rs.getInt("id"));
                medico.setNombre(rs.getString("nombre"));
                medico.setApellido_p(rs.getString("apellido_p"));
                medico.setApellido_m(rs.getString("apellido_m"));
                medico.setTelefono(rs.getString("telefono"));
                medico.setEspecialidad(rs.getString("especialidad"));

                lista.add(medico);
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

    @Override
    public Medicos getMedicoById(int medicoId) throws Exception {
        Medicos medico = new Medicos();

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM medicos WHERE id = ? LIMIT 1;");
            st.setInt(1, medicoId);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                medico.setId(rs.getInt("id"));
                medico.setNombre(rs.getString("nombre"));
                medico.setApellido_p(rs.getString("apellido_p"));
                medico.setApellido_m(rs.getString("apellido_m"));
                medico.setTelefono(rs.getString("telefono"));
                medico.setEspecialidad(rs.getString("especialidad"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return medico;
    }
}
