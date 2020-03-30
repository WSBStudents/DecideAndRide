package edu.wsb.students.model;

import java.time.LocalDate;

public class Customer {

    private String name;
    private String documentId;
    private LocalDate documentExpirationDate;
    private String drivingLicenceId;
    private LocalDate drivingLicenceExpirationDate;
    private LocalDate birthDate;
    private String adress;
    private int phoneNumber;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDrivingLicenceId() {
        return drivingLicenceId;
    }

    public void setDrivingLicenceId(String drivingLicenceId) {
        this.drivingLicenceId = drivingLicenceId;
    }

    public LocalDate getDocumentExpirationDate() {
        return documentExpirationDate;
    }

    public void setDocumentExpirationDate(LocalDate documentExpirationDate) {
        this.documentExpirationDate = documentExpirationDate;
    }

    public LocalDate getDrivingLicenceExpirationDate() {
        return drivingLicenceExpirationDate;
    }

    public void setDrivingLicenceExpirationDate(LocalDate drivingLicenceExpirationDate) {
        this.drivingLicenceExpirationDate = drivingLicenceExpirationDate;
    }
}