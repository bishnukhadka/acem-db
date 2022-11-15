package com.acem.db.model;

import jakarta.persistence.*;

@Entity
@Table(name = "STUDENTS")
public class Student {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="NAME", length=100, nullable = false, unique = true)
    private String name;
    @Column(name="EMAIL", length=100, nullable = false, unique = true)
    private String email;
    @Column(name="CONTACT_NO", length=100, nullable = false, unique = true)
    private String contactNo;
    public Student() {
    }

    public Student(Long id, String name, String email, String contactNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }


}
