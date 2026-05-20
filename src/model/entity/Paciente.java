package model.entity;

public class Paciente {

    private int idPaciente;
    private String numeroExpediente;
    private String nombreCompleto;
    private String curp;
    private String direccion;
    private String estadoCivil;
    private int edad;
    private boolean activo;

    public Paciente() {
    }

    public Paciente(int idPaciente, String numeroExpediente, String nombreCompleto,
                    String curp, String direccion, String estadoCivil,
                    int edad, boolean activo) {

        this.idPaciente = idPaciente;
        this.numeroExpediente = numeroExpediente;
        this.nombreCompleto = nombreCompleto;
        this.curp = curp;
        this.direccion = direccion;
        this.estadoCivil = estadoCivil;
        this.edad = edad;
        this.activo = activo;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}