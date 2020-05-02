package com.example.demo.repositories.users;


import model.User;
import org.springframework.stereotype.Repository;


import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> registeredUsers = Arrays.asList(new User("Pepe", "abcdefg"), new User("John", "123456"));

}
