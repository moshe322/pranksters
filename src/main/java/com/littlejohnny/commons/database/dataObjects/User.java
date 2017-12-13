package com.littlejohnny.commons.database.dataObjects;

import java.sql.Date;
import java.util.Objects;

public class User {

    private int id;
    private String email;
    private String password;

    private String name;
    private String surname;

    private Date birthday;

    private String country;
    private String sity;

    public User(String email, String password, String name, String surname, Date birthday, String country, String sity) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.country = country;
        this.sity = sity;
    }

    public User(int id, String email, String password, String name, String surname, Date birthday, String country, String sity) {
        this(email, password, name, surname, birthday, country, sity);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSity() {
        return sity;
    }

    public void setSity(String sity) {
        this.sity = sity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(country, user.country) &&
                Objects.equals(sity, user.sity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, name, surname, birthday, country, sity);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday.toString() +
                ", country='" + country + '\'' +
                ", sity='" + sity + '\'' +
                '}';
    }
}
