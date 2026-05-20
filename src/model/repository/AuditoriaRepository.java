package model.repository;

import model.entity.Auditoria;
import model.entity.Usuario;
import model.enums.Rol;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository simulado para Auditoria usando ArrayList en memoria.
 * Solo permite inserción. Nunca modifica ni elimina registros.
 */
public class AuditoriaRepository {

    private static final List<Auditoria> registros = new ArrayList<>();
    private static int nextId = 1;

    public void registrar(Auditoria auditoria) {
        auditoria.setIdAuditoria(nextId++);
        registros.add(auditoria);
    }

    public List<Auditoria> buscarTodos() {
        return new ArrayList<>(registros);
    }
}
