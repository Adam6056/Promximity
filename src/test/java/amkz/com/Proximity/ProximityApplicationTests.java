package amkz.com.Proximity;

import amkz.com.Proximity.dto.PlaceDTO;
import amkz.com.Proximity.model.Place;
import amkz.com.Proximity.model.Places;
import amkz.com.Proximity.service.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProximityApplicationTests {

    @Autowired
    private LocationService locationService;

    @LocalServerPort
    private int port;

    @Test
    void shouldReturnAllPlaces() {
        //when
        List<PlaceDTO> response = new RestTemplate().getForObject("http://localhost:" + port + "/location", List.class);

        //then
        assert response != null && response.size() != 0;
    }

    private Place createTestPlace(String name, double lat, double lon) {
        Place place = new Place();
        place.setName(name);
        place.setLat(lat);
        place.setLon(lon);
        return place;
    }

    @Test
    void shouldReturnFilteredAndSortedList() {
        //given
        Places places = new Places();
        Place place1 = createTestPlace("Place 1", 99.0, 99.0);
        Place place2 = createTestPlace("Place 2", 10.0, 10.0);
        Place place3 = createTestPlace("Castle", 0.0, 0.0);
        places.setPlaces(new ArrayList<>() {{
            add(place1);
            add(place2);
            add(place3);
        }});

        //when
        List<PlaceDTO> result = locationService.sortAndFilterPlaces(places, "Place", 0.0, 0.0);

        //then
        assert result.size() == 2;
        assert result.get(0).getName().equals("Place 2");
    }

}
