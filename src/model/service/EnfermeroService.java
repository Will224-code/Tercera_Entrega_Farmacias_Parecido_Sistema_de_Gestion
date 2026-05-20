package model.service;

import model.entity.Enfermero;
import model.entity.Medico;
import model.entity.Usuario;
import model.repository.EnfermeroRepository;
import java.util.List;

/**
 * Service para gestión de enfermeros.
 */
public class EnfermeroService {

    private final EnfermeroRepository enfermeroRepository;
    private final AuditoriaService auditoriaService;

    public EnfermeroService(EnfermeroRepository enfermeroRepository, AuditoriaService auditoriaService) {
        this.enfermeroRepository = enfermeroRepository;
        this.auditoriaService = auditoriaService;
    }

    public Enfermero registrar(String nombre, String curp, String direccion,
                                  String estadoCivil, Medico medicoAsignado, Usuario usuarioActual) {
        if (nombre == null || nombre.trim().isEmpty() || curp == null || curp.trim().isEmpty()) {
            return null;
        }

        Enfermero enfermero = new Enfermero(
            0,
            nombre,
            curp.toUpperCase(),
            direccion,
            estadoCivil,
            true,
            medicoAsignado,
            null
        );

        Enfermero guardado = enfermeroRepository.guardar(enfermero);
        if (guardado != null && guardado.getIdEnfermero() > 0) {
            auditoriaService.registrar(usuarioActual, "Enfermero", guardado.getIdEnfermero(), "CREAR");
        }
        return guardado;
    }

    public List<Enfermero> buscarTodos() {
        return enfermeroRepository.buscarTodos();
    }

    public void deshabilitar(int idEnfermero, Usuario usuarioActual) {
        enfermeroRepository.deshabilitar(idEnfermero);
        auditoriaService.registrar(usuarioActual, "Enfermero", idEnfermero, "DESHABILITAR");
    }
}
