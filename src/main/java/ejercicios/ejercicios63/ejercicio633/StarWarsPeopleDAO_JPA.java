package ejercicios.ejercicios63.ejercicio633;

import ejercicios.ejercicios63.ejercicio633.entidades.People;
import ejercicios.ejercicios63.ejercicio633.excepciones.DataAccessException;
import ejercicios.ejercicios63.ejercicio633.excepciones.DataIntegrityException;
import ejercicios.ejercicios63.ejercicio633.excepciones.DuplicateKeyException;
import ejercicios.ejercicios63.ejercicio633.excepciones.IncompatibleVersionException;
import jakarta.persistence.*;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

public class StarWarsPeopleDAO_JPA implements StarWarsPeopleDAO{

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public StarWarsPeopleDAO_JPA(String persistanceUnit){
        setEntityManagerFactory(persistanceUnit);
        setEntityManager(getEntityManagerFactory());
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void setEntityManagerFactory(String persistanceUnit){
        this.entityManagerFactory = Persistence.createEntityManagerFactory(persistanceUnit);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setEntityManager(EntityManagerFactory entityManagerFactory){
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void create(People character) throws DataAccessException, DuplicateKeyException {
        try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(character);

            entityManager.getTransaction().commit();
        }catch (EntityExistsException e){
            throw new DuplicateKeyException();
        }catch (Exception e){
            throw new DataAccessException();
        }
    }

    @Override
    public People read(String name) throws DataAccessException, IncompatibleVersionException {
        People character = null;
        try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();

            Query query = entityManager.createNativeQuery("SELECT id FROM people WHERE name = ?1");
            query.setParameter(1, name);
            int id = (int) query.getSingleResult();

            character = entityManager.find(People.class, id);

            entityManager.getTransaction().commit();
        }catch (NoResultException e){
            entityManager.getTransaction().rollback();
        }catch (Exception e){
            throw new DataAccessException();
        }
        return character;
    }

    @Override
    public List<People> readAll() throws DataAccessException, IncompatibleVersionException {
        List<People> people = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            Query query = getEntityManager().createNativeQuery("SELECT * FROM people", People.class);
            people = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw new DataAccessException();
        }

        return people;
    }

    @Override
    public boolean update(People character) throws DataAccessException {
        EntityManager entityManager = getEntityManager();
        People previousCharacter = null;
        try {
            entityManager.getTransaction().begin();
            People aux = entityManager.find(People.class, character.getId());
            previousCharacter = new People.Builder().id(aux.getId()).name(aux.getName()).gender(aux.getGender()).birthYear(aux.getBirthYear()).height(aux.getHeight()).mass(aux.getMass()).hairColor(aux.getHairColor()).skinColor(aux.getSkinColor()).eyeColor(aux.getEyeColor()).homeworld(aux.getHomeworld()).species(aux.getSpecies()).created(aux.getCreated()).edited(aux.getEdited()).starships(aux.getStarships()).films(aux.getFilms()).vehicles(aux.getVehicles()).build();
            entityManager.merge(character);

            entityManager.getTransaction().commit();
        }catch (Exception e){
            throw new DataAccessException();
        }
        return !character.equals(previousCharacter);
    }

    @Override
    public boolean delete(People character) throws DataAccessException, DataIntegrityException {
        boolean isDeleted = false;
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        if (entityManager.contains(character)){
            entityManager.remove(character);
            isDeleted = true;
        }
        entityManager.getTransaction().commit();
        return isDeleted;
    }

    public void close(){
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory();
        EntityManager entityManager = getEntityManager();
        entityManager.close();
        entityManagerFactory.close();
    }
}
