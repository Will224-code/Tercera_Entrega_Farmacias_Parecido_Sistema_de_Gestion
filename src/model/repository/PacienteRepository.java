package model.repository;

import model.entity.Paciente;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {

    public PacienteRepository() {
    }

    public void guardar(Paciente p) {
        // TODO: insertar nuevo paciente
    }

    public Paciente buscarPorExpediente(String numeroExpediente) {
        // TODO: búsqueda exacta por número de expediente
        return null;
    }

    public List<Paciente> buscarPorNombre(String nombre) {
        // TODO: búsqueda parcial por nombre
        return new ArrayList<>();
    }

    public List<Paciente> buscarTodos() {
        // TODO: retorna todos los pacientes (activos e inactivos)
        return new ArrayList<>();
    }

    public void actualizar(Paciente p) {
        // TODO: actualizar datos personales (nunca el número de expediente)
    }

    public void deshabilitar(int idPaciente) {
        // TODO: cambiar activo a false
    }
}