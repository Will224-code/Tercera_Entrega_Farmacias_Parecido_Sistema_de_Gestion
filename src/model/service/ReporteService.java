package model.service;

import model.entity.ReporteDiario;
import model.repository.ReporteRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReporteService {

    private final ReporteRepository reporteRepository;

    public ReporteService(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    public List<ReporteDiario> generarPorFecha(Date fecha) {
        // TODO: reporteRepository.buscarPorFecha(fecha)
        return new ArrayList<>();
    }

    /**
     * Genera reporte con filtros opcionales. Si algún parámetro es null se ignora.
     */
    public List<ReporteDiario> generarPorFiltros(Date fechaInicio, Date fechaFin,
                                                 String medico, String especialidad,
                                                 String paciente) {
        // TODO: reporteRepository.buscarPorFiltros(...)
        return new ArrayList<>();
    }
}