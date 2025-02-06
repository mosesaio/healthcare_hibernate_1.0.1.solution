package com.healthcaremanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PatientId")
    private int patientId;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(name = "DateOfBirth")
    private String dateOfBirth;

    @Column(name = "Email", unique = true)
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    // One-to-Many relationship with Appointments
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments = new ArrayList<>();

    // Many-to-Many relationship with Doctors (inverse side)
    @ManyToMany(mappedBy = "patients", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Doctor> doctors = new ArrayList<>();
}
