package com.tsdv.demospringboot.controller;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Integer ID;
    private String name;
}
