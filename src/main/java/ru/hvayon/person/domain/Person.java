package ru.hvayon.person.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persons")
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Person {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "address")
    private String address;
    @Column(name = "work")
    private String work;

    public Person() {
    }

    public Person(String name, int age, String work, String address) {
        this.name = name;
        this.age = age;
        this.work = work;
        this.address = address;
    }

    public Person(int id, String name, int age, String address, String work) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.work = work;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getWork() {
        return work;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", work='" + work + '\'' +
                '}';
    }
}
