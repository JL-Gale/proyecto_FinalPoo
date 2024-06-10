
package com.example.DAO;

import com.example.models.Citas;
import java.util.List;

public interface DAOCitas {
    
    public void registrar(Citas cita) throws Exception;

    public void eliminar(int citaId) throws Exception;

    public List<Citas> listar(String nombre) throws Exception;
}
