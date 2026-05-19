package model.strategy;

public class PagoEfectivo implements MetodoPagoStrategy {

    @Override
    public void procesarPago(float monto) {
        // TODO: validar que el monto recibido sea mayor a 0
        // TODO: registrar el pago en efectivo, no requiere integración externa
        System.out.println("Procesando pago en efectivo: $" + monto);
    }
}