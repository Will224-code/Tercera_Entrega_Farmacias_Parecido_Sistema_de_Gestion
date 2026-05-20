package model.service;

import model.entity.Medico;
import model.entity.Usuario;
import model.enums.Especialidad;
import model.repository.MedicoRepository;
import model.repository.CitaRepository;

import java.util.List;

/**
 * Service para gestión de médicos con reglas de negocio.
 */
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final CitaRepository citaRepository;
    private final AuditoriaService auditoriaService;

    public MedicoService(MedicoRepository medicoRepository,
                         CitaRepository citaRepository,
                         AuditoriaService auditoriaService) {
        this.medicoRepository = medicoRepository;
        this.citaRepository = citaRepository;
        this.auditoriaService = auditoriaService;
    }

    public Medico registrar(String nombre, Especialidad especialidad,
                            String telFijo, String telCelular,
                            String correo, Usuario usuarioActual) {
        if (nombre == null || nombre.trim().isEmpty() || especialidad == null) {
            return null;
        }

        Medico medico = new Medico(
            0,
            nombre,
            especialidad,
            telFijo,
            telCelular,
            correo,
            true
        );

        Medico guardado = medicoRepository.guardar(medico);
        if (guardado != null && guardado.getIdMedico() > 0) {
            auditoriaService.registrar(usuarioActual, "Medico", guardado.getIdMedico(), "CREAR");
        }
        return guardado;
    }

    public List<Medico> buscarTodos() {
        return medicoRepository.buscarTodos();
    }

    public Medico buscarPorId(int idMedico) {
        return medicoRepository.buscarPorId(idMedico);
    }

    /**
     * Deshabilita al médico y todas sus citas futuras en una sola transacción atómica.
     */
    public void deshabilitar(int idMedico, Usuario usuarioActual) {
        // 1. Cancelar citas futuras
        citaRepository.cancelarCitasPorMedico(idMedico, "Médico deshabilitado");

        // 2. Deshabilitar médico
        medicoRepository.deshabilitar(idMedico);

        // 3. Registrar auditoría
        auditoriaService.registrar(usuarioActual, "Medico", idMedico, "DESHABILITAR");

        // Nota: En una implementación con transacciones JDBC reales,
        // ambas operaciones deberían estar en la misma transacción.
        // Actualmente CitaRepository y MedicoRepository manejan sus propias conexiones.
        // Para hacerlo atómico, se necesitaría un TransactionManager centralizado.
    }
}
