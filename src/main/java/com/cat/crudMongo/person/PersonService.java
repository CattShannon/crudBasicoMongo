package com.cat.crudMongo.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public List<Person> findPersonsWithName(String name) {
        return personRepository.findByName(name);
    }

    public Person findPersonById(String id) {
        return personRepository.findById(id).orElse(null);
    }


    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePersonById(String id) {
        personRepository.deleteById(id);
    }
}
