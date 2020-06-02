package edu.wsb.students.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

// Klasa modelu klienta
// oraz adnotacje Hibernate
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "document_id")
    private String documentId;

    @Column(name = "document_expiration_date")
    private LocalDate documentExpirationDate;

    @Column(name = "driving_licence_id")
    private String drivingLicenceId;

    @Column(name = "driving_licence_expiration_date")
    private LocalDate drivingLicenceExpirationDate;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    // pusty konstruktor
    public Customer() {
    }

    // konstruktor ze wszystkimi polami oprócz id(które jest generowane przez bazę).
    public Customer(String name, String documentId, LocalDate documentExpirationDate, String drivingLicenceId, LocalDate drivingLicenceExpirationDate, LocalDate birthday, String address, String phoneNumber) {
        this.name = name;
        this.documentId = documentId;
        this.documentExpirationDate = documentExpirationDate;
        this.drivingLicenceId = drivingLicenceId;
        this.drivingLicenceExpirationDate = drivingLicenceExpirationDate;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getery oraz setery do każdego pola

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getDocumentExpirationDate() {
        return documentExpirationDate;
    }

    public void setDocumentExpirationDate(LocalDate documentExpirationDate) {
        this.documentExpirationDate = documentExpirationDate;
    }

    public String getDrivingLicenceId() {
        return drivingLicenceId;
    }

    public void setDrivingLicenceId(String drivingLicenceId) {
        this.drivingLicenceId = drivingLicenceId;
    }

    public LocalDate getDrivingLicenceExpirationDate() {
        return drivingLicenceExpirationDate;
    }

    public void setDrivingLicenceExpirationDate(LocalDate drivingLicenceExpirationDate) {
        this.drivingLicenceExpirationDate = drivingLicenceExpirationDate;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Nadpisana metoda toString, która wyświetla w odpowiednim formacie wszystkie dane Customer`a
    @Override
    public String toString() {
        return "id: " + id + '\n' +
                "\tname: " + name + '\n' +
                "\tdocumentId: " + documentId + '\n' +
                "\tdocumentExpirationDate: " + documentExpirationDate + '\n' +
                "\tdrivingLicenceId: " + drivingLicenceId + '\n' +
                "\tdrivingLicenceExpirationDate: " + drivingLicenceExpirationDate + '\n' +
                "\tbirthday: " + birthday + '\n' +
                "\taddress: " + address + '\n' +
                "\tphoneNumber: " + phoneNumber;
    }

}