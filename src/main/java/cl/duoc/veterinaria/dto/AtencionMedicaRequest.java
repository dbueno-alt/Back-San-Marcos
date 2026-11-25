package cl.duoc.veterinaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtencionMedicaRequest(
  @NotBlank(message = "El diagnóstico es obligatorio")
  @Size(max = 1000, message = "El diagnóstico no puede superar 1000 caracteres")
  String diagnostico,

  @NotBlank(message = "Las indicaciones médicas son obligatorias")
  @Size(max = 1500, message = "Las indicaciones no pueden superar 1500 caracteres")
  String indicaciones,

  @Size(max = 1500, message = "La receta no puede superar 1500 caracteres")
  String receta
) {}