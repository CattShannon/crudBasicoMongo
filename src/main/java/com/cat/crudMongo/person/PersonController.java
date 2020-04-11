package com.cat.crudMongo.person;

import com.cat.crudMongo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping(value = "/allPersons")
    public List<Person> getAllPersons(){
        return personService.findAllPersons();
    }

    @GetMapping(value = "/{name}")
    public List<Person> getAllPersonsWith(@PathVariable ("name") String name){
        return personService.findPersonsWithName(name);
    }

    @GetMapping(value = "/find/{id}")
    public Person findPersonById(@PathVariable("id") String id){
        return personService.findPersonById(id);
    }

    @PostMapping(value = "/save")
    public Person savePerson(@RequestBody Person person){
        return personService.savePerson(person);
    }

    @PutMapping(value = "/update")
    public Person updatePerson(@RequestBody Person person){
        var verifyPerson = personService.findPersonById(person.getId());
        if(verifyPerson != null){
            return personService.savePerson(person);
        }
        throw new NotFoundException("Cannot update, person doesn't exist");
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deletePersonById(@PathVariable("id") String id){
        personService.deletePersonById(id);
    }
}
