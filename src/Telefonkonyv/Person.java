package Telefonkonyv;

import java.io.Serializable;

public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private String nickname;
    private String address;
    private String workPhoneNumber;
    private String privatePhoneNumber;

    public Person(String firstName, String lastName, String nickname, String address, String workPhoneNumber, String privatePhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.address = address;
        this.workPhoneNumber = workPhoneNumber;
        this.privatePhoneNumber = privatePhoneNumber;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }

    public String getPrivatePhoneNumber() {
        return privatePhoneNumber;
    }

    public void setPrivatePhoneNumber(String privatePhoneNumber) {
        this.privatePhoneNumber = privatePhoneNumber;
    }


    @Override
    public String toString() {
        String result = firstName + " " + lastName;
        if (nickname != null && !nickname.isEmpty()) {
            result += " (" + nickname + ")";
        }
        result += "\nLakhely: " + address;
        if (workPhoneNumber != null && !workPhoneNumber.isEmpty()) {
            result += "\nMunkahelyi szam: " + workPhoneNumber;
        }
        result += "\nPrivat szam: " + privatePhoneNumber + "\n-------------------------";
        return result;
    }
}