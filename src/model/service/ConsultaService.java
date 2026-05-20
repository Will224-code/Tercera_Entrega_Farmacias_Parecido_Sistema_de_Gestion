package model.service;

import model.entity.Cita;
import model.entity.Consulta;
import model.entity.Usuario;
import model.enums.Rol;
import model.repository.ConsultaRepository;
import model.observer.ReporteObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * Service para gestión de consultas médicas.
 * Solo rol MEDICO puede registrar consultas.
 * Notifica a ReporteDiarioObserver al registrar.
 */
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final AuditoriaService auditoriaService;
    private final List<ReporteObserver> reporteObservers = new ArrayList<>();

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
        if (!verificarAcceso(usuarioActual)) {
            System.err.println("Acceso denegado: Solo MEDICO puede registrar consultas");
            return null;
        }

        if (cita == null) {
            return null;
        }

        Consulta consulta = new Consulta(
            0,
            cita,
            null, // enfermero
            null, // motivoConsulta - se puede agregar después
            estatura,
            peso,
            temperatura,
            observaciones,
            diagnostico,
            tratamiento,
            estudios,
            medicamentos
        );

        Consulta guardada = consultaRepository.guardar(consulta);
        if (guardada != null && guardada.getIdConsulta() > 0) {
            auditoriaService.registrar(usuarioActual, "Consulta", guardada.getIdConsulta(), "CREAR");

            // Notificar a observers de reporte
            for (ReporteObserver observer : reporteObservers) {
                observer.actualizar(guardada);
            }
        }
        return guardada;
    }

    public Consulta buscarPorCita(int idCita) {
        return consultaRepository.buscarPorCita(idCita);
    }

    public List<Consulta> buscarPorPaciente(int idPaciente) {
        return consultaRepository.buscarPorPaciente(idPaciente);
    }

    /**
     * Verifica si el usuario tiene acceso a las funciones de consulta.
     * @return true si es MEDICO, false si es ADMINISTRADOR u otro.
     */
    public boolean verificarAcceso(Usuario usuario) {
        return usuario != null && usuario.getRol() == Rol.MEDICO;
    }

    /**
     * Agrega un observer para notificaciones de reporte.
     */
    public void agregarReporteObserver(ReporteObserver observer) {
        if (observer != null && !reporteObservers.contains(observer)) {
            reporteObservers.add(observer);
        }
    }

    /**
     * Elimina un observer de reporte.
     */
    public void removerReporteObserver(ReporteObserver observer) {
        reporteObservers.remove(observer);
    }
}
