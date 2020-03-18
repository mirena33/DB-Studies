package com.softuni.demo.domain.dtos;

import com.softuni.demo.domain.entities.Role;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String email;
    private String fullName;
    private String password;
    private Role role;
}
