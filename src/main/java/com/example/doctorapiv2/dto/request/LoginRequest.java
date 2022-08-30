package com.example.doctorapiv2.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank
    @Length(min = 8)
    private String login;
    @NotBlank
    @Length(min = 8)
    private String password;
}
