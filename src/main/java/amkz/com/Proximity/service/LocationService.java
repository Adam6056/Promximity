package amkz.com.Proximity.service;

import amkz.com.Proximity.api.LocationAPI;
import amkz.com.Proximity.comparator.PlaceComparator;
import amkz.com.Proximity.dto.AddressDTO;
import amkz.com.Proximity.dto.PlaceDTO;
import amkz.com.Proximity.model.Place;
import amkz.com.Proximity.model.Places;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.SloppyMath;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationAPI api;
    private final PlaceComparator placeComparator;

    public List<PlaceDTO> searchPlaces(String location, double lat, double lon) {
        return sortAndFilterPlaces(api.getLocations(), location, lat, lon);
    }

    public PlaceDTO calculateDistanceAndConvert(Place place, double lat, double lon) {
        return new PlaceDTO(
                place.getName(),
                new AddressDTO(place.getCity(), place.getCountryCode(), place.getStreet(), place.getZipCode()),
                SloppyMath.haversinMeters(place.getLat(), place.getLon(), lat, lon)
        );
    }

    public List<PlaceDTO> sortAndFilterPlaces(Places places, String location, double lat, double lon) {
        return places.getPlaces().stream()
                .filter(place -> place.getName().contains(location))
                .map(place -> calculateDistanceAndConvert(place, lat, lon))
                .sorted(placeComparator)
                .collect(Collectors.toCollection(LinkedList::new));
    }

}
