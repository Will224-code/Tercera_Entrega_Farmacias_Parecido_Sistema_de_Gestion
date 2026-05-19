package model.service;

import model.entity.Medico;
import model.entity.Usuario;
import model.enums.Especialidad;
import model.repository.MedicoRepository;
import model.repository.CitaRepository;
import java.util.ArrayList;
import java.util.List;

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
        // TODO: Crear objeto Medico con activo = true.
        // medicoRepository.guardar(m).
        // auditoriaService.registrar(usuarioActual, "Medico", idGenerado, "CREAR").
        return null;
    }

    public List<Medico> buscarTodos() {
        // TODO: medicoRepository.buscarTodos()
        return new ArrayList<>();
    }

    public Medico buscarPorId(int idMedico) {
        // TODO: medicoRepository.buscarPorId(idMedico)
        return null;
    }

    /**
     * Deshabilita al médico y todas sus citas futuras en una sola transacción.
     */
    public void deshabilitar(int idMedico, Usuario usuarioActual) {
        // TODO: 1. Buscar citas futuras del médico (citaRepository.buscarFuturasPorMedico(idMedico)).
        //       2. Para cada cita futura, cambiar su estado a CANCELADA o EXPIRADA (según reglas).
        //       3. Deshabilitar al médico (medicoRepository.deshabilitar(idMedico)).
        //       4. Registrar auditoría tanto del médico como de cada cita afectada.
        // Este método debe ser atómico (usar transacción cuando se implemente JDBC).
    }
}