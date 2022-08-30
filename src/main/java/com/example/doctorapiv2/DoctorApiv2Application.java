package com.example.doctorapiv2;

import com.example.doctorapiv2.dto.request.SignUpRequest;
import com.example.doctorapiv2.service.DoctorService;
import com.example.doctorapiv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class DoctorApiv2Application implements CommandLineRunner {
    private final UserService userService;
    private final DoctorService doctorService;
    public static void main(String[] args) {
        SpringApplication.run(DoctorApiv2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userService.save(new SignUpRequest("adminLogin", "Admin123"));
    }
}
