package ejercicios.ejercicios63.ejercicio633;

import ejercicios.ejercicios63.ejercicio633.entidades.People;
import ejercicios.ejercicios63.ejercicio633.excepciones.*;

import java.util.List;

public interface StarWarsPeopleDAO {
    void create(People character) throws DataAccessException, DuplicateKeyException;

    People read(String name) throws DataAccessException, IncompatibleVersionException;

    List<People> readAll() throws DataAccessException, IncompatibleVersionException;

    boolean update(People character) throws DataAccessException;

    boolean delete(People character) throws DataAccessException, DataIntegrityException;
}

