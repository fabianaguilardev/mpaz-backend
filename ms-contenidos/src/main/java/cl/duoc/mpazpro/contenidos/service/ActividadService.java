package cl.duoc.mpazpro.contenidos.service;

import cl.duoc.mpazpro.contenidos.dto.ActividadRequest;
import cl.duoc.mpazpro.contenidos.exception.RecursoNoEncontradoException;
import cl.duoc.mpazpro.contenidos.exception.ReglaNegocioException;
import cl.duoc.mpazpro.contenidos.model.Actividad;
import cl.duoc.mpazpro.contenidos.repository.ActividadRepository;
import cl.duoc.mpazpro.contenidos.repository.ObjetivoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ActividadService {

    private final ActividadRepository actividadRepository;
    private final ObjetivoRepository objetivoRepository;

    public ActividadService(ActividadRepository actividadRepository, ObjetivoRepository objetivoRepository) {
        this.actividadRepository = actividadRepository;
        this.objetivoRepository = objetivoRepository;
    }

    public List<Actividad> listar(Integer oaId) {
        if (oaId != null) {
            return actividadRepository.findByOaId(oaId);
        }
        return actividadRepository.findAll();
    }

    public Actividad obtener(Integer id) {
        return actividadRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("La actividad " + id + " no existe"));
    }

    public Actividad crear(ActividadRequest request) {
        validarObjetivo(request.oaId());
        return actividadRepository.save(copiarDatos(new Actividad(), request));
    }

    public Actividad actualizar(Integer id, ActividadRequest request) {
        Actividad existente = obtener(id);
        validarObjetivo(request.oaId());
        return actividadRepository.save(copiarDatos(existente, request));
    }

    public void eliminar(Integer id) {
        actividadRepository.delete(obtener(id));
    }

    /** Regla de negocio: toda actividad debe pertenecer a un objetivo existente. */
    private void validarObjetivo(Integer oaId) {
        if (!objetivoRepository.existsById(oaId)) {
            throw new ReglaNegocioException("El objetivo " + oaId + " no existe");
        }
    }

    private Actividad copiarDatos(Actividad destino, ActividadRequest request) {
        destino.setOaId(request.oaId());
        destino.setTitulo(request.titulo());
        destino.setTipoDesafio(request.tipoDesafio());
        destino.setPregunta(request.pregunta());
        destino.setRespuestaCorrecta(request.respuestaCorrecta());
        destino.setAlternativaIncorrecta1(textoOVacio(request.alternativaIncorrecta1()));
        destino.setAlternativaIncorrecta2(textoOVacio(request.alternativaIncorrecta2()));
        destino.setAlternativaIncorrecta3(textoOVacio(request.alternativaIncorrecta3()));
        return destino;
    }

    private String textoOVacio(String texto) {
        return texto == null ? "" : texto;
    }
}
