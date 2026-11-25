package cl.duoc.veterinaria.controller;

import cl.duoc.veterinaria.dto.CitaAdminRequest;
import cl.duoc.veterinaria.dto.EstadoCitaRequest;
import cl.duoc.veterinaria.model.Cita;
import cl.duoc.veterinaria.service.CitaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/citas")
public class CitaAdminController {
  private final CitaService service;

  public CitaAdminController(CitaService service) {
    this.service = service;
  }

  @GetMapping
  public List<Cita> listarTodas() {
    return service.listarTodas();
  }

  @PutMapping("/{id}/estado")
  public Cita actualizarEstado(@PathVariable Long id,
      @Valid @RequestBody EstadoCitaRequest solicitud) {
    return service.actualizarEstadoAdmin(id, solicitud.estado());
  }
  @PutMapping("/{id}")
  public Cita actualizar(@PathVariable Long id,
      @Valid @RequestBody CitaAdminRequest datos) {
    return service.actualizarAdmin(id, datos);
  }
}

