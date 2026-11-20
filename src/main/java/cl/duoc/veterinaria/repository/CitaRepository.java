package cl.duoc.veterinaria.repository;

import cl.duoc.veterinaria.model.Cita;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
  List<Cita> findByCorreoUsuarioAndArchivadaFalseOrderByFechaHoraDesc(String correoUsuario);

  List<Cita> findByCorreoUsuarioAndArchivadaTrueOrderByFechaHoraDesc(String correoUsuario);

  Optional<Cita> findByIdAndCorreoUsuario(Long id, String correoUsuario);
}
