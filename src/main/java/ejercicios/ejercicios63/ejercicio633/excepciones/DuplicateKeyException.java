package ejercicios.ejercicios63.ejercicio633.excepciones;

public class DuplicateKeyException extends Exception {
    public DuplicateKeyException(){
        super();
    }
    public DuplicateKeyException(String message){
        super(message);
    }
}
