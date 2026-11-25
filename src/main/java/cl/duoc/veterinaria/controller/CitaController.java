package cl.duoc.veterinaria.controller;

import cl.duoc.veterinaria.model.Cita;
import cl.duoc.veterinaria.service.CitaService;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/citas")
public class CitaController {
  private final CitaService service;

  public CitaController(CitaService service) {
    this.service = service;
  }

  @GetMapping
  public List<Cita> listar(Principal usuario) {
    return service.listarPorUsuario(usuario.getName());
  }

  @GetMapping("/historial")
  public List<Cita> historial(Principal usuario) {
    return service.listarArchivadasPorUsuario(usuario.getName());
  }

  @GetMapping("/{id}")
  public Cita buscar(@PathVariable Long id, Principal usuario) {
    return service.buscar(id, usuario.getName());
  }

  @PostMapping
  public ResponseEntity<Cita> crear(@Valid @RequestBody Cita cita, Principal usuario) {
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(service.guardar(cita, usuario.getName()));
  }

  @PutMapping("/{id}")
  public Cita actualizar(@PathVariable Long id,
      @Valid @RequestBody Cita cita, Principal usuario) {
    return service.actualizar(id, usuario.getName(), cita);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void archivar(@PathVariable Long id, Principal usuario) {
    service.archivar(id, usuario.getName());
  }
}
