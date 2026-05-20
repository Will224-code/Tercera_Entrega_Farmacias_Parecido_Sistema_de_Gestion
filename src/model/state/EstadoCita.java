package model.state;
import model.entity.Cita;

public interface EstadoCita {
    void cambiarEstado(Cita cita);
    String getNombre();
}