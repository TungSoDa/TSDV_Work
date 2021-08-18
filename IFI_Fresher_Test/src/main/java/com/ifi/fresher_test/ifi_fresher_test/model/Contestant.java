package com.ifi.fresher_test.ifi_fresher_test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contestant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contestant {
    @Id
    private String username;

    @Column(name = "fullname")
    private String fullName;

    public Contestant(String username) {
        this.username = username;
    }
}
