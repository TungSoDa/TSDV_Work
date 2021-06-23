package com.ifi.fresher_test.ifi_fresher_test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne
    @JoinColumn
    private Account account;

    @OneToMany
    @JoinColumn
    private List<Exam> examList = new ArrayList<>();
}
