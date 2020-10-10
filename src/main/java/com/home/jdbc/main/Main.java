package com.home.jdbc.main;

import com.home.jdbc.dao.PersonDAO;
import com.home.jdbc.entity.Person;
import com.home.jdbc.server.Server;

public class Main {
    public static void main(String[] args) {
        Person person = new Person(1, "James", "Bond", "007@jamesbond.com");
        Person person2 = new Person(2, "Forrest", "Gump", "forrestgump@jamesbond.com");
        try {
            PersonDAO.insert(person);
            PersonDAO.insert(person2);
            System.out.println("added persons");
            Server.readNames(5050);
        } catch (Exception e) {
            System.out.println("Exception occurred " + e.getMessage());
        }
    }
}
