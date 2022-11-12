package com.example.examspringjpa.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * todo:
 * - lege-constructor implementeren
 * - getters en setters implementeren
 * - implementeer equals en hashCode gebaseerd op identifier-field
 * - configureer JPA entiteit
 * - tabelnaam specificeren: "employee"
 * - configureer automatisch gegenereerde identifier
 * - configureer niet-nullable kolommen: email, firstName, lastName
 * - breng unidirectionele relatie tussen {@link Employee} en {@link
EmployeeProfile} aan de child-zijde in de relatie
 */
@Entity(name = "employee")
@EntityListeners(AuditingEntityListener.class)
public class Employee {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Date created_at;
    private Date updated_at;
    private Company company;
    public Employee() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @CreatedDate
    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @LastModifiedDate
    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @ManyToOne
    @JoinColumn(name="company_id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id) && email.equals(employee.email) && firstName.equals(employee.firstName) && lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName);
    }
}
