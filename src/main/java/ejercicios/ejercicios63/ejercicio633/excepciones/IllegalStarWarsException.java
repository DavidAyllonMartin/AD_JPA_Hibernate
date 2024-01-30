package ejercicios.ejercicios63.ejercicio633.excepciones;

public class IllegalStarWarsException extends Exception {
    public IllegalStarWarsException() {
        super();
    }

    public IllegalStarWarsException(String message) {
        super(message);
    }

    public IllegalStarWarsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalStarWarsException(Throwable cause) {
        super(cause);
    }
}
