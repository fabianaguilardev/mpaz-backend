package cl.duoc.mpazpro.contenidos.exception;

/** Se lanza cuando una operación incumple una regla del dominio. */
public class ReglaNegocioException extends RuntimeException {
    public ReglaNegocioException(String mensaje) {
        super(mensaje);
    }
}
