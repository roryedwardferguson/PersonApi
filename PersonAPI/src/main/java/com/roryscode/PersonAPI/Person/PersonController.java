package com.roryscode.PersonAPI.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPeople(){
        return personService.getPeople();
    }

    @PostMapping
    public void addNewPerson(@RequestBody Person person){
        personService.addNewPerson(person);
    }

    @DeleteMapping(path = "{personId}")
    public void removePerson(@PathVariable("personId" )Long personId){
        personService.removePerson(personId);
    }

    @PutMapping(path = "{personId}")
    public void updatePerson(@PathVariable("personId") Long personId,
                             @RequestParam(required = false) String firstName,
                             @RequestParam(required = false) String lastName,
                             @RequestParam(required = false) String email){
        personService.updatePerson(personId, firstName, lastName, email);
    }


}
