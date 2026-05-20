package model.entity;

import java.sql.Date;

public class ReporteDiario {

    private int idReporte;
    private Date fecha;
    private String consultorio;
    private String especialidad;
    private String nombreMedico;
    private String nombrePaciente;
    private String motivoConsulta;

    public ReporteDiario() {
    }

    public ReporteDiario(int idReporte, Date fecha,
                         String consultorio,
                         String especialidad,
                         String nombreMedico,
                         String nombrePaciente,
                         String motivoConsulta) {

        this.idReporte = idReporte;
        this.fecha = fecha;
        this.consultorio = consultorio;
        this.especialidad = especialidad;
        this.nombreMedico = nombreMedico;
        this.nombrePaciente = nombrePaciente;
        this.motivoConsulta = motivoConsulta;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }
}