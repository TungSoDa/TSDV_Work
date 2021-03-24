package com.tsdv.demospringboot;

import com.tsdv.demospringboot.childpackage1.Bikini;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan("com.tsdv.demospringboot.childpackage1")
@SpringBootApplication
public class DemoSpringBootApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoSpringBootApplication.class, args);

//        Outfit outfit =  context.getBean(Outfit.class);
//        System.out.println(outfit);
//        outfit.wear();


//        Dress dress =  context.getBean(Dress.class);
//        System.out.println(dress);
//
//        Bikini bikini =  context.getBean(Bikini.class);
//        System.out.println(bikini);


//        Girl girl = context.getBean(Girl.class);
//        System.out.println(girl);
//        System.out.println(girl.getOutfit());
    }
}
