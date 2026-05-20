package model.service;

import model.entity.Paciente;
import model.entity.Usuario;
import model.repository.PacienteRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Service para gestión de pacientes con reglas de negocio.
 */
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final AuditoriaService auditoriaService;

    public PacienteService(PacienteRepository pacienteRepository, AuditoriaService auditoriaService) {
        this.pacienteRepository = pacienteRepository;
        this.auditoriaService = auditoriaService;
    }

    /**
     * Registra un nuevo paciente, genera número de expediente único y audita la operación.
     */
    public Paciente registrar(String nombre, String curp, String direccion,
                              String estadoCivil, int edad, Usuario usuarioActual) {
        if (nombre == null || nombre.trim().isEmpty() || curp == null || curp.trim().isEmpty()) {
            return null;
        }

        String numeroExpediente = generarNumeroExpediente();

        Paciente paciente = new Paciente(
            0,
            numeroExpediente,
            nombre,
            curp.toUpperCase(),
            direccion,
            estadoCivil,
            edad,
            true
        );

        Paciente guardado = pacienteRepository.guardar(paciente);
        if (guardado != null && guardado.getIdPaciente() > 0) {
            auditoriaService.registrar(usuarioActual, "Paciente", guardado.getIdPaciente(), "CREAR");
        }
        return guardado;
    }

    public Paciente buscarPorExpediente(String numeroExpediente) {
        return pacienteRepository.buscarPorExpediente(numeroExpediente);
    }

    public List<Paciente> buscarPorNombre(String nombre) {
        return pacienteRepository.buscarPorNombre(nombre);
    }

    public List<Paciente> buscarTodos() {
        return pacienteRepository.buscarTodos();
    }

    /**
     * Edita los datos personales del paciente (NUNCA el número de expediente).
     */
    public void editar(Paciente paciente, Usuario usuarioActual) {
        if (paciente == null || paciente.getIdPaciente() <= 0) {
            return;
        }
        pacienteRepository.actualizar(paciente);
        auditoriaService.registrar(usuarioActual, "Paciente", paciente.getIdPaciente(), "ACTUALIZAR");
    }

    /**
     * Deshabilita lógicamente al paciente (activo = false).
     */
    public void deshabilitar(int idPaciente, Usuario usuarioActual) {
        pacienteRepository.deshabilitar(idPaciente);
        auditoriaService.registrar(usuarioActual, "Paciente", idPaciente, "DESHABILITAR");
    }

    private String generarNumeroExpediente() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fecha = sdf.format(new Date());
        int random = (int) (Math.random() * 9000) + 1000;
        return "EXP-" + fecha + "-" + random;
    }
}
