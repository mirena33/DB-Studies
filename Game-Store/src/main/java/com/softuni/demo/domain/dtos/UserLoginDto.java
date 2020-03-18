package com.softuni.demo.domain.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginDto {
    private String email;
    private String password;
}
