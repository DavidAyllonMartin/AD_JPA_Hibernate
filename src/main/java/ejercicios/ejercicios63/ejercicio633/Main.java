package ejercicios.ejercicios63.ejercicio633;

import ejercicios.ejercicios63.ejercicio633.entidades.People;
import ejercicios.ejercicios63.ejercicio633.entidades.Planets;
import ejercicios.ejercicios63.ejercicio633.entidades.Species;
import ejercicios.ejercicios63.ejercicio633.excepciones.DataAccessException;
import ejercicios.ejercicios63.ejercicio633.excepciones.DuplicateKeyException;
import ejercicios.ejercicios63.ejercicio633.excepciones.IllegalStarWarsException;
import ejercicios.ejercicios63.ejercicio633.excepciones.IncompatibleVersionException;

import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) throws DataAccessException, DuplicateKeyException, IncompatibleVersionException {
        StarWarsPeopleDAO_JPA dao = new StarWarsPeopleDAO_JPA("ejercicios633_test");

        Planets tatooine = new Planets();
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

        Species human = new Species();

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

        People character1 = null;
        try {
            character1 = new People.Builder()
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
        } catch (IllegalStarWarsException e) {
            throw new RuntimeException(e);
        }

        People character2 = null;
        try {
            character2 = new People.Builder()
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
        } catch (IllegalStarWarsException e) {
            throw new RuntimeException(e);
        }

        dao.create(character1);
        dao.create(character2);
    }
}
