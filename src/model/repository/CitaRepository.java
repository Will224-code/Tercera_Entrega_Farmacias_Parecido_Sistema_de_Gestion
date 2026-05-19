package model.repository;

import model.entity.Cita;
import model.state.EstadoCita;          // ← cambiado: ahora usa la interfaz
import java.util.ArrayList;
import java.util.List;

public class CitaRepository {

    public CitaRepository() {
    }

    public void guardar(Cita c) {
        // TODO: insertar una cita nueva con estado PENDIENTE
        // Para guardar el estado, usar c.getEstadoCita().getNombre() que retorna "Pendiente", etc.
    }

    public Cita buscarPorId(int idCita) {
        // TODO: buscar cita por ID
        return null;
    }

    public List<Cita> buscarPorMedico(int idMedico) {
        // TODO: todas las citas de un médico
        return new ArrayList<>();
    }

    public List<Cita> buscarPorPaciente(int idPaciente) {
        // TODO: todas las citas de un paciente
        return new ArrayList<>();
    }

    public List<Cita> buscarFuturasPorMedico(int idMedico) {
        // TODO: solo citas futuras activas (se usa al deshabilitar un médico)
        return new ArrayList<>();
    }

    public void actualizarEstado(int idCita, EstadoCita estado) {  // ← tipo cambiado
        // TODO: actualizar únicamente el estado de la cita.
        // Para guardar el nuevo estado en BD, usar estado.getNombre() que retorna "Confirmada", "Cancelada", etc.
    }

    public List<Cita> buscarTodas() {
        // TODO: retorna todas las citas
        return new ArrayList<>();
    }
}