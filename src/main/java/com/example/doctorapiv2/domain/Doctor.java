package com.example.doctorapiv2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String fullname;
}
