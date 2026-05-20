package model.repository;

import model.entity.Comprobante;
import model.entity.Pago;
import model.entity.Consulta;
import model.entity.Cita;
import model.entity.Paciente;
import model.entity.Medico;
import model.enums.MetodoPago;
import model.state.EstadoCitaFactory;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository simulado para Comprobante usando ArrayList en memoria.
 */
public class ComprobanteRepository {

    private static final List<Comprobante> comprobantes = new ArrayList<>();
    private static int nextId = 1;

    public Comprobante guardar(Comprobante comprobante) {
        comprobante.setIdComprobante(nextId++);
        comprobantes.add(comprobante);
        return comprobante;
    }

    public Comprobante buscarPorId(int idComprobante) {
        for (Comprobante c : comprobantes) {
            if (c.getIdComprobante() == idComprobante) {
                return clonar(c);
            }
        }
        return null;
    }

    public Comprobante buscarPorPago(int idPago) {
        for (Comprobante c : comprobantes) {
            if (c.getPago().getIdPago() == idPago) {
                return clonar(c);
            }
        }
        return null;
    }

    public List<Comprobante> buscarPorFecha(Date fecha) {
        List<Comprobante> resultado = new ArrayList<>();
        for (Comprobante c : comprobantes) {
            if (c.getFecha().equals(fecha)) {
                resultado.add(clonar(c));
            }
        }
        return resultado;
    }

    private Comprobante clonar(Comprobante c) {
        Paciente pa = c.getConsulta().getCita().getPaciente();
        Medico m = c.getConsulta().getCita().getMedico();

        Paciente pac = new Paciente(
            pa.getIdPaciente(), pa.getNumeroExpediente(), pa.getNombreCompleto(),
            pa.getCurp(), pa.getDireccion(), pa.getEstadoCivil(), pa.getEdad(), pa.isActivo()
        );
        Medico med = new Medico(
            m.getIdMedico(), m.getNombreCompleto(), m.getEspecialidad(),
            m.getTelefonoFijo(), m.getTelefonoCelular(), m.getCorreoElectronico(), m.isActivo()
        );

        Cita cita = new Cita();
        cita.setIdCita(c.getConsulta().getCita().getIdCita());
        cita.setFecha(c.getConsulta().getCita().getFecha());
        cita.setHora(c.getConsulta().getCita().getHora());
        cita.setHoraFin(c.getConsulta().getCita().getHoraFin());
        cita.setEstadoCita(EstadoCitaFactory.crearEstado(
            c.getConsulta().getCita().getEstadoCita().getNombre().toUpperCase().replace(" ", "_")));
        cita.setPaciente(pac);
        cita.setMedico(med);

        Consulta consulta = new Consulta();
        consulta.setIdConsulta(c.getConsulta().getIdConsulta());
        consulta.setCita(cita);

        Pago pago = new Pago(c.getPago().getIdPago(), consulta, 
            c.getPago().getMetodoPago(), c.getPago().getMonto(), c.getPago().getFecha());

        return new Comprobante(
            c.getIdComprobante(), consulta, pago,
            c.getNumeroConsulta(), c.getNombrePaciente(), c.getEspecialidad(),
            c.getMetodoPago(), c.getFecha(), c.getMonto()
        );
    }
}
