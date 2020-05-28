package edu.wsb.students.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "horse_power")
    private int horsePower;

    @Column(name = "engine_capacity")
    private String engineCapacity;

    @Column(name = "production_year")
    private int productionYear;

    @Column(name = "vinNumber")
    private String vinNumber;

    @Column(name = "insurance_date")
    private LocalDate insuranceDate;

    @Column(name = "technical_inspection_date")
    private LocalDate technicalInspectionDate;

    @Column(name = "daily_payment")
    private BigDecimal dailyPayment;

    @Column(name = "rate")
    private String rate;

    public Car() {
    }

    public Car(String brand, String model, int horsePower,
               String engineCapacity, int productionYear,
               String vinNumber, LocalDate insuranceDate,
               LocalDate technicalInspectionDate, BigDecimal dailyPayment,
               String rate) {
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
        this.engineCapacity = engineCapacity;
        this.productionYear = productionYear;
        this.vinNumber = vinNumber;
        this.insuranceDate = insuranceDate;
        this.technicalInspectionDate = technicalInspectionDate;
        this.dailyPayment = dailyPayment;
        this.rate = rate;
    }

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

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public LocalDate getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(LocalDate insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public LocalDate getTechnicalInspectionDate() {
        return technicalInspectionDate;
    }

    public void setTechnicalInspectionDate(LocalDate technicalInspectionDate) {
        this.technicalInspectionDate = technicalInspectionDate;
    }

    public BigDecimal getDailyPayment() {
        return dailyPayment;
    }

    public void setDailyPayment(BigDecimal dailyPayment) {
        this.dailyPayment = dailyPayment;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "id: " + id + '\n' +
               "\tbrand: " + brand + '\n' +
               "\tmodel: " + model + '\n' +
               "\thorsePower: " + horsePower + '\n' +
               "\tengineCapacity: " + engineCapacity + '\n' +
               "\tproductionYear: " + productionYear + '\n' +
               "\tvinNumber: " + vinNumber + '\n' +
               "\tinsuranceDate: " + insuranceDate + '\n' +
               "\ttechnicalInspectionDate: " + technicalInspectionDate + '\n' +
               "\tdailyPayment: " + dailyPayment + '\n' +
               "\trate: " + rate;
    }

}