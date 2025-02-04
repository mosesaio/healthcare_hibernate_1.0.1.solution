package com.healthcaremanagement.service;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.repository.AppointmentRepositoryImpl;


import java.util.List;

public class AppointmentService {

    private final AppointmentRepositoryImpl doctorRepository;

    public AppointmentService(AppointmentRepositoryImpl doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void createAppointment(Appointment doctor) {
        doctorRepository.createAppointment(doctor);
    }

    public Appointment getAppointmentById(int id) {
        return doctorRepository.getAppointmentById(id);
    }

    public List<Appointment> getAllAppointments() {
        return doctorRepository.getAllAppointments();
    }

    public void updateAppointment(Appointment doctor) {
        doctorRepository.updateAppointment(doctor);
    }

    public void deleteAppointment(int id) {
        doctorRepository.deleteAppointment(id);
    }
}