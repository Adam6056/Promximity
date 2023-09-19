package amkz.com.Proximity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {

    private String city;
    private String countryCode;
    private String street;
    private String zipCode;

}
