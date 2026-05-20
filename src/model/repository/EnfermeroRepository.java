package model.repository;

import model.entity.Enfermero;
import model.entity.Medico;
import model.enums.Especialidad;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository simulado para Enfermero usando ArrayList en memoria.
 */
public class EnfermeroRepository {

    private static final List<Enfermero> enfermeros = new ArrayList<>();
    private static int nextId = 1;

    public Enfermero guardar(Enfermero enfermero) {
        enfermero.setIdEnfermero(nextId++);
        enfermeros.add(enfermero);
        return enfermero;
    }

    public List<Enfermero> buscarTodos() {
        List<Enfermero> resultado = new ArrayList<>();
        for (Enfermero e : enfermeros) {
            if (e.isActivo()) {
                resultado.add(clonar(e));
            }
        }
        return resultado;
    }

    public void deshabilitar(int idEnfermero) {
        for (Enfermero e : enfermeros) {
            if (e.getIdEnfermero() == idEnfermero) {
                e.setActivo(false);
                return;
            }
        }
    }

    private Enfermero clonar(Enfermero e) {
        Medico med = e.getMedico();
        Medico mc = null;
        if (med != null) {
            mc = new Medico(
                med.getIdMedico(), med.getNombreCompleto(), med.getEspecialidad(),
                med.getTelefonoFijo(), med.getTelefonoCelular(), med.getCorreoElectronico(), med.isActivo()
            );
        }
        return new Enfermero(
            e.getIdEnfermero(), e.getNombreCompleto(), e.getCurp(),
            e.getDireccion(), e.getEstadoCivil(), e.isActivo(), mc, null
        );
    }
}
