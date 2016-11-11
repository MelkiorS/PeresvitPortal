package org.bionic.web.dto;


import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;


/**
 * Created by Alex Sanak on 04.11.2016.
 */
@Data
public class UserDto {

    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;
    @NotNull
    @NotEmpty
    private String middleName;

    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String matchingPassword;

    @NotNull
    @NotEmpty
//    @Column(unique = true)
    private String email;
}
