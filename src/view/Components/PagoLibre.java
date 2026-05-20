package view.Components;

import model.strategy.MetodoPagoStrategy;

public class PagoLibre implements MetodoPagoStrategy {

    @Override
    public void procesarPago(float monto) {
        // TODO: este método representa pago sin costo o cortesía
        System.out.println("Procesando pago libre (sin costo): $" + monto);
    }
}