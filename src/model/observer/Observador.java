package model.observer;

public interface Observador {
    void actualizar(String entidad, String accion, int idEntidad);
}