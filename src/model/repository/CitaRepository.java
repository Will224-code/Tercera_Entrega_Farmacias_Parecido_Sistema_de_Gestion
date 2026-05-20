package model.repository;

import model.entity.Cita;
import model.entity.Paciente;
import model.entity.Medico;
import model.state.EstadoCita;
import model.state.EstadoCitaFactory;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Repository simulado para Cita usando ArrayList en memoria.
 */
public class CitaRepository {

    private static final List<Cita> citas = new ArrayList<>();
    private static int nextId = 1;

    public Cita guardar(Cita cita) {
        cita.setIdCita(nextId++);
        citas.add(cita);
        return cita;
    }

    public Cita buscarPorId(int idCita) {
        for (Cita c : citas) {
            if (c.getIdCita() == idCita) {
                return clonar(c);
            }
        }
        return null;
    }

    public List<Cita> buscarPorMedico(int idMedico) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : citas) {
            if (c.getMedico().getIdMedico() == idMedico) {
                resultado.add(clonar(c));
            }
        }
        return resultado;
    }

    public List<Cita> buscarPorPaciente(int idPaciente) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : citas) {
            if (c.getPaciente().getIdPaciente() == idPaciente) {
                resultado.add(clonar(c));
            }
        }
        return resultado;
    }

    public List<Cita> buscarTodas() {
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : citas) {
            resultado.add(clonar(c));
        }
        return resultado;
    }

    public List<Cita> buscarFuturasPorMedico(int idMedico) {
        List<Cita> resultado = new ArrayList<>();
        Date hoy = new Date(System.currentTimeMillis());
        for (Cita c : citas) {
            if (c.getMedico().getIdMedico() == idMedico 
                && !c.getEstadoCita().getNombre().equals("Cancelada")
                && !c.getEstadoCita().getNombre().equals("Expirada")
                && c.getFecha().compareTo(hoy) >= 0) {
                resultado.add(clonar(c));
            }
        }
        return resultado;
    }

    public boolean existeConflictoHorario(int idMedico, Date fecha, Time hora, Integer excluirId) {
        for (Cita c : citas) {
            if (c.getMedico().getIdMedico() == idMedico
                && c.getFecha().equals(fecha)
                && c.getHora().equals(hora)
                && !c.getEstadoCita().getNombre().equals("Cancelada")
                && !c.getEstadoCita().getNombre().equals("Expirada")) {
                if (excluirId == null || c.getIdCita() != excluirId) {
                    return true;
                }
            }
        }
        return false;
    }

    public void actualizarEstado(int idCita, EstadoCita nuevoEstado, String motivoCancelacion) {
        for (Cita c : citas) {
            if (c.getIdCita() == idCita) {
                c.setEstadoCita(nuevoEstado);
                if (motivoCancelacion != null) {
                    c.setMotivoCancelacion(motivoCancelacion);
                }
                return;
            }
        }
    }

    public void cancelarCitasPorMedico(int idMedico, String motivo) {
        for (Cita c : citas) {
            if (c.getMedico().getIdMedico() == idMedico
                && !c.getEstadoCita().getNombre().equals("Cancelada")
                && !c.getEstadoCita().getNombre().equals("Expirada")) {
                c.setEstadoCita(EstadoCitaFactory.crearEstado("CANCELADA"));
                c.setMotivoCancelacion(motivo);
            }
        }
    }

    public void actualizarEstadosAutomaticos() {
        // Simulado - en memoria no hay persistencia temporal real
        // En una implementación real, esto compararía con System.currentTimeMillis()
    }

    private Cita clonar(Cita c) {
        return new Cita(
            c.getIdCita(), c.getFecha(), c.getHora(), c.getHoraFin(),
            c.getMotivoCancelacion(), c.getObservaciones(), c.getConsultorio(),
            EstadoCitaFactory.crearEstado(c.getEstadoCita().getNombre().toUpperCase().replace(" ", "_")),
            clonarPaciente(c.getPaciente()),
            clonarMedico(c.getMedico())
        );
    }

    private Paciente clonarPaciente(Paciente p) {
        return new Paciente(
            p.getIdPaciente(), p.getNumeroExpediente(), p.getNombreCompleto(),
            p.getCurp(), p.getDireccion(), p.getEstadoCivil(), p.getEdad(), p.isActivo()
        );
    }

    private Medico clonarMedico(Medico m) {
        return new Medico(
            m.getIdMedico(), m.getNombreCompleto(), m.getEspecialidad(),
            m.getTelefonoFijo(), m.getTelefonoCelular(), m.getCorreoElectronico(), m.isActivo()
        );
    }
}
