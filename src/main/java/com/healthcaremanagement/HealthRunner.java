package com.healthcaremanagement;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Office;
import com.healthcaremanagement.model.Patient;
import com.healthcaremanagement.service.AppointmentService;
import com.healthcaremanagement.service.DoctorService;
import com.healthcaremanagement.service.OfficeService;
import com.healthcaremanagement.service.PatientService;
import java.util.Scanner;

public class HealthRunner {

    public void run(PatientService patientService,
                    DoctorService doctorService,
                    AppointmentService appointmentService,
                    OfficeService officeService) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n====== Main Menu ======");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Manage Appointments");
            System.out.println("4. Manage Offices");
            System.out.println("5. Exit");
            int choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    managePatients(patientService, scanner);
                    break;
                case 2:
                    manageDoctors(doctorService, scanner);
                    break;
                case 3:
                    // Updated signature with patientService and doctorService for relationships.
                    manageAppointments(appointmentService, patientService, doctorService, scanner);
                    break;
                case 4:
                    manageOffices(officeService, doctorService, scanner);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Helper method to safely read integers.
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
                scanner.nextLine(); // clear the invalid input
            }
        }
        return value;
    }

    // Method for managing patients.
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

    // Method for managing doctors.
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

    // Method for managing appointments, including relationship methods.
    private static void manageAppointments(AppointmentService appointmentService,
                                           PatientService patientService,
                                           DoctorService doctorService,
                                           Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Appointment Management ---");
            System.out.println("1. Create Appointment");
            System.out.println("2. Read Appointment");
            System.out.println("3. Update Appointment");
            System.out.println("4. Delete Appointment");
            System.out.println("5. List All Appointments");
            System.out.println("6. Back to Main Menu");
            int choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1: {
                    Appointment newAppointment = new Appointment();
                    int patientId = readInt(scanner, "Enter Patient ID: ");
                    int doctorId = readInt(scanner, "Enter Doctor ID: ");

                    // Assume you retrieve the entities via their service
                    Patient patient = patientService.getPatientById(patientId);
                    Doctor doctor = doctorService.getDoctorById(doctorId);

                    if (patient == null || doctor == null) {
                        System.out.println("Either patient or doctor was not found.");
                        break;
                    }

                    newAppointment.setPatient(patient);
                    newAppointment.setDoctor(doctor);

                    System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                    newAppointment.setAppointmentDate(scanner.nextLine());
                    System.out.print("Enter Notes: ");
                    newAppointment.setNotes(scanner.nextLine());

                    appointmentService.createAppointment(newAppointment);

                    // Apply relationship methods
                    doctorService.addPatientToDoctor(doctorId, patient);
                    patientService.addDoctorToPatient(patientId, doctor);

                    System.out.println("Appointment created successfully.");
                    break;
                }
                case 2: {
                    int appointmentId = readInt(scanner, "Enter Appointment ID: ");
                    Appointment appointment = appointmentService.getAppointmentById(appointmentId);
                    if (appointment != null) {
                        System.out.println("Appointment ID: " + appointment.getAppointmentId());
                        System.out.println("Patient ID: " + appointment.getPatient().getPatientId());
                        System.out.println("Doctor ID: " + appointment.getDoctor().getDoctorId());
                        System.out.println("Appointment Date: " + appointment.getAppointmentDate());
                        System.out.println("Notes: " + appointment.getNotes());
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                }
                case 3: {
                    int appointmentId = readInt(scanner, "Enter Appointment ID: ");
                    Appointment appointment = appointmentService.getAppointmentById(appointmentId);
                    if (appointment != null) {
                        // Get the original doctor and patient for relationship management
                        Doctor originalDoctor = appointment.getDoctor();
                        Patient originalPatient = appointment.getPatient();

                        System.out.print("Enter new Appointment Date (YYYY-MM-DD): ");
                        appointment.setAppointmentDate(scanner.nextLine());
                        System.out.print("Enter new Notes: ");
                        appointment.setNotes(scanner.nextLine());

                        appointmentService.updateAppointment(appointment);

                        // Check if there are no other appointments between this doctor and patient.
                        if (!appointmentService.hasOtherAppointmentsBetween(
                                originalDoctor.getDoctorId(), originalPatient.getPatientId())) {
                            doctorService.removePatientFromDoctor(originalDoctor.getDoctorId(), originalPatient);
                            patientService.removeDoctorFromPatient(originalPatient.getPatientId(), originalDoctor);
                        }

                        System.out.println("Appointment updated successfully.");
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                }
                case 4: {
                    int appointmentId = readInt(scanner, "Enter Appointment ID to delete: ");
                    Appointment appointment = appointmentService.getAppointmentById(appointmentId);
                    if (appointment != null) {
                        // Obtain doctor and patient for relationship removal.
                        Doctor doctorToCheck = appointment.getDoctor();
                        Patient patientToCheck = appointment.getPatient();

                        appointmentService.deleteAppointment(appointmentId);

                        // If there are no other appointments linking the doctor and patient, remove relationship.
                        if (!appointmentService.hasOtherAppointmentsBetween(
                                doctorToCheck.getDoctorId(), patientToCheck.getPatientId())) {
                            doctorService.removePatientFromDoctor(doctorToCheck.getDoctorId(), patientToCheck);
                            patientService.removeDoctorFromPatient(patientToCheck.getPatientId(), doctorToCheck);
                        }

                        System.out.println("Appointment deleted successfully.");
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                }
                case 5: {
                    appointmentService.getAllAppointments().forEach(app -> System.out.println("Appointment ID: " + app.getAppointmentId()
                            + " | Patient ID: " + app.getPatient().getPatientId()
                            + " | Doctor ID: " + app.getDoctor().getDoctorId()
                            + " | Date: " + app.getAppointmentDate()));
                    break;
                }
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Method for managing offices.
    private static void manageOffices(OfficeService officeService, DoctorService doctorService, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Office Management ---");
            System.out.println("1. Create Office");
            System.out.println("2. Read Office");
            System.out.println("3. Update Office");
            System.out.println("4. Delete Office");
            System.out.println("5. List All Offices");
            System.out.println("6. Back to Main Menu");
            int choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1: {
                    Office newOffice = new Office();
                    System.out.print("Enter Location: ");
                    newOffice.setLocation(scanner.nextLine());
                    System.out.print("Enter Phone: ");
                    newOffice.setPhone(scanner.nextLine());
                    int doctorId = readInt(scanner, "Enter Doctor ID to associate with this office: ");

                    // Use DoctorService instead of OfficeService
                    Doctor doctor = doctorService.getDoctorById(doctorId);
                    if (doctor == null) {
                        System.out.println("Doctor not found. Office creation aborted.");
                        break;
                    }
                    newOffice.setDoctor(doctor);
                    officeService.createOffice(newOffice);
                    System.out.println("Office created successfully.");
                    break;
                }
                case 2: {
                    int officeId = readInt(scanner, "Enter Office ID: ");
                    Office office = officeService.getOfficeById(officeId);
                    if (office != null) {
                        System.out.println("Office ID: " + office.getOfficeId());
                        System.out.println("Location: " + office.getLocation());
                        System.out.println("Phone: " + office.getPhone());
                        if (office.getDoctor() != null) {
                            System.out.println("Associated Doctor ID: " + office.getDoctor().getDoctorId());
                        }
                    } else {
                        System.out.println("Office not found.");
                    }
                    break;
                }
                case 3: {
                    int officeId = readInt(scanner, "Enter Office ID: ");
                    Office office = officeService.getOfficeById(officeId);
                    if (office != null) {
                        System.out.print("Enter new Location: ");
                        office.setLocation(scanner.nextLine());
                        System.out.print("Enter new Phone: ");
                        office.setPhone(scanner.nextLine());
                        officeService.updateOffice(office);
                        System.out.println("Office updated successfully.");
                    } else {
                        System.out.println("Office not found.");
                    }
                    break;
                }
                case 4: {
                    int officeId = readInt(scanner, "Enter Office ID to delete: ");
                    officeService.deleteOffice(officeId);
                    System.out.println("Office deleted successfully.");
                    break;
                }
                case 5: {
                    officeService.getAllOffices().forEach(office -> {
                        System.out.println("Office ID: " + office.getOfficeId()
                                + " | Location: " + office.getLocation()
                                + " | Phone: " + office.getPhone());
                    });
                    break;
                }
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
