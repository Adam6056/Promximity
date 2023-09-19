package amkz.com.Proximity.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Place {

    private int id;
    private double lat;
    private double lon;
    private String name;
    private String partnerId;
    private String placeId;
    private boolean wifiCheckinEnabled;
    private ArrayList<Wifi> wifis;
    private String city;
    private String countryCode;
    private String street;
    private String zipCode;
    private ArrayList<String> features;

}
