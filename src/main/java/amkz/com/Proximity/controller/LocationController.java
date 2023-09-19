package amkz.com.Proximity.controller;

import amkz.com.Proximity.dto.PlaceDTO;
import amkz.com.Proximity.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/location")
    public List<PlaceDTO> getLocations(
            @RequestParam(defaultValue = "") String location,
            @RequestParam(defaultValue = "0.0", required = false) double lat,
            @RequestParam(defaultValue = "0", required = false) double lon
    ) {
        return locationService.searchPlaces(location, lat, lon);
    }

}
