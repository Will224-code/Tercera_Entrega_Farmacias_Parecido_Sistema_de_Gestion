package model.service;

import model.entity.Cita;
import model.entity.Consulta;
import model.entity.Usuario;
import model.enums.Rol;
import model.repository.ConsultaRepository;
import java.util.ArrayList;
import java.util.List;

public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final AuditoriaService auditoriaService;

    public ConsultaService(ConsultaRepository consultaRepository, AuditoriaService auditoriaService) {
        this.consultaRepository = consultaRepository;
        this.auditoriaService = auditoriaService;
    }

    /**
     * Registra una consulta. Solo rol MEDICO puede ejecutar este método.
     */
    public Consulta registrar(Cita cita, float estatura, float peso, float temperatura,
                              String observaciones, String diagnostico, String tratamiento,
                              String estudios, String medicamentos, Usuario usuarioActual) {
        // TODO: Validar que usuarioActual.getRol() == Rol.MEDICO.
        // Crear objeto Consulta.
        // consultaRepository.guardar(consulta).
        // auditoriaService.registrar(usuarioActual, "Consulta", idGenerado, "CREAR").
        return null;
    }

    public Consulta buscarPorCita(int idCita) {
        // TODO: consultaRepository.buscarPorCita(idCita)
        return null;
    }

    /**
     * Verifica si el usuario tiene acceso a las funciones de consulta.
     * @return true si es MEDICO, false si es ADMINISTRADOR u otro.
     */
    public boolean verificarAcceso(Usuario usuario) {
        // Modificado para que retorne true si el rol es MEDICO
        return usuario != null && usuario.getRol() == Rol.MEDICO;
    }
}