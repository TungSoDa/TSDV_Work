package com.ifi.servletwebsite.Model;

import java.sql.Date;

public class User {
    private Integer id;
    private String username;
    private Date birthday;
    private String email;
    private String company;
    private String homeTown;

    public User() {
    }

    public User(Integer id, String username, Date birthday, String email, String company, String homeTown) {
        this.id = id;
        this.username = username;
        this.birthday = birthday;
        this.email = email;
        this.company = company;
        this.homeTown = homeTown;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }
}
