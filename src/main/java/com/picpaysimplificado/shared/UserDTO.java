package com.picpaysimplificado.shared;

import java.math.BigDecimal;

import com.picpaysimplificado.model.user.UserType;

public record UserDTO(
        String firstName, String lastName,
        String document, String email,
        String password, BigDecimal balance, UserType userType) {

}
