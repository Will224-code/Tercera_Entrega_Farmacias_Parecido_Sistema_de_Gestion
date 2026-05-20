package model.repository;

import model.entity.Consulta;
import model.entity.Cita;
import model.entity.Paciente;
import model.entity.Medico;
import model.state.EstadoCitaFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository simulado para Consulta usando ArrayList en memoria.
 */
public class ConsultaRepository {

    private static final List<Consulta> consultas = new ArrayList<>();
    private static int nextId = 1;

    public Consulta guardar(Consulta consulta) {
        consulta.setIdConsulta(nextId++);
        consultas.add(consulta);
        return consulta;
    }

    public Consulta buscarPorId(int idConsulta) {
        for (Consulta c : consultas) {
            if (c.getIdConsulta() == idConsulta) {
                return clonar(c);
            }
        }
        return null;
    }

    public Consulta buscarPorCita(int idCita) {
        for (Consulta c : consultas) {
            if (c.getCita().getIdCita() == idCita) {
                return clonar(c);
            }
        }
        return null;
    }

    public List<Consulta> buscarPorPaciente(int idPaciente) {
        List<Consulta> resultado = new ArrayList<>();
        for (Consulta c : consultas) {
            if (c.getCita().getPaciente().getIdPaciente() == idPaciente) {
                resultado.add(clonar(c));
            }
        }
        return resultado;
    }

    private Consulta clonar(Consulta c) {
        Paciente p = c.getCita().getPaciente();
        Medico m = c.getCita().getMedico();

        Paciente pc = new Paciente(
            p.getIdPaciente(), p.getNumeroExpediente(), p.getNombreCompleto(),
            p.getCurp(), p.getDireccion(), p.getEstadoCivil(), p.getEdad(), p.isActivo()
        );
        Medico mc = new Medico(
            m.getIdMedico(), m.getNombreCompleto(), m.getEspecialidad(),
            m.getTelefonoFijo(), m.getTelefonoCelular(), m.getCorreoElectronico(), m.isActivo()
        );

        Cita cita = new Cita();
        cita.setIdCita(c.getCita().getIdCita());
        cita.setFecha(c.getCita().getFecha());
        cita.setHora(c.getCita().getHora());
        cita.setHoraFin(c.getCita().getHoraFin());
        cita.setEstadoCita(EstadoCitaFactory.crearEstado(
            c.getCita().getEstadoCita().getNombre().toUpperCase().replace(" ", "_")));
        cita.setMotivoCancelacion(c.getCita().getMotivoCancelacion());
        cita.setObservaciones(c.getCita().getObservaciones());
        cita.setConsultorio(c.getCita().getConsultorio());
        cita.setPaciente(pc);
        cita.setMedico(mc);

        return new Consulta(
            c.getIdConsulta(), cita, c.getEnfermero(), c.getMotivoConsulta(),
            c.getEstatura(), c.getPeso(), c.getTemperatura(),
            c.getObservacionesClinicas(), c.getDiagnostico(), c.getTratamiento(),
            c.getEstudiosSolicitados(), c.getMedicamentos()
        );
    }
}
