package cl.duoc.mpazpro.contenidos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/** Datos de entrada de una actividad. No incluye id: lo genera el servidor. */
public record ActividadRequest(
        @NotNull @Positive Integer oaId,
        @NotBlank String titulo,
        @NotBlank String tipoDesafio,
        @NotBlank String pregunta,
        @NotBlank String respuestaCorrecta,
        String alternativaIncorrecta1,
        String alternativaIncorrecta2,
        String alternativaIncorrecta3
) {
}
