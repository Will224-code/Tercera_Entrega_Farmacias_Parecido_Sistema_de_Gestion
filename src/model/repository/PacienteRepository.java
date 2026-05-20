package model.repository;

import model.entity.Paciente;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository simulado para Paciente usando ArrayList en memoria.
 */
public class PacienteRepository {

    private static final List<Paciente> pacientes = new ArrayList<>();
    private static int nextId = 1;

    public Paciente guardar(Paciente paciente) {
        paciente.setIdPaciente(nextId++);
        pacientes.add(paciente);
        return paciente;
    }

    public Paciente buscarPorId(int idPaciente) {
        for (Paciente p : pacientes) {
            if (p.getIdPaciente() == idPaciente && p.isActivo()) {
                return clonar(p);
            }
        }
        return null;
    }

    public Paciente buscarPorExpediente(String numeroExpediente) {
        for (Paciente p : pacientes) {
            if (p.getNumeroExpediente().equals(numeroExpediente) && p.isActivo()) {
                return clonar(p);
            }
        }
        return null;
    }

    public List<Paciente> buscarPorNombre(String nombre) {
        List<Paciente> resultado = new ArrayList<>();
        String busqueda = nombre.toLowerCase();
        for (Paciente p : pacientes) {
            if (p.isActivo() && (p.getNombreCompleto().toLowerCase().contains(busqueda) 
                || p.getNumeroExpediente().toLowerCase().contains(busqueda))) {
                resultado.add(clonar(p));
            }
        }
        return resultado;
    }

    public List<Paciente> buscarTodos() {
        List<Paciente> resultado = new ArrayList<>();
        for (Paciente p : pacientes) {
            if (p.isActivo()) {
                resultado.add(clonar(p));
            }
        }
        return resultado;
    }

    public void actualizar(Paciente paciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getIdPaciente() == paciente.getIdPaciente()) {
                pacientes.set(i, paciente);
                return;
            }
        }
    }

    public void deshabilitar(int idPaciente) {
        for (Paciente p : pacientes) {
            if (p.getIdPaciente() == idPaciente) {
                p.setActivo(false);
                return;
            }
        }
    }

    private Paciente clonar(Paciente p) {
        return new Paciente(
            p.getIdPaciente(), p.getNumeroExpediente(), p.getNombreCompleto(),
            p.getCurp(), p.getDireccion(), p.getEstadoCivil(), p.getEdad(), p.isActivo()
        );
    }
}
