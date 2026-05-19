package model.service;

import model.entity.Consulta;
import model.entity.Pago;
import model.entity.Comprobante;
import model.entity.Usuario;
import model.enums.MetodoPago;
import model.repository.PagoRepository;
import model.repository.ComprobanteRepository;
import java.sql.Date;

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
        // TODO: Crear objeto Pago con fecha actual.
        // pagoRepository.guardar(pago).
        // Llamar a generarComprobante(pago, consulta) para crear el comprobante.
        // auditoriaService.registrar(...).
        return null;
    }

    /**
     * Genera el comprobante a partir del pago y la consulta. Nunca debe quedar un pago sin comprobante.
     */
    public Comprobante generarComprobante(Pago pago, Consulta consulta) {
        // TODO: Construir objeto Comprobante con los datos relevantes.
        // comprobanteRepository.guardar(comprobante).
        // Retornar el comprobante generado.
        return null;
    }

    public Pago buscarPorConsulta(int idConsulta) {
        // TODO: pagoRepository.buscarPorConsulta(idConsulta)
        return null;
    }
}