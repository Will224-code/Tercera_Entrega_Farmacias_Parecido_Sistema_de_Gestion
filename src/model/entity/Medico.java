package model.entity;

import model.enums.Especialidad;

public class Medico {

    private int idMedico;
    private String nombreCompleto;
    private Especialidad especialidad;
    private String telefonoFijo;
    private String telefonoCelular;
    private String correoElectronico;
    private boolean activo;

    public Medico() {
    }

    public Medico(int idMedico, String nombreCompleto, Especialidad especialidad,
                  String telefonoFijo, String telefonoCelular,
                  String correoElectronico, boolean activo) {

        this.idMedico = idMedico;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
        this.telefonoFijo = telefonoFijo;
        this.telefonoCelular = telefonoCelular;
        this.correoElectronico = correoElectronico;
        this.activo = activo;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}