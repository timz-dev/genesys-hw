package com.genesys.hw.models;

public class PersonalInformation {
    private String firstName;
    private String lastName;
    private int postalCode;

    public int getPostalCode() {
        return postalCode;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
