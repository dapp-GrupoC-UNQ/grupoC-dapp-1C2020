package com.example.demo.services.users;

import com.example.demo.model.user.ClientUser;
import com.example.demo.model.store.Store;
import com.example.demo.model.user.StoreAdminUser;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IUserService {

    ClientUser validateUser(ClientUser clientUser);

    List<ClientUser> getUsers();

    Boolean canAddUser(String username);

    ClientUser addUser(String username, String password);

    StoreAdminUser addStoreAdmin(String username, String password, Store store);
}
