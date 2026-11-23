package cl.duoc.mpazpro.resultados.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.duoc.mpazpro.resultados.dto.ResultadoRequest;
import cl.duoc.mpazpro.resultados.exception.RecursoNoEncontradoException;
import cl.duoc.mpazpro.resultados.model.Resultado;
import cl.duoc.mpazpro.resultados.repository.ResultadoRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ResultadoServiceTest {

    @Mock
    private ResultadoRepository repository;

    @InjectMocks
    private ResultadoService service;

    @Test
    void obtener_lanzaExcepcionSiNoExiste() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class, () -> service.obtener(99));
    }

    @Test
    void crear_guardaLosDatosRecibidos() {
        when(repository.save(any(Resultado.class))).thenAnswer(invocacion -> invocacion.getArgument(0));

        Resultado creado = service.crear(new ResultadoRequest(1, 2, 80, 1, 1700000000000L));

        assertEquals(Integer.valueOf(80), creado.getPuntaje());
        assertEquals(Integer.valueOf(2), creado.getOaId());
        verify(repository).save(any(Resultado.class));
    }

    @Test
    void actualizar_modificaLosDatosDelResultadoExistente() {
        Resultado existente = new Resultado();
        existente.setId(5);
        existente.setPuntaje(10);
        when(repository.findById(5)).thenReturn(Optional.of(existente));
        when(repository.save(any(Resultado.class))).thenAnswer(invocacion -> invocacion.getArgument(0));

        Resultado actualizado = service.actualizar(5, new ResultadoRequest(1, 2, 95, 2, 1700000000000L));

        assertEquals(Integer.valueOf(5), actualizado.getId());
        assertEquals(Integer.valueOf(95), actualizado.getPuntaje());
        assertEquals(Integer.valueOf(2), actualizado.getIntentosUsados());
        verify(repository).save(existente);
    }

    @Test
    void actualizar_lanzaExcepcionYNoGuardaSiNoExiste() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class,
                () -> service.actualizar(99, new ResultadoRequest(1, 2, 95, 2, 1700000000000L)));

        verify(repository, never()).save(any(Resultado.class));
    }

    @Test
    void eliminar_borraElResultadoExistente() {
        Resultado existente = new Resultado();
        existente.setId(7);
        when(repository.findById(7)).thenReturn(Optional.of(existente));

        service.eliminar(7);

        verify(repository).delete(existente);
    }

    @Test
    void eliminar_lanzaExcepcionYNoBorraSiNoExiste() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class, () -> service.eliminar(99));

        verify(repository, never()).delete(any(Resultado.class));
    }
}
