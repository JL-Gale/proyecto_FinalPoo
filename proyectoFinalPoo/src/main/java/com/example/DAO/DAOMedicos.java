package com.example.DAO;

import com.example.models.Medicos;
import java.util.List;

public interface DAOMedicos {

    public void registrar(Medicos medico) throws Exception;

    public void modificar(Medicos medico) throws Exception;

    public void eliminar(int medicoId) throws Exception;

    public List<Medicos> listar(String nombre) throws Exception;

    public Medicos getMedicoById(int medicoId) throws Exception;
}
