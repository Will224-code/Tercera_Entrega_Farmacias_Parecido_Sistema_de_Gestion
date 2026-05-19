package model.observer;

import model.entity.Consulta;
import model.entity.ReporteDiario;

public class ReporteDiarioObserver implements ReporteObserver {
    private final ReporteDiario reporteDiario;

    public ReporteDiarioObserver(ReporteDiario reporteDiario) {
        this.reporteDiario = reporteDiario;
    }

    @Override
    public void actualizar(Consulta consulta) {
        // Extraer datos de la consulta y poblar el reporte
        if (consulta != null && consulta.getCita() != null) {
            reporteDiario.setNombreMedico(consulta.getCita().getMedico().getNombreCompleto());
            reporteDiario.setNombrePaciente(consulta.getCita().getPaciente().getNombreCompleto());
            reporteDiario.setMotivoConsulta(consulta.getMotivoConsulta());
            reporteDiario.setEspecialidad(consulta.getCita().getMedico().getEspecialidad().toString());
            reporteDiario.setConsultorio(consulta.getCita().getConsultorio());
            reporteDiario.setFecha(consulta.getCita().getFecha());
        }
    }
}