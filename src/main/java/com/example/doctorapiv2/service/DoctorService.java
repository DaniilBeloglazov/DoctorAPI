package com.example.doctorapiv2.service;

import com.example.doctorapiv2.domain.Doctor;
import com.example.doctorapiv2.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }
}
