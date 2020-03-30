package edu.wsb.students.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Table(name = "Car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "hp")
    private int hp;

    @Column(name = "engineCapacity")
    private String engineCapacity;

    @Column(name = "prodYear")
    private int prodYear;

    @Column(name = "VIN")
    private String VIN;

    @Column(name = "insuranceDate")
    private LocalDateTime insuraceDate;

    @Column(name = "technicalInspection")
    private String technicalInspection;

    @Column(name = "priceDay")
    private Currency priceDay;

    @Column(name = "priceMonth")
    private Currency priceMonth;

    @Column(name = "rate")
    private String rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public int getProdYear() {
        return prodYear;
    }

    public void setProdYear(int prodYear) {
        this.prodYear = prodYear;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public LocalDateTime getInsuraceDate() {
        return insuraceDate;
    }

    public void setInsuraceDate(LocalDateTime insuraceDate) {
        this.insuraceDate = insuraceDate;
    }

    public String getTechnicalInspection() {
        return technicalInspection;
    }

    public void setTechnicalInspection(String technicalInspection) {
        this.technicalInspection = technicalInspection;
    }

    public Currency getPriceDay() {
        return priceDay;
    }

    public void setPriceDay(Currency priceDay) {
        this.priceDay = priceDay;
    }

    public Currency getPriceMonth() {
        return priceMonth;
    }

    public void setPriceMonth(Currency priceMonth) {
        this.priceMonth = priceMonth;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

}