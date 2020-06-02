package edu.wsb.students.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

// Klasa modelu zlecenia
// oraz adnotacje Hibernate
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "rentDate")
    private LocalDate rentDate;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car rentalCar;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    // Pusty konstruktor
    public Order() {
    }

    // Konstruktor ze wszystkimi polami oprócz id(które jest generowane przez bazę).
    public Order(LocalDate rentDate, Car rentalCar, Customer customer) {
        this.rentDate = rentDate;
        this.rentalCar = rentalCar;
        this.customer = customer;
    }

    // Getery oraz setery do każdego pola

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public Car getRentalCar() {
        return rentalCar;
    }

    public void setRentalCar(Car rentalCar) {
        this.rentalCar = rentalCar;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Nadpisana metoda toString, która wyświetla w odpowiednim formacie wszystkie dane Order`a
    @Override
    public String toString() {
        return "id: " + id + '\n' +
                "\trentDate: " + rentDate + '\n' +
                "\trentalCarId: " + rentalCar.getId() + '\n' +
                "\tcustomerId: " + customer.getId();
    }

}