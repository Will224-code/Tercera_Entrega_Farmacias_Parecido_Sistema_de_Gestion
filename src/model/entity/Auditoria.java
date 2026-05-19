package model.entity;

import java.sql.Timestamp;

public class Auditoria {

    private int idAuditoria;

    private Usuario usuario;

    private String entidadAuditada;
    private int idEntidad;
    private String accion;

    private Timestamp fechaHora;

    public Auditoria() {
    }

    public Auditoria(int idAuditoria,
                     Usuario usuario,
                     String entidadAuditada,
                     int idEntidad,
                     String accion,
                     Timestamp fechaHora) {

        this.idAuditoria = idAuditoria;
        this.usuario = usuario;
        this.entidadAuditada = entidadAuditada;
        this.idEntidad = idEntidad;
        this.accion = accion;
        this.fechaHora = fechaHora;
    }

    public int getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(int idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEntidadAuditada() {
        return entidadAuditada;
    }

    public void setEntidadAuditada(String entidadAuditada) {
        this.entidadAuditada = entidadAuditada;
    }

    public int getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(int idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }
}