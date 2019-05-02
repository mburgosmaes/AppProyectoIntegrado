package com.proyectointegrado.Database_manager;

import java.util.Date;

public class Users {
    String name, surname,email, pass, type;

    public Users(String name, String surname, String email, String pass, String type) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pass = pass;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
