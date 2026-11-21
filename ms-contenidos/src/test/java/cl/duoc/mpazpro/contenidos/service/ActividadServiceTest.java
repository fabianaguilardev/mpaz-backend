package cl.duoc.mpazpro.contenidos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.duoc.mpazpro.contenidos.dto.ActividadRequest;
import cl.duoc.mpazpro.contenidos.exception.ReglaNegocioException;
import cl.duoc.mpazpro.contenidos.model.Actividad;
import cl.duoc.mpazpro.contenidos.repository.ActividadRepository;
import cl.duoc.mpazpro.contenidos.repository.ObjetivoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ActividadServiceTest {

    @Mock
    private ActividadRepository actividadRepository;

    @Mock
    private ObjetivoRepository objetivoRepository;

    @InjectMocks
    private ActividadService service;

    @Test
    void crear_rechazaActividadConObjetivoInexistente() {
        when(objetivoRepository.existsById(5)).thenReturn(false);
        ActividadRequest request = new ActividadRequest(5, "Sumas", "opcion", "¿2+2?", "4", null, null, null);

        assertThrows(ReglaNegocioException.class, () -> service.crear(request));
        verify(actividadRepository, never()).save(any(Actividad.class));
    }

    @Test
    void crear_guardaAlternativasVaciasCuandoNoVienen() {
        when(objetivoRepository.existsById(1)).thenReturn(true);
        when(actividadRepository.save(any(Actividad.class))).thenAnswer(invocacion -> invocacion.getArgument(0));
        ActividadRequest request = new ActividadRequest(1, "Sumas", "opcion", "¿2+2?", "4", null, null, null);

        Actividad creada = service.crear(request);

        assertEquals("", creada.getAlternativaIncorrecta1());
        assertEquals("4", creada.getRespuestaCorrecta());
    }
}
