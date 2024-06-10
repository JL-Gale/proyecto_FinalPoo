package com.example.DAOImpl;

import com.example.db.Database;
import com.example.DAO.DAOPacientes;
import com.example.models.Pacientes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOPacientesImpl extends Database implements DAOPacientes {

    @Override
    public void registrar(Pacientes paciente) throws Exception {

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO pacientes(nombre, apellido_p, apellido_m, domicilio, telefono) VALUES(?,?,?,?,?);");
            st.setString(1, paciente.getNombre());
            st.setString(2, paciente.getApellido_p());
            st.setString(3, paciente.getApellido_m());
            st.setString(4, paciente.getDomicilio());
            st.setString(5, paciente.getTelefono());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public void modificar(Pacientes paciente) throws Exception {

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE pacientes SET nombre = ?, apellido_p = ?, apellido_m = ?, domicilio = ?, telefono = ? WHERE id = ?");
            st.setString(1, paciente.getNombre());
            st.setString(2, paciente.getApellido_p());
            st.setString(3, paciente.getApellido_m());
            st.setString(4, paciente.getDomicilio());
            st.setString(5, paciente.getTelefono());
            st.setInt(6, paciente.getId());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public void eliminar(int pacintesId) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM pacientes WHERE id = ?;");
            st.setInt(1, pacintesId);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public List<Pacientes> listar(String nombre) throws Exception {
        List<Pacientes> lista = null;

        try {
            String query = nombre.isEmpty() ? "SELECT * FROM pacientes;" : "SELECT * FROM pacientes WHERE nombre LIKE '%"+ nombre +"%';";
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(query);
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pacientes paciente = new Pacientes();
                paciente.setId(rs.getInt("id"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido_p(rs.getString("apellido_p"));
                paciente.setApellido_m(rs.getString("apellido_m"));
                paciente.setDomicilio(rs.getString("domicilio"));
                paciente.setTelefono(rs.getString("telefono"));
                lista.add(paciente);
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
    public Pacientes getPacienteById(int pacienteId) throws Exception {
        Pacientes paciente = new Pacientes();

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM pacientes WHERE id = ? LIMIT 1;");
            st.setInt(1, pacienteId);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                paciente.setId(rs.getInt("id"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido_p(rs.getString("apellido_p"));
                paciente.setApellido_m(rs.getString("apellido_m"));
                paciente.setDomicilio(rs.getString("domicilio"));
                paciente.setTelefono(rs.getString("telefono"));

            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return paciente;
    }

}
