package com.tsdv.demospringboot.childpackage1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Bikini {
    public void wear() {
        System.out.println("wearing Bikini");
    }
}
