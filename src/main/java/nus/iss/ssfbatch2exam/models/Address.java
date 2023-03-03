package nus.iss.ssfbatch2exam.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Address {

    @NotBlank(message = "Name is a mandotary field")
    @Size(min = 2, message = "Your name must be longer than 2 characters")
    private String name;

    @NotBlank(message = "Address is a mandotary field")
    private String address;

    public Address() {
    }

    public Address(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address [name=" + name + ", address=" + address + "]";
    }

    

    

    
}
