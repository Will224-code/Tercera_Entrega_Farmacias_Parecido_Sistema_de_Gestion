package model.proxy;

import model.entity.Consulta;
import model.entity.Usuario;
import model.enums.Rol;

public class ConsultaProxy {
    private final Consulta consultaReal;
    private final Usuario usuarioActual;

    public ConsultaProxy(Consulta consultaReal, Usuario usuarioActual) {
        this.consultaReal = consultaReal;
        this.usuarioActual = usuarioActual;
    }

    /**
     * Retorna la consulta real solo si el usuario tiene rol MEDICO.
     * @param usuario Usuario que intenta acceder
     * @return Consulta real si el rol es MEDICO, null en caso contrario
     */
    public Consulta verConsulta(Usuario usuario) {
        if (usuario.getRol() == Rol.MEDICO) {
            return consultaReal;
        } else {
            System.out.println("Acceso denegado: el rol " + usuario.getRol() + " no tiene permisos para ver esta consulta");
            return null;
        }
    }

    /**
     * Misma lógica que verConsulta, controla acceso al expediente clínico.
     * @param usuario Usuario que intenta acceder
     * @return Consulta real si el rol es MEDICO, null en caso contrario
     */
    public Consulta verExpediente(Usuario usuario) {
        if (usuario.getRol() == Rol.MEDICO) {
            return consultaReal;
        } else {
            System.out.println("Acceso denegado: el rol " + usuario.getRol() + " no puede acceder al expediente clínico");
            return null;
        }
    }

    /**
     * Método auxiliar para que el controller verifique permisos antes de mostrar información.
     * @param usuario Usuario a verificar
     * @return true si el rol es MEDICO, false en caso contrario
     */
    public boolean tieneAcceso(Usuario usuario) {
        return usuario != null && usuario.getRol() == Rol.MEDICO;
    }
}