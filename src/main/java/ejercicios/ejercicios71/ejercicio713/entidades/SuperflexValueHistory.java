package ejercicios.ejercicios71.ejercicio713.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "superflex_value_history", schema = "nfl")
public class SuperflexValueHistory implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "date", nullable = false)
    private Date date;
    @Basic
    @Column(name = "value", nullable = false)
    private Integer value;
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Players player;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuperflexValueHistory that = (SuperflexValueHistory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    public Players getPlayer() {
        return player;
    }

    public void setPlayer(Players player) {
        this.player = player;
    }
}
