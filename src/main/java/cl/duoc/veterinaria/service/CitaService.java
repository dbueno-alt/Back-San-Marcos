package cl.duoc.veterinaria.service;

import  cl.duoc.veterinaria.dto.CitaAdminRequest;
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

  public List<Cita> listarTodas() {
    return repository.findAllByOrderByFechaHoraDesc();
  }

  public Cita actualizarEstadoAdmin(Long id, String estado) {
    Cita cita = repository.findById(id)
      .orElseThrow(() -> new RecursoNoEncontradoException("Cita no encontrada: " + id));
    cita.setEstado(estado);
    cita.setArchivada("ATENDIDA".equals(estado) || "CANCELADA".equals(estado));
    return repository.save(cita);
  }
  
  public Cita actualizarAdmin(Long id, CitaAdminRequest datos) {
    Cita cita = repository.findById(id)
      .orElseThrow(() -> new RecursoNoEncontradoException("Cita no encontrada: " + id));
    cita.setNombreMascota(datos.nombreMascota());
    cita.setEspecie(datos.especie());
    cita.setFechaHora(datos.fechaHora());
    cita.setMotivo(datos.motivo());
    cita.setObservaciones(datos.obvervaciones());
    return repository.save(cita);
  }

  public List<Cita> listarPorUsuario(String correoUsuario) {
    return repository.findByCorreoUsuarioAndArchivadaFalseOrderByFechaHoraDesc(correoUsuario);
  }

  public List<Cita> listarArchivadasPorUsuario(String correoUsuario) {
    return repository.findByCorreoUsuarioAndArchivadaTrueOrderByFechaHoraDesc(correoUsuario);
  }

  public Cita buscar(Long id, String correoUsuario) {
    return repository.findByIdAndCorreoUsuario(id, correoUsuario)
      .orElseThrow(() -> new RecursoNoEncontradoException("Cita no encontrada: " + id));
  }

  public Cita guardar(Cita cita, String correoUsuario) {
    cita.setId(null);
    cita.setCorreoUsuario(correoUsuario);
    cita.setObservaciones(null);
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
    cita.setArchivada(true);
    repository.save(cita);
  }
}
