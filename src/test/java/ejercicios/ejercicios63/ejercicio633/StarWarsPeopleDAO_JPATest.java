package ejercicios.ejercicios63.ejercicio633;

import ejercicios.ejercicios63.ejercicio633.entidades.People;
import ejercicios.ejercicios63.ejercicio633.entidades.Planets;
import ejercicios.ejercicios63.ejercicio633.entidades.Species;
import ejercicios.ejercicios63.ejercicio633.excepciones.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StarWarsPeopleDAO_JPATest {

    private StarWarsPeopleDAO_JPA dao = new StarWarsPeopleDAO_JPA("ejercicios633_test");

    private Planets tatooine = new Planets();
    {
        tatooine.setId(1);
        tatooine.setName("Tatooine");
        tatooine.setClimate("Arid");
        tatooine.setRotationPeriod(23); // Set the appropriate rotation period
        tatooine.setOrbitalPeriod(304); // Set the appropriate orbital period
        tatooine.setDiameter(10465); // Set the appropriate diameter
        tatooine.setGravity("1 standard"); // Set the appropriate gravity
        tatooine.setTerrain("Desert"); // Set the appropriate terrain
        tatooine.setSurfaceWater("1"); // Set the appropriate surface water
        tatooine.setPopulation(200000L); // Set the appropriate population
        tatooine.setCreated(new Timestamp(System.currentTimeMillis()));
        tatooine.setEdited(new Timestamp(System.currentTimeMillis()));
    }

    private Species human = new Species();

    {
        human.setId(1);
        human.setName("Human");
        human.setAverageHeight("180"); // Asigna la altura promedio de los humanos
        human.setAverageLifespan("70"); // Asigna la esperanza de vida promedio de los humanos
        human.setClassification("Mammal");
        human.setDesignation("Sentient");
        human.setEyeColors("Brown, Blue, Green"); // Asigna los colores de ojos típicos de los humanos
        human.setHairColors("Black, Brown, Blonde"); // Asigna los colores de cabello típicos de los humanos
        human.setHomeworld(1); // Asigna el ID del planeta natal de los humanos (por ejemplo, la Tierra)
        human.setLanguage("Galactic Basic");
        human.setSkinColors("Various"); // Asigna los colores de piel variados de los humanos
        human.setCreated(new Timestamp(System.currentTimeMillis())); // Establece la fecha de creación actual
        human.setEdited(new Timestamp(System.currentTimeMillis())); // Establece la fecha de edición actual

    }

    private People character1 = new People.Builder()
            .id(10000)
            .name("Luke Skywalker")
            .gender("male")
            .birthYear("19BBY")
            .height((short) 172)
            .mass(77)
            .hairColor("blond")
            .skinColor("fair")
            .eyeColor("blue")
            .species(human)
            .homeworld(tatooine)
            .build();

    private People character1Duplicated = new People.Builder()
            .id(10000)
            .name("Luke Skywalker")
            .gender("male")
            .birthYear("19BBY")
            .height((short) 172)
            .mass(77)
            .hairColor("blond")
            .skinColor("fair")
            .eyeColor("blue")
            .species(human)
            .homeworld(tatooine)
            .build();

    private People character2 = new People.Builder()
            .id(10001)
            .name("Anakin Skywalker")
            .gender("male")
            .birthYear("41.9BBY")
            .height((short) 188)
            .mass(84)
            .hairColor("blond")
            .skinColor("fair")
            .eyeColor("blue")
            .homeworld(tatooine)
            .species(human)
            .build();

    @BeforeEach
    void setUp(){
        EntityManager entityManager = dao.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("delete from star_wars_test.people").executeUpdate();
        entityManager.createNativeQuery("delete from star_wars_test.planets").executeUpdate();
        entityManager.createNativeQuery("delete from star_wars_test.species").executeUpdate();
        entityManager.getTransaction().commit();

    }
    @Test
    void create() throws DataAccessException, DuplicateKeyException, IncompatibleVersionException {

        dao.create(character1);
        People readCharacter = dao.read("Luke Skywalker");
        assertNotNull(readCharacter);
        assertEquals("Luke Skywalker", readCharacter.getName());
        assertEquals("19BBY", readCharacter.getBirthYear());
        assertThrows(DuplicateKeyException.class, () -> dao.create(character1Duplicated));
    }

    @Test
    void read() throws IncompatibleVersionException, DataAccessException, DuplicateKeyException {
        People readCharacter = dao.read("Luke Skywalker");
        assertNull(readCharacter);
        dao.create(character1);
        readCharacter = dao.read("Luke Skywalker");
        assertNotNull(readCharacter);
        assertEquals("Luke Skywalker", readCharacter.getName());
    }

    @Test
    void readAll() throws IncompatibleVersionException, DataAccessException, DuplicateKeyException {
        List<People> characters = dao.readAll();
        assertNotNull(characters);
        assertTrue(characters.isEmpty());
        dao.create(character1);
        dao.create(character2);
        characters = dao.readAll();
        assertNotNull(characters);
        assertEquals(2, characters.size());
        assertEquals("Luke Skywalker", characters.get(0).getName());
        assertEquals("Anakin Skywalker", characters.get(1).getName());
    }

    @Test
    void update() throws DataAccessException, DuplicateKeyException, IncompatibleVersionException {
        dao.create(character2);
        People updatedCharacter = new People.Builder()
                .id(10001)
                .name("Anakin Skywalker")
                .gender("male")
                .birthYear("41.9BBY")
                .height((short) 187)
                .mass(85)
                .hairColor("black")
                .skinColor("fair")
                .eyeColor("blue")
                .homeworld(tatooine)
                .species(human)
                .build();

        assertTrue(dao.update(updatedCharacter));
        People readCharacter = null;
        readCharacter = dao.read("Anakin Skywalker");
        assertNotNull(readCharacter);
        assertEquals("black", readCharacter.getHairColor());
    }

    @Test
    void delete() throws DataIntegrityException, DataAccessException, DuplicateKeyException, IncompatibleVersionException {

        assertFalse(dao.delete(character2));
        dao.create(character2);
        assertTrue(dao.delete(character2));
        assertNull(dao.read("Anakin Skywalker"));
    }
}