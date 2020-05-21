package com.techhunters.borrowmyproducts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {


    private int userId;

    private String userFirstName;

    private String userLastName;

    private String password;

    private String confirmPassword;

    private String phone;

    private String email;

    private AddressDTO address;


}
