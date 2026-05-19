package model.repository;

import model.entity.Medico;
import java.util.ArrayList;
import java.util.List;

public class MedicoRepository {

    public MedicoRepository() {
    }

    public void guardar(Medico m) {
        // TODO: insertar un médico nuevo
    }

    public Medico buscarPorId(int idMedico) {
        // TODO: buscar médico por ID
        return null;
    }

    public List<Medico> buscarTodos() {
        // TODO: retorna todos los médicos (incluyendo inactivos)
        return new ArrayList<>();
    }

    public void actualizar(Medico m) {
        // TODO: actualizar datos del médico
    }

    public void deshabilitar(int idMedico) {
        // TODO: cambiar activo a false
    }
}