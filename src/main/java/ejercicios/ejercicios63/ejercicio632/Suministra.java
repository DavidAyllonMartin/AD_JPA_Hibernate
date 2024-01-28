package ejercicios.ejercicios63.ejercicio632;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class Suministra {
    @Id
    long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_proveedor", referencedColumnName = "codigo")
    private Proveedor proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_pieza", referencedColumnName = "codigo")
    private Pieza pieza;
    private int cantidad;
    private LocalDate fecha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


}
