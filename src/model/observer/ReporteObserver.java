package model.observer;

import model.entity.Consulta;

public interface ReporteObserver {
    void actualizar(Consulta consulta);
}