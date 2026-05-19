package model.factories;

import model.entity.Comprobante;
import model.entity.Pago;

public class ComprobanteFactory {

    public static Comprobante crearComprobante(Pago pago) {
        if (pago == null) {
            System.err.println("Error: no se puede crear comprobante, el pago es null");
            return null;
        }

        Comprobante comprobante = new Comprobante();
        comprobante.setIdComprobante(0); // será generado por la BD
        comprobante.setConsulta(pago.getConsulta());
        comprobante.setPago(pago);
        comprobante.setNumeroConsulta(String.valueOf(pago.getConsulta().getCita().getIdCita()));
        comprobante.setNombrePaciente(pago.getConsulta().getCita().getPaciente().getNombreCompleto());
        comprobante.setEspecialidad(pago.getConsulta().getCita().getMedico().getEspecialidad().toString());
        comprobante.setMetodoPago(pago.getMetodoPago().toString());
        comprobante.setFecha(pago.getFecha());
        comprobante.setMonto(pago.getMonto());

        return comprobante;
    }
}