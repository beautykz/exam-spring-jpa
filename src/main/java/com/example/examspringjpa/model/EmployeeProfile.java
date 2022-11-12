package com.example.examspringjpa.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * todo:
 * - maak een lege-constructor aan
 * - getters en setters moet geimplenteerd worden
 * - implementeer equals en hashCode gebaseerd op identifier-field
 * - configureer JPA entiteit
 * - tabelnaam opgeven: "employee_profile"
 * - configureer niet-nullable kolommen: position, department
 * - relatie tussen {@link Employee} en {@link EmployeeProfile} moet gemapt
 worden met behulp van foreign_key kolom: "employee_id"
 * - configureer een afgeleide (derived) identifier. De kolom "employee_id"
 moet bijvoorbeeld ook een primaire sleutel (id) (PK) zijn voor deze
 entiteit.
 **/
@Entity(name = "employee_profile")
@EntityListeners(AuditingEntityListener.class)
public class EmployeeProfile {
    private Long id;
    private String department;
    private String position;
    private Employee employee;
    private Date created_at;
    private Date updated_at;


    public EmployeeProfile() {
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
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(nullable = false)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeProfile that = (EmployeeProfile) o;
        return id.equals(that.id) && department.equals(that.department) && position.equals(that.position) && employee.equals(that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, department, position, employee);
    }
}
