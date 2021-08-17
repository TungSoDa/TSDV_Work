package com.ifi.fresher_test.ifi_fresher_test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private String username;

    private String password;

    private boolean active;

    private String role;

//    @OneToOne
//    @JoinColumn
//    private Contestant contestant;
//
//    @OneToOne
//    @JoinColumn
//    private Contributor contributor;


    public Account(String username) {
        this.username = username;
    }

    public Account(String username, String password, Boolean active) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.role = null;
    }
}
