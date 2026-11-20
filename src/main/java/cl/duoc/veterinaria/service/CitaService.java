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

  public List<Cita> listarPorUsuario(String correoUsuario) {
    return repository.findByCorreoUsuarioOrderByFechaHoraDesc(correoUsuario);
  }

  public Cita buscar(Long id, String correoUsuario) {
    return repository.findByIdAndCorreoUsuario(id, correoUsuario)
      .orElseThrow(() -> new RecursoNoEncontradoException("Cita no encontrada: " + id));
  }

  public Cita guardar(Cita cita, String correoUsuario) {
    cita.setId(null);
    cita.setCorreoUsuario(correoUsuario);
    cita.setEstado("SOLICITADA");
    cita.setArchivada(false);
    return repository.save(cita);
  }

  public Cita actualizar(Long id, String correoUsuario, Cita datos) {
    Cita actual = buscar(id, correoUsuario);
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

  public void archivar(Long id, String correoUsuario) {
    Cita cita = buscar(id, correoUsuario);
    cita.setEstado("CANCELADA");
    cita.setArchivada(true);
    repository.save(cita);
  }
}
