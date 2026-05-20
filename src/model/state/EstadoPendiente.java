package model.state;

import model.entity.Cita;

public class EstadoPendiente implements EstadoCita {


    public void cambiarEstado(Cita cita) {
        // No hace nada aquí; el controller debe llamar a cita.cambiarEstado(nuevoEstado)
        // Este método se usa cuando se quiere cambiar DESDE el estado actual.
        // Para el patrón State, normalmente se recibe el nuevo estado como parámetro.
        // Pero según la especificación, cada estado conoce las transiciones permitidas.
        // Sin embargo, las transiciones son múltiples desde un mismo estado.
        // Para simplificar, se usará cita.setEstadoCita(nuevoEstado) directamente.
        // Dejamos este método vacío para que el controller use el factory.
    }


    public String getNombre() {
        return "Pendiente";
    }
}