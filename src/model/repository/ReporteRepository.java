package model.repository;

import model.entity.ReporteDiario;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository simulado para ReporteDiario usando ArrayList en memoria.
 */
public class ReporteRepository {

    private static final List<ReporteDiario> reportes = new ArrayList<>();
    private static int nextId = 1;

    public ReporteDiario guardar(ReporteDiario reporte) {
        reporte.setIdReporte(nextId++);
        reportes.add(reporte);
        return reporte;
    }

    public List<ReporteDiario> buscarPorFecha(Date fecha) {
        List<ReporteDiario> resultado = new ArrayList<>();
        for (ReporteDiario r : reportes) {
            if (r.getFecha().equals(fecha)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public List<ReporteDiario> buscarPorFiltros(Date fechaInicio, Date fechaFin,
                                                 String medico, String especialidad,
                                                 String paciente) {
        List<ReporteDiario> resultado = new ArrayList<>();
        for (ReporteDiario r : reportes) {
            boolean match = true;
            if (fechaInicio != null && r.getFecha().before(fechaInicio)) match = false;
            if (fechaFin != null && r.getFecha().after(fechaFin)) match = false;
            if (medico != null && !medico.isEmpty() && !r.getNombreMedico().contains(medico)) match = false;
            if (especialidad != null && !especialidad.isEmpty() && !r.getEspecialidad().equals(especialidad)) match = false;
            if (paciente != null && !paciente.isEmpty() && !r.getNombrePaciente().contains(paciente)) match = false;
            if (match) resultado.add(r);
        }
        return resultado;
    }
}
