package cl.duoc.mpazpro.resultados.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
}
