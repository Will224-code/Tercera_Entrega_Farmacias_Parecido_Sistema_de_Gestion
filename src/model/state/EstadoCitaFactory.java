package model.state;

import model.state.EstadoCita;
import model.state.EstadoPendiente;
import model.state.EstadoConfirmada;
import model.state.EstadoCompletada;
import model.state.EstadoCancelada;
import model.state.EstadoNoPresentada;
import model.state.EstadoExpirada;

public class EstadoCitaFactory {

    public static EstadoCita crearEstado(String tipo) {
        if (tipo == null) {
            return new EstadoPendiente();
        }

        switch (tipo.toUpperCase()) {
            case "PENDIENTE":
                return new EstadoPendiente();
            case "CONFIRMADA":
                return new EstadoConfirmada();
            case "COMPLETADA":
                return new EstadoCompletada();
            case "CANCELADA":
                return new EstadoCancelada();
            case "NO_PRESENTADA":
                return new EstadoNoPresentada();
            case "EXPIRADA":
                return new EstadoExpirada();
            default:
                return new EstadoPendiente(); // por defecto
        }
    }
}