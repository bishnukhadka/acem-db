package com.acem.db.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class StudentSaveRequest {

    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String contactNo;

    public StudentSaveRequest() {
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

}
