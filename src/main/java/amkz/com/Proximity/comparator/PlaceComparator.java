package amkz.com.Proximity.comparator;

import amkz.com.Proximity.dto.PlaceDTO;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class PlaceComparator implements Comparator<PlaceDTO> {

    @Override
    public int compare(PlaceDTO o1, PlaceDTO o2) {
        return Double.compare(o1.getDistance(), o2.getDistance());
    }

}
