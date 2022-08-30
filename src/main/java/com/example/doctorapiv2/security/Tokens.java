package com.example.doctorapiv2.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tokens {
    private String accesstoken;
    private String refreshtoken;
}
