package cl.duoc.mpazpro.contenidos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** Espejo de ObjetivoAprendizajeEntity de la app (Room). */
@Entity
@Table(name = "objetivos_aprendizaje")
public class Objetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer asignaturaId;

    @Column(nullable = false)
    private String codigoMineduc;

    @Column(nullable = false, length = 1000)
    private String descripcion;

    @Column(nullable = false)
    private Boolean autorizado = false;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getAsignaturaId() { return asignaturaId; }
    public void setAsignaturaId(Integer asignaturaId) { this.asignaturaId = asignaturaId; }
    public String getCodigoMineduc() { return codigoMineduc; }
    public void setCodigoMineduc(String codigoMineduc) { this.codigoMineduc = codigoMineduc; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Boolean getAutorizado() { return autorizado; }
    public void setAutorizado(Boolean autorizado) { this.autorizado = autorizado; }
}
