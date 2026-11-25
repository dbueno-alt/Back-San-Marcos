package cl.duoc.veterinaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.*;

// administrador y veterinario pueden corregir cita

public record CitaAdminRequest( 
    @NotBlank @Size(max = 100) String nombreMascota,
    @NotBlank @Size(max = 50) String especie,
    @NotNull LocalDateTime fechaHora,
    @NotBlank @Size(max = 500) String motivo,
    @Size(max = 999, message ="Las observaciones no pueden superar los 999 caracteres") String obvervaciones
    ){}
