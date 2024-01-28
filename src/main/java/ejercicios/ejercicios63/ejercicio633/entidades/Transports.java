package ejercicios.ejercicios63.ejercicio633.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "transports", schema = "star_wars")
public class Transports implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 80)
    private String name;
    @Basic
    @Column(name = "manufacturer", nullable = true, length = -1)
    private String manufacturer;
    @Basic
    @Column(name = "model", nullable = true, length = -1)
    private String model;
    @Basic
    @Column(name = "cargo_capacity", nullable = true, length = -1)
    private String cargoCapacity;
    @Basic
    @Column(name = "consumables", nullable = true, length = -1)
    private String consumables;
    @Basic
    @Column(name = "cost_in_credits", nullable = true, length = -1)
    private String costInCredits;
    @Basic
    @Column(name = "crew", nullable = true, length = -1)
    private String crew;
    @Basic
    @Column(name = "length", nullable = true, length = -1)
    private String length;
    @Basic
    @Column(name = "max_atmosphering_speed", nullable = true, length = -1)
    private String maxAtmospheringSpeed;
    @Basic
    @Column(name = "passengers", nullable = true, length = -1)
    private String passengers;
    @Basic
    @Column(name = "created", nullable = true)
    private Timestamp created;
    @Basic
    @Column(name = "edited", nullable = true)
    private Timestamp edited;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(String cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public String getConsumables() {
        return consumables;
    }

    public void setConsumables(String consumables) {
        this.consumables = consumables;
    }

    public String getCostInCredits() {
        return costInCredits;
    }

    public void setCostInCredits(String costInCredits) {
        this.costInCredits = costInCredits;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getMaxAtmospheringSpeed() {
        return maxAtmospheringSpeed;
    }

    public void setMaxAtmospheringSpeed(String maxAtmospheringSpeed) {
        this.maxAtmospheringSpeed = maxAtmospheringSpeed;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getEdited() {
        return edited;
    }

    public void setEdited(Timestamp edited) {
        this.edited = edited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transports that = (Transports) o;

        if (id != that.id) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(manufacturer, that.manufacturer)) return false;
        if (!Objects.equals(model, that.model)) return false;
        if (!Objects.equals(cargoCapacity, that.cargoCapacity))
            return false;
        if (!Objects.equals(consumables, that.consumables)) return false;
        if (!Objects.equals(costInCredits, that.costInCredits))
            return false;
        if (!Objects.equals(crew, that.crew)) return false;
        if (!Objects.equals(length, that.length)) return false;
        if (!Objects.equals(maxAtmospheringSpeed, that.maxAtmospheringSpeed))
            return false;
        if (!Objects.equals(passengers, that.passengers)) return false;
        if (!Objects.equals(created, that.created)) return false;
        return Objects.equals(edited, that.edited);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (cargoCapacity != null ? cargoCapacity.hashCode() : 0);
        result = 31 * result + (consumables != null ? consumables.hashCode() : 0);
        result = 31 * result + (costInCredits != null ? costInCredits.hashCode() : 0);
        result = 31 * result + (crew != null ? crew.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (maxAtmospheringSpeed != null ? maxAtmospheringSpeed.hashCode() : 0);
        result = 31 * result + (passengers != null ? passengers.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (edited != null ? edited.hashCode() : 0);
        return result;
    }
}
