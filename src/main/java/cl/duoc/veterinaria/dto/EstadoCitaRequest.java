package cl.duoc.veterinaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EstadoCitaRequest(
  @NotBlank
  @Pattern(
    regexp = "CONFIRMADA|ATENDIDA|CANCELADA",
    message = "El estado debe ser CONFIRMADA, ATENDIDA o CANCELADA"
  )
  String estado
) {}
