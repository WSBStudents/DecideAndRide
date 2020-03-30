package edu.wsb.students.model;

import java.time.LocalDateTime;
import java.util.Currency;

public class Order {

    private int id;
    private LocalDateTime rentDate;
    private Currency price;
    private Car rentalCar;
    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDateTime rentDate) {
        this.rentDate = rentDate;
    }

    public Currency getPrice() {
        return price;
    }

    public void setPrice(Currency price) {
        this.price = price;
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

}