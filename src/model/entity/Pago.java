package model.entity;

import model.enums.MetodoPago;
import model.strategy.MetodoPagoStrategy;

import java.sql.Date;

public class Pago {

    private int idPago;
    private Consulta consulta;
    private MetodoPago metodoPago;
    private float monto;
    private Date fecha;

    // Nuevo atributo para el patrón Strategy
    private MetodoPagoStrategy estrategia;

    public Pago() {
    }

    public Pago(int idPago, Consulta consulta,
                MetodoPago metodoPago,
                float monto,
                Date fecha) {

        this.idPago = idPago;
        this.consulta = consulta;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fecha = fecha;
    }

    // Getters y setters existentes
    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // Nuevos métodos para el patrón Strategy
    public MetodoPagoStrategy getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(MetodoPagoStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public void ejecutarPago() {
        if (estrategia != null) {
            estrategia.procesarPago(this.monto);
        } else {
            System.err.println("Error: no se ha asignado una estrategia de pago.");
        }
    }
}