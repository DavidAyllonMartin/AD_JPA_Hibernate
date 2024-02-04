package ejercicios.ejercicios71.ejercicio712;

import ejercicios.ejercicios63.ejercicio633.entidades.People;
import ejercicios.ejercicios63.ejercicio633.entidades.Planets;

import java.util.Objects;

public class CharacterPlanet {
    private People character;
    private Planets homeworld;

    public CharacterPlanet(People character, Planets homeworld) {
        this.character = character;
        this.homeworld = homeworld;
    }

    public People getCharacter() {
        return character;
    }

    public void setCharacter(People character) {
        this.character = character;
    }

    public Planets getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(Planets homeworld) {
        this.homeworld = homeworld;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterPlanet that = (CharacterPlanet) o;

        if (!Objects.equals(character, that.character)) return false;
        return Objects.equals(homeworld, that.homeworld);
    }

    @Override
    public int hashCode() {
        int result = character != null ? character.hashCode() : 0;
        result = 31 * result + (homeworld != null ? homeworld.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CharacterPlanet{");
        sb.append("character=").append(character.getName());
        sb.append(", homeworld=").append(homeworld.getName());
        sb.append('}');
        return sb.toString();
    }
}
