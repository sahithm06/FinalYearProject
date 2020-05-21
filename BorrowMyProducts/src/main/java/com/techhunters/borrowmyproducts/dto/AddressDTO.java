package com.techhunters.borrowmyproducts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private int userAddressId;

    private UserDTO user;

    private String houseNo;

    private String StreetNo;

    private String city;

    private String state;

    private String country;

    private String zipCode;
    private Double latitude;
    private Double longitude;
}
