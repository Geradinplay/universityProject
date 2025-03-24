package org.acme.rest.userDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class PersonalDataDTO {
    @Email
    private String mail;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
