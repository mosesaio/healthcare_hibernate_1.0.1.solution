package com.healthcaremanagement;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;
import com.healthcaremanagement.repository.AppointmentRepositoryImpl;
import com.healthcaremanagement.repository.DoctorRepositoryImpl;
import com.healthcaremanagement.repository.PatientRepositoryImpl;
import com.healthcaremanagement.service.AppointmentService;
import com.healthcaremanagement.service.DoctorService;
import com.healthcaremanagement.service.PatientService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Build the SessionFactory (assumes a common config for all entities)
        SessionFactory sessionFactory = new Configuration().configure("patient.cfg.xml").buildSessionFactory();

        // Initialize repositories and services for each entity
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(sessionFactory);
        PatientService patientService = new PatientService(patientRepository);

        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl(sessionFactory);
        DoctorService doctorService = new DoctorService(doctorRepository);

        AppointmentRepositoryImpl appointmentRepository = new AppointmentRepositoryImpl(sessionFactory);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n====== Main Menu ======");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Manage Appointments");
            System.out.println("4. Exit");
            int mainChoice = readInt(scanner, "Enter your choice: ");

            switch (mainChoice) {
                case 1:
                    managePatients(patientService, scanner);
                    break;
                case 2:
                    manageDoctors(doctorService, scanner);
                    break;
                case 3:
                    manageAppointments(appointmentService, scanner);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
        sessionFactory.close();
    }

    // Helper method to prompt and read an integer from the console
    private static int readInt(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                scanner.nextLine(); // consume newline
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // clear invalid input
            }
        }
        return value;
    }

    // Method for Patient management
    private static void managePatients(PatientService patientService, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Patient Management ---");
            System.out.println("1. Create Patient");
            System.out.println("2. Read Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Back to Main Menu");
            int choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    Patient newPatient = new Patient();
                    System.out.print("Enter first name: ");
                    newPatient.setFirstName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    newPatient.setLastName(scanner.nextLine());
                    System.out.print("Enter date of birth (YYYY-MM-DD): ");
                    newPatient.setDateOfBirth(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newPatient.setEmail(scanner.nextLine());
                    System.out.print("Enter phone number: ");
                    newPatient.setPhoneNumber(scanner.nextLine());
                    patientService.createPatient(newPatient);
                    System.out.println("Patient created successfully.");
                    break;
                case 2:
                    int patientId = readInt(scanner, "Enter Patient ID: ");
                    Patient patient = patientService.getPatientById(patientId);
                    if (patient != null) {
                        System.out.println("Patient ID: " + patient.getPatientId());
                        System.out.println("Name: " + patient.getFirstName() + " " + patient.getLastName());
                        System.out.println("Date of Birth: " + patient.getDateOfBirth());
                        System.out.println("Email: " + patient.getEmail());
                        System.out.println("Phone: " + patient.getPhoneNumber());
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 3:
                    patientId = readInt(scanner, "Enter Patient ID: ");
                    patient = patientService.getPatientById(patientId);
                    if (patient != null) {
                        System.out.print("Enter new first name: ");
                        patient.setFirstName(scanner.nextLine());
                        System.out.print("Enter new last name: ");
                        patient.setLastName(scanner.nextLine());
                        System.out.print("Enter new date of birth (YYYY-MM-DD): ");
                        patient.setDateOfBirth(scanner.nextLine());
                        System.out.print("Enter new email: ");
                        patient.setEmail(scanner.nextLine());
                        System.out.print("Enter new phone number: ");
                        patient.setPhoneNumber(scanner.nextLine());
                        patientService.updatePatient(patient);
                        System.out.println("Patient updated successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 4:
                    patientId = readInt(scanner, "Enter Patient ID: ");
                    patientService.deletePatient(patientId);
                    System.out.println("Patient deleted successfully.");
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Method for Doctor management
    private static void manageDoctors(DoctorService doctorService, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Doctor Management ---");
            System.out.println("1. Create Doctor");
            System.out.println("2. Read Doctor");
            System.out.println("3. Update Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("5. Back to Main Menu");
            int choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    Doctor newDoctor = new Doctor();
                    System.out.print("Enter first name: ");
                    newDoctor.setFirstName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    newDoctor.setLastName(scanner.nextLine());
                    System.out.print("Enter specialty: ");
                    newDoctor.setSpecialty(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newDoctor.setEmail(scanner.nextLine());
                    doctorService.createDoctor(newDoctor);
                    System.out.println("Doctor created successfully.");
                    break;
                case 2:
                    int doctorId = readInt(scanner, "Enter Doctor ID: ");
                    Doctor doctor = doctorService.getDoctorById(doctorId);
                    if (doctor != null) {
                        System.out.println("Doctor ID: " + doctor.getDoctorId());
                        System.out.println("Name: " + doctor.getFirstName() + " " + doctor.getLastName());
                        System.out.println("Specialty: " + doctor.getSpecialty());
                        System.out.println("Email: " + doctor.getEmail());
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 3:
                    doctorId = readInt(scanner, "Enter Doctor ID: ");
                    doctor = doctorService.getDoctorById(doctorId);
                    if (doctor != null) {
                        System.out.print("Enter new first name: ");
                        doctor.setFirstName(scanner.nextLine());
                        System.out.print("Enter new last name: ");
                        doctor.setLastName(scanner.nextLine());
                        System.out.print("Enter new specialty: ");
                        doctor.setSpecialty(scanner.nextLine());
                        System.out.print("Enter new email: ");
                        doctor.setEmail(scanner.nextLine());
                        doctorService.updateDoctor(doctor);
                        System.out.println("Doctor updated successfully.");
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 4:
                    doctorId = readInt(scanner, "Enter Doctor ID to delete: ");
                    doctorService.deleteDoctor(doctorId);
                    System.out.println("Doctor deleted successfully.");
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Method for Appointment management
    private static void manageAppointments(AppointmentService appointmentService, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Appointment Management ---");
            System.out.println("1. Create Appointment");
            System.out.println("2. Read Appointment");
            System.out.println("3. Update Appointment");
            System.out.println("4. Delete Appointment");
            System.out.println("5. Back to Main Menu");
            int choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    Appointment newAppointment = new Appointment();
                    newAppointment.setPatientId(readInt(scanner, "Enter Patient ID: "));
                    newAppointment.setDoctorId(readInt(scanner, "Enter Doctor ID: "));
                    System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                    newAppointment.setAppointmentDate(scanner.nextLine());
                    System.out.print("Enter Notes: ");
                    newAppointment.setNotes(scanner.nextLine());
                    appointmentService.createAppointment(newAppointment);
                    System.out.println("Appointment created successfully.");
                    break;
                case 2:
                    int appointmentId = readInt(scanner, "Enter Appointment ID: ");
                    Appointment appointment = appointmentService.getAppointmentById(appointmentId);
                    if (appointment != null) {
                        System.out.println("Appointment ID: " + appointment.getAppointmentId());
                        System.out.println("Patient ID: " + appointment.getPatientId());
                        System.out.println("Doctor ID: " + appointment.getDoctorId());
                        System.out.println("Appointment Date: " + appointment.getAppointmentDate());
                        System.out.println("Notes: " + appointment.getNotes());
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                case 3:
                    appointmentId = readInt(scanner, "Enter Appointment ID: ");
                    appointment = appointmentService.getAppointmentById(appointmentId);
                    if (appointment != null) {
                        appointment.setPatientId(readInt(scanner, "Enter new Patient ID: "));
                        appointment.setDoctorId(readInt(scanner, "Enter new Doctor ID: "));
                        System.out.print("Enter new Appointment Date (YYYY-MM-DD): ");
                        appointment.setAppointmentDate(scanner.nextLine());
                        System.out.print("Enter new Notes: ");
                        appointment.setNotes(scanner.nextLine());
                        appointmentService.updateAppointment(appointment);
                        System.out.println("Appointment updated successfully.");
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                case 4:
                    appointmentId = readInt(scanner, "Enter Appointment ID to delete: ");
                    appointmentService.deleteAppointment(appointmentId);
                    System.out.println("Appointment deleted successfully.");
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
