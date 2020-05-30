package com.example.demo.services.users;

import com.example.demo.model.ClientUser;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IUserService {

    ClientUser validateUser(ClientUser clientUser);

    List<ClientUser> getUsers();

    Boolean canAddUser(String username);

    ClientUser addUser(String username, String password);
}
