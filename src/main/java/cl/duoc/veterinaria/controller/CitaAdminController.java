package cl.duoc.veterinaria.controller;

import cl.duoc.veterinaria.model.Cita;
import cl.duoc.veterinaria.service.CitaService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
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
}
