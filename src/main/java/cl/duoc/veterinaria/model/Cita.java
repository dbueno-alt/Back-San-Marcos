package cl.duoc.veterinaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
public class Cita {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "correo_usuario", nullable = false)
  private String correoUsuario;

  @NotBlank
  @Size(max = 100, message = "El nombre de la mascota no puede superar 100 caracteres")
  @Column(name = "nombre_mascota", nullable = false)
  private String nombreMascota;

  @NotBlank
  @Size(max = 50, message = "La especie no puede superar 50 caracteres")
  @Column(nullable = false)
  private String especie;

  @NotNull
  @Future(message = "La fecha y hora deben ser futuras")
  @Column(name = "fecha_hora", nullable = false)
  private LocalDateTime fechaHora;

  @NotBlank
  @Size(max = 500, message = "El motivo no puede superar 500 caracteres")
  @Column(nullable = false)
  private String motivo;

  @Column(nullable = false)
  private String estado;

  @Column(nullable = false)
  private boolean archivada;

  @Column(name = "recordatorio_solicitado", nullable = false)
  private boolean recordatorioSolicitado;

  @Size(max = 999, message = "Las observaciones no pueden superar los 999 caracteres")
  @Column(length=999)

  private String observaciones;

  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCorreoUsuario() {
    return correoUsuario;
  }

  public void setCorreoUsuario(String correoUsuario) {
    this.correoUsuario = correoUsuario;
  }

  public String getNombreMascota() {
    return nombreMascota;
  }

  public void setNombreMascota(String nombreMascota) {
    this.nombreMascota = nombreMascota;
  }

  public String getEspecie() {
    return especie;
  }

  public void setEspecie(String especie) {
    this.especie = especie;
  }

  public LocalDateTime getFechaHora() {
    return fechaHora;
  }

  public void setFechaHora(LocalDateTime fechaHora) {
    this.fechaHora = fechaHora;
  }

  public String getMotivo() {
    return motivo;
  }

  public void setMotivo(String motivo) {
    this.motivo = motivo;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public boolean isArchivada() {
    return archivada;
  }

  public void setArchivada(boolean archivada) {
    this.archivada = archivada;
  }

  public boolean isRecordatorioSolicitado() {
    return recordatorioSolicitado;
  }

  public void setRecordatorioSolicitado(boolean recordatorioSolicitado) {
    this.recordatorioSolicitado = recordatorioSolicitado;
  }
}
