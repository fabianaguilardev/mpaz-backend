package cl.duoc.mpazpro.resultados.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** Entidad persistida: espejo de ResultadoPruebaEntity de la app (Room). */
@Entity
@Table(name = "resultados_prueba")
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer estudianteFicticioId;

    @Column(nullable = false)
    private Integer oaId;

    @Column(nullable = false)
    private Integer puntaje;

    @Column(nullable = false)
    private Integer intentosUsados;

    @Column(nullable = false)
    private Long fechaRegistro;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getEstudianteFicticioId() { return estudianteFicticioId; }
    public void setEstudianteFicticioId(Integer v) { this.estudianteFicticioId = v; }
    public Integer getOaId() { return oaId; }
    public void setOaId(Integer oaId) { this.oaId = oaId; }
    public Integer getPuntaje() { return puntaje; }
    public void setPuntaje(Integer puntaje) { this.puntaje = puntaje; }
    public Integer getIntentosUsados() { return intentosUsados; }
    public void setIntentosUsados(Integer v) { this.intentosUsados = v; }
    public Long getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Long v) { this.fechaRegistro = v; }
}
