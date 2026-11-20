package cl.duoc.veterinaria.repository;

import cl.duoc.veterinaria.model.Cita;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
  List<Cita> findByUsuarioIdOrderByFechaHoraDesc(Long usuarioId);
}
