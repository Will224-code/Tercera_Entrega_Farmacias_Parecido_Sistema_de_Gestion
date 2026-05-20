package model.service;

import model.entity.Consulta;
import model.entity.Pago;
import model.entity.Comprobante;
import model.entity.Usuario;
import model.enums.MetodoPago;
import model.factories.ComprobanteFactory;
import model.repository.PagoRepository;
import model.repository.ComprobanteRepository;
import java.sql.Date;

/**
 * Service para gestión de pagos.
 * Todo pago genera comprobante automáticamente. Nunca debe quedar pago sin comprobante.
 */
public class PagoService {

    private final PagoRepository pagoRepository;
    private final ComprobanteRepository comprobanteRepository;
    private final AuditoriaService auditoriaService;

    public PagoService(PagoRepository pagoRepository,
                       ComprobanteRepository comprobanteRepository,
                       AuditoriaService auditoriaService) {
        this.pagoRepository = pagoRepository;
        this.comprobanteRepository = comprobanteRepository;
        this.auditoriaService = auditoriaService;
    }

    /**
     * Registra un pago y automáticamente genera el comprobante asociado.
     */
    public Pago registrar(Consulta consulta, float monto,
                          MetodoPago metodoPago, Usuario usuarioActual) {
        if (consulta == null || consulta.getIdConsulta() <= 0 || monto < 0 || metodoPago == null) {
            return null;
        }

        Pago pago = new Pago(
            0,
            consulta,
            metodoPago,
            monto,
            new Date(System.currentTimeMillis())
        );

        // Asignar estrategia según método de pago
        switch (metodoPago) {
            case EFECTIVO:
                pago.setEstrategia(new model.strategy.PagoEfectivo());
                break;
            case TARJETA:
                pago.setEstrategia(new model.strategy.PagoTarjeta());
                break;
            case LIBRE:
                pago.setEstrategia(new model.strategy.PagoLibre());
                break;
        }

        Pago guardado = pagoRepository.guardar(pago);
        if (guardado != null && guardado.getIdPago() > 0) {
            // Ejecutar pago con strategy
            guardado.ejecutarPago();

            // Generar comprobante automáticamente - NUNCA debe quedar sin comprobante
            Comprobante comprobante = generarComprobante(guardado, consulta);
            if (comprobante == null) {
                System.err.println("ERROR CRÍTICO: No se pudo generar comprobante para el pago " + guardado.getIdPago());
            }

            auditoriaService.registrar(usuarioActual, "Pago", guardado.getIdPago(), "CREAR");
        }
        return guardado;
    }

    /**
     * Genera el comprobante a partir del pago y la consulta.
     */
    public Comprobante generarComprobante(Pago pago, Consulta consulta) {
        if (pago == null || consulta == null) {
            return null;
        }

        Comprobante comprobante = ComprobanteFactory.crearComprobante(pago);
        if (comprobante != null) {
            return comprobanteRepository.guardar(comprobante);
        }
        return null;
    }

    public Pago buscarPorConsulta(int idConsulta) {
        return pagoRepository.buscarPorConsulta(idConsulta);
    }
}
