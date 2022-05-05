package com.roryscode.PersonAPI.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


import javax.persistence.Transient;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class PersonService {

   private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeople() {
        return personRepository.findAll();
    }


    public void addNewPerson(Person person) {
        Optional<Person> optionalPerson = personRepository.findPersonByEmail(person.getEmail());
        if(optionalPerson.isPresent()){
            throw new IllegalStateException("A person with this email already exists");
        }
        personRepository.save(person);
    }

    public void removePerson(Long personId) {
        boolean exists = personRepository.existsById(personId);
        if(!exists){
            throw new IllegalStateException("Unable to find Person in database");
        }
        personRepository.deleteById(personId);
    }


    @Transient
    public void updatePerson(Long personId, String firstName, String lastName, String email){
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalStateException("Person with " + personId + " does not exist"));
        // Check if the argument for firstName is not NULL and doesn't match the current value
        if (firstName != null && firstName.length() >= 0 && !Objects.equals(person.getFirstName(),firstName)){
            person.setFirstName(firstName);
        }
        // Check if the argument for lastName is not NULL and doesn't match the current value
        if(lastName != null && lastName.length() >= 0 && !Objects.equals(person.getLastName(), lastName)){
            person.setLastName(lastName);
        }
        // Check if the argument for email is not NULL and doesn't match the current value
        if (email != null && email.length() >= 0 && !Objects.equals(person.getEmail(), email)){
            person.setEmail(email);
        }
    }

}
