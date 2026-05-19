package model.service;

import model.entity.Paciente;
import model.entity.Usuario;
import model.repository.PacienteRepository;
import java.util.ArrayList;
import java.util.List;

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
        // TODO: Generar número de expediente único (por ejemplo, "EXP-YYYYMMDD-XXXX").
        // Crear objeto Paciente con activo = true.
        // Llamar a pacienteRepository.guardar(p).
        // Obtener el ID generado (si es autogenerado).
        // Llamar a auditoriaService.registrar(usuarioActual, "Paciente", idGenerado, "CREAR").
        // Retornar el paciente con su ID.
        return null;
    }

    public Paciente buscarPorExpediente(String numeroExpediente) {
        // TODO: Llamar a pacienteRepository.buscarPorExpediente(numeroExpediente)
        return null;
    }

    public List<Paciente> buscarPorNombre(String nombre) {
        // TODO: Llamar a pacienteRepository.buscarPorNombre(nombre)
        return new ArrayList<>();
    }

    public List<Paciente> buscarTodos() {
        // TODO: Llamar a pacienteRepository.buscarTodos()
        return new ArrayList<>();
    }

    /**
     * Edita los datos personales del paciente (NUNCA el número de expediente).
     */
    public void editar(Paciente paciente, Usuario usuarioActual) {
        // TODO: Validar que el paciente no sea null.
        // Llamar a pacienteRepository.actualizar(paciente).
        // Registrar en auditoría "ACTUALIZAR".
    }

    /**
     * Deshabilita lógicamente al paciente (activo = false).
     */
    public void deshabilitar(int idPaciente, Usuario usuarioActual) {
        // TODO: Llamar a pacienteRepository.deshabilitar(idPaciente).
        // Registrar en auditoría "DESHABILITAR".
    }
}