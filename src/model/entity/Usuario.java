package model.entity;

import model.enums.Rol;

public class Usuario {

    private int idUsuario;
    private String nombreUsuario;
    private String contrasena;
    private Rol rol;  // ✅ enum Rol
    private boolean activo;

    public Usuario() {
    }

    // Constructor CORREGIDO
    public Usuario(int idUsuario, String nombreUsuario,
                   String contrasena, Rol rol,  // ← Rol, no String
                   boolean activo) {

        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.activo = activo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // GETTER - devuelve Rol
    public Rol getRol() {
        return rol;
    }

    // SETTER - recibe Rol
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}