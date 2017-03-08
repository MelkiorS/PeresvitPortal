package ua.peresvit.dto;


import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import ua.peresvit.util.helper.SocialMediaService;

import javax.validation.constraints.NotNull;


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
    private String password;

    private String profileVK;
    private String profileFB;
    private String profileGoogle;
    private SocialMediaService social;

    @NotNull
    @NotEmpty
//    @Column(unique = true)
    private String email;
}
