package amkz.com.Proximity.api;

import amkz.com.Proximity.model.Places;
import feign.RequestLine;

public interface LocationAPIClient {

    @RequestLine("GET /germany.json")
    Places getLocations();

}
