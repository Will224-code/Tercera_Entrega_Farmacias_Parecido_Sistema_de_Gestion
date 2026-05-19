package model.repository;

import model.entity.ReporteDiario;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReporteRepository {

    public ReporteRepository() {
    }

    public List<ReporteDiario> buscarPorFecha(Date fecha) {
        // TODO: reporte de un día específico
        return new ArrayList<>();
    }

    public List<ReporteDiario> buscarPorFiltros(Date fechaInicio,
                                                Date fechaFin,
                                                String medico,
                                                String especialidad,
                                                String paciente) {
        // TODO: todos los parámetros son opcionales, si vienen null se ignoran como filtro
        return new ArrayList<>();
    }
}