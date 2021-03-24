package com.tsdv.demospringboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Underwear implements Outfit {
    @Override
    public void wear() {
        System.out.println("Wearing Underwear");
    }
}
