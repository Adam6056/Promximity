package amkz.com.Proximity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

@Data
@AllArgsConstructor
public class PlaceDTO implements Comparable<PlaceDTO> {

    private String name;
    private AddressDTO address;
    private double distance;

    @Override
    public int compareTo(@NotNull PlaceDTO o) {
        return Comparator.comparingDouble(PlaceDTO::getDistance)
                .thenComparingDouble(PlaceDTO::getDistance).compare(this, o);
    }

}
