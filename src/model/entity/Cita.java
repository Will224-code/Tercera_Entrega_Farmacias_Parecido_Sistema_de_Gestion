package model.entity;

import model.state.EstadoCita;
import model.state.EstadoCitaFactory;
import java.sql.Date;
import java.sql.Time;

public class Cita {

    private int idCita;
    private Date fecha;
    private Time hora;
    private Time horaFin;
    private String motivoCancelacion;
    private String observaciones;
    private String consultorio;

    private EstadoCita estadoCita;  // Cambiado: ahora es interfaz
    private Paciente paciente;
    private Medico medico;

    public Cita() {
        // Por defecto, estado Pendiente
        this.estadoCita = EstadoCitaFactory.crearEstado("PENDIENTE");
    }

    public Cita(int idCita, Date fecha, Time hora,
                Time horaFin, String motivoCancelacion,
                String observaciones, String consultorio,
                EstadoCita estadoCita,
                Paciente paciente,
                Medico medico) {

        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.horaFin = horaFin;
        this.motivoCancelacion = motivoCancelacion;
        this.observaciones = observaciones;
        this.consultorio = consultorio;
        this.estadoCita = estadoCita;
        this.paciente = paciente;
        this.medico = medico;
    }

    // Getters y Setters existentes (solo se muestra el modificado para estadoCita)
    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }

    // Método para cambiar el estado (delega en la lógica del nuevo estado)
    public void cambiarEstado(EstadoCita nuevoEstado) {
        // Validar transiciones según las reglas de negocio
        // El controller debería usar las reglas definidas antes de llamar este método.
        // Aquí se asigna directamente, pero se puede agregar validación si se desea.
        this.estadoCita = nuevoEstado;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}