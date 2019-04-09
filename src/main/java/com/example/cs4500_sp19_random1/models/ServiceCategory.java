package com.example.cs4500_sp19_random1.models;

/*
 * Not sure what the rule of thumb is, but these imports were getting extensive.
 * Shortening with wildcard until someone argues otherwise.
 */
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.ArrayList;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="service_categories")
public class ServiceCategory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @ManyToMany
    @JoinTable(
            name="CATEGORIES_SERVICES",
            joinColumns=@JoinColumn(name="CATEGORY_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="SERVICE_ID", referencedColumnName="ID"))
    private List<Service> services = new ArrayList<Service>();
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<Service> getServices() {
        return services;
    }
    public void setServices(List<Service> services) {
        this.services = services;
    }
}
