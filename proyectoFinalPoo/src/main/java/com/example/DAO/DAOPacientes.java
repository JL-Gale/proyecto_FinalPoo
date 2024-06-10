package com.example.DAO;

import com.example.models.Pacientes;
import java.util.List;

public interface DAOPacientes {

    public void registrar(Pacientes paciente) throws Exception;

    public void modificar(Pacientes paciente) throws Exception;

    public void eliminar(int pacienteId) throws Exception;

    public List<Pacientes> listar(String nombre) throws Exception;
    
    public Pacientes getPacienteById(int pacienteId) throws Exception;
}
