package model.state;

import model.entity.Cita;

public class EstadoCompletada implements EstadoCita {


    public void cambiarEstado(Cita cita) {
        // Estado final, no permite transiciones
    }


    public String getNombre() {
        return "Completada";
    }
}