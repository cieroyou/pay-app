package com.sera.payapp.money;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.AliasFor;

@SpringBootApplication
public class MoneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyApplication.class, args);
    }

//    @CommandHandler(@AliasFor("test"))
    @AliasFor(annotation = CommandHandler.class)
    public void test(Object object) {
    }
}
