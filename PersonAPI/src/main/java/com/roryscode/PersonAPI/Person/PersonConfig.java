package com.roryscode.PersonAPI.Person;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class PersonConfig {

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository){
        return args ->{
            Person p1 = new Person("Rory", "Ferguson", "rory@email.com", LocalDate.of(1990,9,6));
            Person p2 = new Person("Holly","Ferguson","holly@email.com",LocalDate.of(1992,11,30));

            personRepository.saveAll(
                    List.of(p1,p2)
            );
        };
    }

}
