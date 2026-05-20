package model.entity;

public class Enfermero {

    private int idEnfermero;
    private String nombreCompleto;
    private String curp;
    private String direccion;
    private String estadoCivil;
    private boolean activo;

    private Medico medico;
    private Usuario usuario;

    public Enfermero() {
    }

    public Enfermero(int idEnfermero, String nombreCompleto,
                     String curp, String direccion,
                     String estadoCivil, boolean activo,
                     Medico medico, Usuario usuario) {

        this.idEnfermero = idEnfermero;
        this.nombreCompleto = nombreCompleto;
        this.curp = curp;
        this.direccion = direccion;
        this.estadoCivil = estadoCivil;
        this.activo = activo;
        this.medico = medico;
        this.usuario = usuario;
    }

    public int getIdEnfermero() {
        return idEnfermero;
    }

    public void setIdEnfermero(int idEnfermero) {
        this.idEnfermero = idEnfermero;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}