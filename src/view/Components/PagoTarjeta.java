package view.Components;

import model.strategy.MetodoPagoStrategy;

public class PagoTarjeta implements MetodoPagoStrategy {

    @Override
    public void procesarPago(float monto) {
        // TODO: validar que el monto sea mayor a 0
        // TODO: en esta versión no hay integración con terminal bancaria, solo registro interno
        System.out.println("Procesando pago con tarjeta: $" + monto);
    }
}