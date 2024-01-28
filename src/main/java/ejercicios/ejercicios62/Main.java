package ejercicios.ejercicios62;

import ejercicios.ejercicios62.entidades.Localizaciones;
import ejercicios.ejercicios62.entidades.Paises;
import ejercicios.ejercicios62.entidades.Regiones;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ejercicios62");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Regiones region = entityManager.find(Regiones.class, 1);
        Paises pais = entityManager.find(Paises.class, "ES");
        Localizaciones localizacion = entityManager.find(Localizaciones.class, 4000);

        System.out.println(region);
        System.out.println(pais);
        System.out.println(localizacion);

        entityManager.close();
        entityManagerFactory.close();
    }
}
