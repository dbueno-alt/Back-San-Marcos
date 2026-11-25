package cl.duoc.veterinaria.controller;

import cl.duoc.veterinaria.dto.AtencionMedicaRequest;
import cl.duoc.veterinaria.model.Cita;
import cl.duoc.veterinaria.service.CitaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/veterinario/citas")
public class CitaVeterinarioController {
  private final CitaService service;

  public CitaVeterinarioController(CitaService service) {
    this.service = service;
  }

  @GetMapping
  public List<Cita> listarAgendaClinica() {
    return service.listarTodas();
  }

  @PutMapping("/{id}/atencion")
  public Cita registrarAtencion(@PathVariable Long id,
      @Valid @RequestBody AtencionMedicaRequest solicitud,
      Authentication auth) {
    return service.registrarAtencionMedica(
      id,
      auth.getName(),
      solicitud.diagnostico(),
      solicitud.indicaciones(),
      solicitud.receta()
    );
  }
}