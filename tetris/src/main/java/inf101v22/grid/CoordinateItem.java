package inf101v22.grid;

import java.util.Objects;

/**
 * Connecting a coordinate with an item.
 * CoordinateItem works with the generic type 'E'.
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public class CoordinateItem <E> {
    public final Coordinate coordinate;
    public final E item;

    /**
     * Class constructor
     * 
     * @param Coordinate
     * @param Item
     */
    public CoordinateItem(Coordinate Coordinate, E Item) {
        this.coordinate = Coordinate;
        this.item = Item;
    }

    @Override
    public String toString() {
        return "{" +
            " coordinate='" + coordinate + "'" +
            ", item='" + item + "'" +
            " }";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CoordinateItem)) {
            return false;
        }
        CoordinateItem coordinateItem = (CoordinateItem) o;
        return Objects.equals(coordinate, coordinateItem.coordinate) && Objects.equals(item, coordinateItem.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, item);
    }

}
