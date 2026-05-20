package model.repository;

import model.entity.Pago;
import model.entity.Consulta;
import model.entity.Cita;
import model.entity.Paciente;
import model.entity.Medico;
import model.enums.MetodoPago;
import model.state.EstadoCitaFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository simulado para Pago usando ArrayList en memoria.
 */
public class PagoRepository {

    private static final List<Pago> pagos = new ArrayList<>();
    private static int nextId = 1;

    public Pago guardar(Pago pago) {
        pago.setIdPago(nextId++);
        pagos.add(pago);
        return pago;
    }

    public Pago buscarPorId(int idPago) {
        for (Pago p : pagos) {
            if (p.getIdPago() == idPago) {
                return clonar(p);
            }
        }
        return null;
    }

    public Pago buscarPorConsulta(int idConsulta) {
        for (Pago p : pagos) {
            if (p.getConsulta().getIdConsulta() == idConsulta) {
                return clonar(p);
            }
        }
        return null;
    }

    private Pago clonar(Pago p) {
        Paciente pa = p.getConsulta().getCita().getPaciente();
        Medico m = p.getConsulta().getCita().getMedico();

        Paciente pac = new Paciente(
            pa.getIdPaciente(), pa.getNumeroExpediente(), pa.getNombreCompleto(),
            pa.getCurp(), pa.getDireccion(), pa.getEstadoCivil(), pa.getEdad(), pa.isActivo()
        );
        Medico med = new Medico(
            m.getIdMedico(), m.getNombreCompleto(), m.getEspecialidad(),
            m.getTelefonoFijo(), m.getTelefonoCelular(), m.getCorreoElectronico(), m.isActivo()
        );

        Cita cita = new Cita();
        cita.setIdCita(p.getConsulta().getCita().getIdCita());
        cita.setFecha(p.getConsulta().getCita().getFecha());
        cita.setHora(p.getConsulta().getCita().getHora());
        cita.setHoraFin(p.getConsulta().getCita().getHoraFin());
        cita.setEstadoCita(EstadoCitaFactory.crearEstado(
            p.getConsulta().getCita().getEstadoCita().getNombre().toUpperCase().replace(" ", "_")));
        cita.setPaciente(pac);
        cita.setMedico(med);

        Consulta consulta = new Consulta();
        consulta.setIdConsulta(p.getConsulta().getIdConsulta());
        consulta.setCita(cita);

        Pago np = new Pago(p.getIdPago(), consulta, p.getMetodoPago(), p.getMonto(), p.getFecha());
        np.setEstrategia(p.getEstrategia());
        return np;
    }
}
