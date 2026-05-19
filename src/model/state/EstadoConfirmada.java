package model.state;

import model.entity.Cita;

public class EstadoConfirmada implements EstadoCita {

    public void cambiarEstado(Cita cita) {
        // El controller decide la transición
    }

    public String getNombre() {
        return "Confirmada";
    }
}