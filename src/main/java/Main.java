import entidades.taller.Categoria;
import entidades.taller.Pieza;
import entidades.taller.Proveedor;
import excepciones.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        try {
            Categoria[] categorias = new Categoria[3];
            categorias[0] = new Categoria();
            categorias[0].setNombre("Electrónicos");

            categorias[1] = new Categoria();
            categorias[1].setNombre("Ropa");

            categorias[2] = new Categoria();
            categorias[2].setNombre("Muebles");

            Proveedor[] proveedores = new Proveedor[4];
            proveedores[0] = new Proveedor("123456789", "Proveedor1", "Provincia1", "Ciudad1");
            proveedores[1] = new Proveedor("987654321", "Proveedor2", "Provincia2", "Ciudad2");
            proveedores[2] = new Proveedor("111222333", "Proveedor3", "Provincia3", "Ciudad3");
            proveedores[3] = new Proveedor("444555666", "Proveedor4", "Provincia4", "Ciudad4");

            Pieza[] piezas = new Pieza[7];
            piezas[0] = new Pieza(19.99, "Pieza1", "Rojo");
            piezas[1] = new Pieza(29.99, "Pieza2", "Verde");
            piezas[2] = new Pieza(39.99, "Pieza3", "Azul");
            piezas[3] = new Pieza(9.99, "Pieza4", "Negro");
            piezas[4] = new Pieza(49.99, "Pieza5", "Blanco");
            piezas[5] = new Pieza(14.99, "Pieza6", "Amarillo");
            piezas[6] = new Pieza(24.99, "Pieza7", "Gris");

            try {
                Categoria categoriaIncorrecta = new Categoria();
                categoriaIncorrecta.setCodigo(-1);  // Código incorrecto
            } catch (CodigoIlegalException e) {
                System.out.println("Excepción al intentar establecer un código de categoría incorrecto.");
            }

            try {
                Proveedor proveedorIncorrecto = new Proveedor("123", "", "Provincia", "Ciudad");
            } catch (TelefonoIlegalException | NombreIlegalException | ProvinciaIlegalException | CiudadIlegalException e) {
                System.out.println("Excepción al intentar crear un proveedor con datos incorrectos.");
            }

            try {
                Pieza piezaIncorrecta = new Pieza(-1, 15.0, "PiezaIncorrecta", "Rojo");
            } catch (CodigoIlegalException | PrecioIlegalException | NombreIlegalException | ColorIlegalException e) {
                System.out.println("Excepción al intentar crear una pieza con datos incorrectos.");
            }

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            for (Categoria categoria : categorias){
                guardarObjeto(categoria, entityManager);
            }

            for (Pieza pieza : piezas){
                guardarObjeto(pieza, entityManager);
            }

            for (Proveedor proveedor : proveedores){
                guardarObjeto(proveedor, entityManager);
            }

            modificarProveedor(entityManager);

            eliminarProveedor(entityManager);

            entityManager.close();
            entityManagerFactory.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void modificarProveedor(EntityManager entityManager) throws CiudadIlegalException, ProvinciaIlegalException {
        Proveedor proveedorAModificar = entityManager.find(Proveedor.class, 1);

        if (proveedorAModificar != null) {
            System.out.println("Proveedor antes de la modificación: " + proveedorAModificar);

            entityManager.getTransaction().begin();
            proveedorAModificar.setCiudad("Madrid");
            proveedorAModificar.setProvincia("Madrid");
            entityManager.getTransaction().commit();

            System.out.println("Proveedor después de la modificación: " + proveedorAModificar);
        } else {
            System.out.println("Proveedor no encontrado.");
        }
    }

    private static void eliminarProveedor(EntityManager entityManager){
        Proveedor proveedorAEliminar = entityManager.find(Proveedor.class, 2);
        if (proveedorAEliminar != null) {
            System.out.println("Proveedor antes de la eliminación: " + proveedorAEliminar);

            entityManager.getTransaction().begin();
            entityManager.remove(proveedorAEliminar);
            entityManager.getTransaction().commit();

        } else {
            System.out.println("Proveedor no encontrado.");
        }
    }

    public static void guardarObjeto(Object object, EntityManager entityManager){
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }
}
