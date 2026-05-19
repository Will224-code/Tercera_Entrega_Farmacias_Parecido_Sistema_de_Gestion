package model.entity;

import model.entity.Cita;
import model.entity.Enfermero;

public class Consulta {

    private int idConsulta;

    private Cita cita;
    private Enfermero enfermero;

    private String motivoConsulta;
    private float estatura;
    private float peso;
    private float temperatura;

    private String observacionesClinicas;
    private String diagnostico;
    private String tratamiento;
    private String estudiosSolicitados;
    private String medicamentos;

    public Consulta() {
    }

    public Consulta(int idConsulta, Cita cita,
                    Enfermero enfermero,
                    String motivoConsulta,
                    float estatura,
                    float peso,
                    float temperatura,
                    String observacionesClinicas,
                    String diagnostico,
                    String tratamiento,
                    String estudiosSolicitados,
                    String medicamentos) {

        this.idConsulta = idConsulta;
        this.cita = cita;
        this.enfermero = enfermero;
        this.motivoConsulta = motivoConsulta;
        this.estatura = estatura;
        this.peso = peso;
        this.temperatura = temperatura;
        this.observacionesClinicas = observacionesClinicas;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.estudiosSolicitados = estudiosSolicitados;
        this.medicamentos = medicamentos;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Enfermero getEnfermero() {
        return enfermero;
    }

    public void setEnfermero(Enfermero enfermero) {
        this.enfermero = enfermero;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public String getObservacionesClinicas() {
        return observacionesClinicas;
    }

    public void setObservacionesClinicas(String observacionesClinicas) {
        this.observacionesClinicas = observacionesClinicas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getEstudiosSolicitados() {
        return estudiosSolicitados;
    }

    public void setEstudiosSolicitados(String estudiosSolicitados) {
        this.estudiosSolicitados = estudiosSolicitados;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }
}