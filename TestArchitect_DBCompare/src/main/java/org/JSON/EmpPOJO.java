package org.JSON;

public class EmpPOJO {

    private  String firstName;
    private String lastName;
    private String profession;
    private int age;
    private String salary;
    private AddressPOJO address;

    public AddressPOJO getAddress() {
        return address;
    }

    public void setAddress(AddressPOJO address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }



}
