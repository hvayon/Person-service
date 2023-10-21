package ru.hvayon.person;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.hvayon.person.domain.Person;
import ru.hvayon.person.model.PersonRequest;
import ru.hvayon.person.repository.PersonRepository;
import ru.hvayon.person.service.impl.PersonServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
public class PersonServiceImplTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    Person person;

    @Test
    void findByIdTest () {
        int personId = 1;
        person = new Person(1, "Natalia", 24, "Moscow", "SBER");
        when(personRepository.findById(personId)).thenReturn(Optional.ofNullable(person));
        Person result = personService.getPersonById(personId);
        Assertions.assertEquals(person, result);
    }

    @Test
    public void findAllPersonsTest() {
        List<Person> persons = Arrays.asList(new Person(1, "John", 30, "Engineer", "Tinkoff"),
                new Person(2, "Jane", 25, "Doctor", "Hospital"));
        when(personRepository.findAll()).thenReturn(persons);
        List<Person> result = personService.getPersons();
        assertEquals(persons, result);
    }

    @Test
    public void createPersonTest() {
        PersonRequest request = new PersonRequest("Mike", 27, "Developer", "SBER");
        Person savedPerson = new Person(1, "Mike", 27, "Developer", "SBER");
        when(personRepository.save(any(Person.class))).thenReturn(savedPerson);
        Integer createdPersonId = personService.createPerson(request);
        assertNotNull(createdPersonId);
        assertEquals(savedPerson.getId(), createdPersonId);
    }


    @Test
    public void editPersonTest() {
        int personId = 1;
        Person person = new Person(1, "John", 30, "Engineer", "Kaspersky");
        Map<String, Object> fields = new HashMap<>();
        fields.put("name", "John Changed");
        fields.put("age", 35);
        Mockito.when(personRepository.findById(personId)).thenReturn(Optional.of(person));
        Mockito.when(personRepository.save(any(Person.class))).thenReturn(person);

        Person result = personService.editPerson(personId, fields);

        Assertions.assertEquals("John Changed", result.getName());
        Assertions.assertEquals(35, result.getAge());
    }

    @Test
    public void deletePersonTest() {
        int personId = 1;
        personService.deletePerson(personId);
        Mockito.verify(personRepository, Mockito.times(1)).deleteById(personId);
    }
}