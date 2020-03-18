package com.softuni.demo.domain.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterDto {

    @Pattern(regexp = "[a-z0-9]+@[a-z]+\\..+", message = "Email is not valid.")
    private String email;

    @Pattern(regexp = "[A-Z]+[a-z]+[0-9]+", message = "Password is not valid.")
    @Size(min = 6, message = "Password length is not valid.")
    private String password;

    @NotNull(message = "Name cannot be null.")
    private String fullName;
}
