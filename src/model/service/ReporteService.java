package model.service;

import model.entity.ReporteDiario;
import model.repository.ReporteRepository;
import java.sql.Date;
import java.util.List;

/**
 * Service para generación de reportes diarios.
 */
public class ReporteService {

    private final ReporteRepository reporteRepository;

    public ReporteService(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    public List<ReporteDiario> generarPorFecha(Date fecha) {
        return reporteRepository.buscarPorFecha(fecha);
    }

    /**
     * Genera reporte con filtros opcionales. Si algún parámetro es null se ignora.
     */
    public List<ReporteDiario> generarPorFiltros(Date fechaInicio, Date fechaFin,
                                                 String medico, String especialidad,
                                                 String paciente) {
        return reporteRepository.buscarPorFiltros(fechaInicio, fechaFin, medico, especialidad, paciente);
    }
}
