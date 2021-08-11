package POJO;

import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.time.format.DateTimeFormatter;

public class Customer {
    private String customerID;
    private String name;
    private String password;
    private char gender;
    private LocalDate dob;
    private String mobile_number;
    private boolean isPremium;

    
    
    public Customer(String customerID, String name, String password, char gender, LocalDate dob, String mobile_number,
            boolean isPremium) {
        this.customerID = customerID;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        this.mobile_number = mobile_number;
        this.isPremium = isPremium;
    }
    public String getCustomerId() {
        return customerID;
    }
    public void setCustomerId(String customerID) {
        this.customerID = customerID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public String getMobile_number() {
        return mobile_number;
    }
    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }
    public boolean isPremium() {
        return isPremium;
    }
    public void setPremium(boolean isPremium) {
        this.isPremium = isPremium;
    }

    @Override
    public boolean equals(Object obj) {
        return this.customerID.equals(((Customer)obj).getCustomerId());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("| %-20s |",customerID));
        builder.append(String.format(" %-20s |",name));
        builder.append(String.format(" %-15s |",password));
        builder.append(String.format(" %-10c |",gender));
        builder.append(String.format(" %-20s |",dob.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))));
        builder.append(String.format(" %-13s |",mobile_number));
        builder.append(String.format(" %-10s |",isPremium));
        return builder.toString();
    }
    
}
