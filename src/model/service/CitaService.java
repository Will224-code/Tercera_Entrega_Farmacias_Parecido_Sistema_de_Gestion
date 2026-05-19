package model.service;

import model.entity.Cita;
import model.entity.Paciente;
import model.entity.Medico;
import model.entity.Usuario;
import model.state.EstadoCita;
import model.state.EstadoCitaFactory;
import model.repository.CitaRepository;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CitaService {

    private final CitaRepository citaRepository;
    private final AuditoriaService auditoriaService;

    public CitaService(CitaRepository citaRepository, AuditoriaService auditoriaService) {
        this.citaRepository = citaRepository;
        this.auditoriaService = auditoriaService;
    }

    public Cita agendar(Paciente paciente, Medico medico, Date fecha, Time hora, Usuario usuarioActual) {
        // TODO: Validar conflicto, calcular horaFin, guardar
        return null;
    }

    public void cambiarEstado(int idCita, EstadoCita nuevoEstado, Usuario usuarioActual) {
        // TODO: Validar transición, actualizar estado
    }

    public void cancelar(int idCita, String motivo, Usuario usuarioActual) {
        // TODO: Validar 72h, actualizar motivo y estado
    }

    public List<Cita> buscarPorMedico(int idMedico) {
        // TODO: Llamar a citaRepository.buscarPorMedico(idMedico)
        return new ArrayList<>();
    }

    public List<Cita> buscarPorPaciente(int idPaciente) {
        return new ArrayList<>();
    }

    public List<Cita> buscarTodas() {
        return new ArrayList<>();
    }

    public Cita buscarPorId(int idCita) {
        // TODO: Llamar a citaRepository.buscarPorId(idCita)
        return null;
    }
}