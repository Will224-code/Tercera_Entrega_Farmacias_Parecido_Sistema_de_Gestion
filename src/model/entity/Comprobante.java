package model.entity;

import java.sql.Date;

public class Comprobante {

    private int idComprobante;

    private Consulta consulta;
    private Pago pago;

    private String numeroConsulta;
    private String nombrePaciente;
    private String especialidad;
    private String metodoPago;
    private Date fecha;
    private float monto;

    public Comprobante() {
    }

    public Comprobante(int idComprobante,
                       Consulta consulta,
                       Pago pago,
                       String numeroConsulta,
                       String nombrePaciente,
                       String especialidad,
                       String metodoPago,
                       Date fecha,
                       float monto) {

        this.idComprobante = idComprobante;
        this.consulta = consulta;
        this.pago = pago;
        this.numeroConsulta = numeroConsulta;
        this.nombrePaciente = nombrePaciente;
        this.especialidad = especialidad;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
        this.monto = monto;
    }

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public String getNumeroConsulta() {
        return numeroConsulta;
    }

    public void setNumeroConsulta(String numeroConsulta) {
        this.numeroConsulta = numeroConsulta;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
}