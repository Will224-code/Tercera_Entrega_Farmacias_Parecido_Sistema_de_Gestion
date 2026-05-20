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
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Service para gestión de citas con validaciones de negocio.
 */
public class CitaService {

    private final CitaRepository citaRepository;
    private final AuditoriaService auditoriaService;

    public CitaService(CitaRepository citaRepository, AuditoriaService auditoriaService) {
        this.citaRepository = citaRepository;
        this.auditoriaService = auditoriaService;
    }

    /**
     * Agenda una cita validando conflicto de horario y calculando horaFin (+60 min).
     */
    public Cita agendar(Paciente paciente, Medico medico, Date fecha, Time hora, Usuario usuarioActual) {
        if (paciente == null || medico == null || fecha == null || hora == null) {
            return null;
        }

        // Validar conflicto de horario
        if (citaRepository.existeConflictoHorario(medico.getIdMedico(), fecha, hora, null)) {
            System.err.println("Error: Conflicto de horario para el médico " + medico.getNombreCompleto());
            return null;
        }

        // Calcular hora fin (+60 minutos)
        Calendar cal = Calendar.getInstance();
        cal.setTime(hora);
        cal.add(Calendar.MINUTE, 60);
        Time horaFin = new Time(cal.getTimeInMillis());

        Cita cita = new Cita(
            0,
            fecha,
            hora,
            horaFin,
            null,
            null,
            null,
            EstadoCitaFactory.crearEstado("PENDIENTE"),
            paciente,
            medico
        );

        Cita guardada = citaRepository.guardar(cita);
        if (guardada != null && guardada.getIdCita() > 0) {
            auditoriaService.registrar(usuarioActual, "Cita", guardada.getIdCita(), "CREAR");
        }
        return guardada;
    }

    /**
     * Cambia el estado de una cita validando transiciones permitidas.
     */
    public void cambiarEstado(int idCita, EstadoCita nuevoEstado, Usuario usuarioActual) {
        Cita cita = citaRepository.buscarPorId(idCita);
        if (cita == null) {
            return;
        }

        String estadoActual = cita.getEstadoCita().getNombre().toUpperCase().replace(" ", "_");
        String estadoNuevo = nuevoEstado.getNombre().toUpperCase().replace(" ", "_");

        // Validar transiciones permitidas
        boolean transicionValida = false;

        switch (estadoActual) {
            case "PENDIENTE":
                if (estadoNuevo.equals("CONFIRMADA") || estadoNuevo.equals("CANCELADA") 
                    || estadoNuevo.equals("NO_PRESENTADA") || estadoNuevo.equals("EXPIRADA")) {
                    transicionValida = true;
                }
                break;
            case "CONFIRMADA":
                if (estadoNuevo.equals("COMPLETADA") || estadoNuevo.equals("CANCELADA")
                    || estadoNuevo.equals("NO_PRESENTADA")) {
                    transicionValida = true;
                }
                break;
            default:
                // Estados finales no permiten transiciones
                break;
        }

        if (!transicionValida) {
            System.err.println("Transición no válida: " + estadoActual + " -> " + estadoNuevo);
            return;
        }

        citaRepository.actualizarEstado(idCita, nuevoEstado, cita.getMotivoCancelacion());
        auditoriaService.registrar(usuarioActual, "Cita", idCita, "CAMBIAR_ESTADO_" + estadoNuevo);
    }

    /**
     * Cancela una cita validando que falten al menos 72 horas.
     */
    public void cancelar(int idCita, String motivo, Usuario usuarioActual) {
        Cita cita = citaRepository.buscarPorId(idCita);
        if (cita == null) {
            return;
        }

        // Validar 72 horas de anticipación
        long diferenciaMs = cita.getFecha().getTime() - System.currentTimeMillis();
        long diferenciaHoras = TimeUnit.MILLISECONDS.toHours(diferenciaMs);

        if (diferenciaHoras < 72) {
            System.err.println("Error: La cancelación requiere mínimo 72 horas de anticipación");
            return;
        }

        EstadoCita cancelada = EstadoCitaFactory.crearEstado("CANCELADA");
        citaRepository.actualizarEstado(idCita, cancelada, motivo);
        auditoriaService.registrar(usuarioActual, "Cita", idCita, "CANCELAR");
    }

    public List<Cita> buscarPorMedico(int idMedico) {
        return citaRepository.buscarPorMedico(idMedico);
    }

    public List<Cita> buscarPorPaciente(int idPaciente) {
        return citaRepository.buscarPorPaciente(idPaciente);
    }

    public List<Cita> buscarTodas() {
        return citaRepository.buscarTodas();
    }

    public Cita buscarPorId(int idCita) {
        return citaRepository.buscarPorId(idCita);
    }

    /**
     * Ejecuta actualizaciones automáticas de estado (NO_PRESENTADA, EXPIRADA).
     */
    public void ejecutarAutomatismos() {
        citaRepository.actualizarEstadosAutomaticos();
    }
}
