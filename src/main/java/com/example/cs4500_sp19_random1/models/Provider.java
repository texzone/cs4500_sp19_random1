package com.example.cs4500_sp19_random1.models;

import com.example.cs4500_sp19_random1.util.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.Calendar;

@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true)
    private String name;
    private float rating;

    private String price;
    private int hires;
    private int numEmployees;
    private boolean backgroundChecked;
    private String introduction;

    @Embedded
    private Address businessAddress;
    private String businessEmail;
    private int yearFounded;
    private boolean creditCard;
    private boolean cash;
    private boolean paperCheck; // SQL doesn't like "check"
    private boolean venmo;
    private boolean paypal;
    private boolean square;
    private String facebookLink;
    private String instaLink;
    private String twitterLink;

    @OneToOne
    @JsonIgnore
    private User user;

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public boolean isCreditCard() {
        return creditCard;
    }

    public void setCreditCard(boolean creditCard) {
        this.creditCard = creditCard;
    }

    public boolean isCash() {
        return cash;
    }

    public void setCash(boolean cash) {
        this.cash = cash;
    }

    public boolean isCheck() {
        return paperCheck;
    }

    public void setCheck(boolean check) {
        this.paperCheck = check;
    }

    public boolean isVenmo() {
        return venmo;
    }

    public void setVenmo(boolean venmo) {
        this.venmo = venmo;
    }

    public boolean isPaypal() {
        return paypal;
    }

    public void setPaypal(boolean paypal) {
        this.paypal = paypal;
    }

    public boolean isSquare() {
        return square;
    }

    public void setSquare(boolean square) {
        this.square = square;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getInstaLink() {
        return instaLink;
    }

    public void setInstaLink(String instaLink) {
        this.instaLink = instaLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(int yearFounded) {
        this.yearFounded = yearFounded;
    }

    public int getYearsInBusiness() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return year - yearFounded;

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

    public int getNumEmployees() {
        return numEmployees;
    }

    public void setNumEmployees(int employees) {
        this.numEmployees = employees;
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

    public void setName(String name) {
        this.name = name;
    }


    public Address getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(Address address) {
        this.businessAddress = address;
    }

    @Override
    public String toString() {
        return "ServiceProvider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", yearsInBusiness=" + Integer.toString(getYearsInBusiness()) +
                ", price='" + price + '\'' +
                ", hires=" + hires +
                ", employees=" + numEmployees +
                ", backgroundChecked=" + backgroundChecked +
                ", introduction='" + introduction + '\'' +
                '}';
    }

}