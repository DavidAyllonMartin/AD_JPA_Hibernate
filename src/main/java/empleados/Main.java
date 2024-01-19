package empleados;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("empleados");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Regiones region = entityManager.find(Regiones.class, 1);

        for (Paises pais : region.getPaises()){
            System.out.println(pais.getNombrePais());
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
