package cl.duoc.mpazpro.contenidos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** Espejo de ActividadEntity de la app (Room). oaId referencia a un Objetivo. */
@Entity
@Table(name = "actividades")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer oaId;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String tipoDesafio;

    @Column(nullable = false, length = 1000)
    private String pregunta;

    @Column(nullable = false)
    private String respuestaCorrecta;

    private String alternativaIncorrecta1 = "";
    private String alternativaIncorrecta2 = "";
    private String alternativaIncorrecta3 = "";

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getOaId() { return oaId; }
    public void setOaId(Integer oaId) { this.oaId = oaId; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getTipoDesafio() { return tipoDesafio; }
    public void setTipoDesafio(String tipoDesafio) { this.tipoDesafio = tipoDesafio; }
    public String getPregunta() { return pregunta; }
    public void setPregunta(String pregunta) { this.pregunta = pregunta; }
    public String getRespuestaCorrecta() { return respuestaCorrecta; }
    public void setRespuestaCorrecta(String respuestaCorrecta) { this.respuestaCorrecta = respuestaCorrecta; }
    public String getAlternativaIncorrecta1() { return alternativaIncorrecta1; }
    public void setAlternativaIncorrecta1(String v) { this.alternativaIncorrecta1 = v; }
    public String getAlternativaIncorrecta2() { return alternativaIncorrecta2; }
    public void setAlternativaIncorrecta2(String v) { this.alternativaIncorrecta2 = v; }
    public String getAlternativaIncorrecta3() { return alternativaIncorrecta3; }
    public void setAlternativaIncorrecta3(String v) { this.alternativaIncorrecta3 = v; }
}
