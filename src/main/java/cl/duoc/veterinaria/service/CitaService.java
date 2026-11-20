package cl.duoc.veterinaria.service;

import cl.duoc.veterinaria.exception.RecursoNoEncontradoException;
import cl.duoc.veterinaria.model.Cita;
import cl.duoc.veterinaria.repository.CitaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CitaService {
  private final CitaRepository repository;

  public CitaService(CitaRepository repository) {
    this.repository = repository;
  }

  public List<Cita> listarPorUsuario(Long usuarioId) {
    return repository.findByUsuarioIdOrderByFechaHoraDesc(usuarioId);
  }

  public Cita buscar(Long id, Long usuarioId) {
    return repository.findByIdAndUsuarioId(id, usuarioId)
      .orElseThrow(() -> new RecursoNoEncontradoException("Cita no encontrada: " + id));
  }

  public Cita guardar(Cita cita, Long usuarioId) {
    cita.setId(null);
    cita.setUsuarioId(usuarioId);
    cita.setEstado("SOLICITADA");
    cita.setArchivada(false);
    return repository.save(cita);
  }

  public Cita actualizar(Long id, Long usuarioId, Cita datos) {
    Cita actual = buscar(id, usuarioId);
    if (actual.isArchivada()) {
      throw new RecursoNoEncontradoException("Cita no encontrada: " + id);
    }

    actual.setNombreMascota(datos.getNombreMascota());
    actual.setEspecie(datos.getEspecie());
    actual.setFechaHora(datos.getFechaHora());
    actual.setMotivo(datos.getMotivo());
    actual.setRecordatorioSolicitado(datos.isRecordatorioSolicitado());
    return repository.save(actual);
  }

  public void archivar(Long id, Long usuarioId) {
    Cita cita = buscar(id, usuarioId);
    cita.setEstado("CANCELADA");
    cita.setArchivada(true);
    repository.save(cita);
  }
}
