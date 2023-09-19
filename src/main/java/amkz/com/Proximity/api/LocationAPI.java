package amkz.com.Proximity.api;

import amkz.com.Proximity.model.Places;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LocationAPI {

    private LocationAPIClient api;

    public LocationAPI(@Value("${location.api.url}") String locationUrl) {
        this.api = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(LocationAPIClient.class))
                .logLevel(Logger.Level.FULL)
                .target(LocationAPIClient.class, locationUrl);
    }

    public Places getLocations() {
        return api.getLocations();
    }

}
