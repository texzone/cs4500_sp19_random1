package com.example.cs4500_sp19_random1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String zipCode;
    private float rating;

    private int yearsInBusiness;
    private String price;
    private int hires;
    private int employees;
    private boolean backgroundChecked;
    private String introduction;

    @OneToOne
    @JsonIgnore
    private User user;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getYearsInBusiness() {
        return yearsInBusiness;
    }

    public void setYearsInBusiness(int yearsInBusiness) {
        this.yearsInBusiness = yearsInBusiness;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getHires() {
        return hires;
    }

    public void setHires(int hires) {
        this.hires = hires;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public boolean isBackgroundChecked() {
        return backgroundChecked;
    }

    public void setBackgroundChecked(boolean backgroundChecked) {
        this.backgroundChecked = backgroundChecked;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


    @Override
    public String toString() {
        return "ServiceProvider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", rating=" + rating +
                ", yearsInBusiness=" + yearsInBusiness +
                ", price='" + price + '\'' +
                ", hires=" + hires +
                ", employees=" + employees +
                ", backgroundChecked=" + backgroundChecked +
                ", introduction='" + introduction + '\'' +
                '}';
    }

}