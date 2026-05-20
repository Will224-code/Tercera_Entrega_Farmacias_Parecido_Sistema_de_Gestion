package model.repository;

import model.entity.Medico;
import model.enums.Especialidad;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository simulado para Medico usando ArrayList en memoria.
 */
public class MedicoRepository {

    private static final List<Medico> medicos = new ArrayList<>();
    private static int nextId = 1;

    static {
        // Médico de ejemplo
        medicos.add(new Medico(nextId++, "Dr. Juan Pérez", Especialidad.GENERAL,
            "555-1234", "555-5678", "juan.perez@clinica.com", true));
    }

    public Medico guardar(Medico medico) {
        medico.setIdMedico(nextId++);
        medicos.add(medico);
        return medico;
    }

    public Medico buscarPorId(int idMedico) {
        for (Medico m : medicos) {
            if (m.getIdMedico() == idMedico && m.isActivo()) {
                return clonar(m);
            }
        }
        return null;
    }

    public List<Medico> buscarTodos() {
        List<Medico> resultado = new ArrayList<>();
        for (Medico m : medicos) {
            if (m.isActivo()) {
                resultado.add(clonar(m));
            }
        }
        return resultado;
    }

    public void deshabilitar(int idMedico) {
        for (Medico m : medicos) {
            if (m.getIdMedico() == idMedico) {
                m.setActivo(false);
                return;
            }
        }
    }

    private Medico clonar(Medico m) {
        return new Medico(
            m.getIdMedico(), m.getNombreCompleto(), m.getEspecialidad(),
            m.getTelefonoFijo(), m.getTelefonoCelular(), m.getCorreoElectronico(), m.isActivo()
        );
    }
}
