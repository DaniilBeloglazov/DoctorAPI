package com.example.doctorapiv2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank
    @Length(min = 8)
    private String login;
    @NotBlank
    @Length(min = 8)
    private String password;
}
