////////////////////////////////////////////////////////////////////
// FEDERICO CHIARELLO 1187598
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import java.util.UUID;

public class User {

    private final String id;
    private final String name;
    private final String surname;
    private final int age;

    public User(String name, String surname, int age) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.age = age;
    } 
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }
}
