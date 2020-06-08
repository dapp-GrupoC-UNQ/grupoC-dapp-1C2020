package com.example.demo.controllers;

import com.example.demo.builders.ClientUserBuilder;
import com.example.demo.builders.StoreAdminBuilder;
import com.example.demo.helpers.StoreTestHelper;
import com.example.demo.model.exceptions.InvalidStoreException;
import com.example.demo.model.exceptions.NotAvailableUserNameException;
import com.example.demo.model.exceptions.NotFoundUserException;
import com.example.demo.model.store.Store;
import com.example.demo.model.user.StoreAdminUser;
import com.example.demo.model.user.User;
import com.example.demo.services.StoreService;
import com.example.demo.services.users.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.model.user.ClientUser;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsersControllerTest {

    private StoreTestHelper storeTestHelper = new StoreTestHelper();

    @MockBean
    UserService userServiceMock;
    @MockBean
    StoreService storeService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void aUserIsValidatedIfItsUsernameMatchesWithItsPassword() throws Exception {
        ClientUser clientUser = ClientUserBuilder.user().build();
        when(userServiceMock.authenticateUser(any())).thenReturn(clientUser);

        JSONObject body = generateClientUserBody(clientUser);
        mockMvc.perform(post("/validateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username", is(clientUser.username())));
    }

    @Test
    public void aUserIsNotValidatedIfItsUsernameMatchesWithItsPassword() throws Exception {
        ClientUser clientUser = ClientUserBuilder.user().build();
        when(userServiceMock.authenticateUser(any())).thenThrow(new NotFoundUserException());

        JSONObject body = generateClientUserBody(clientUser);
        mockMvc.perform(post("/validateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(body)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenCreatingAClientUserTheUserIsReturnedAndTheStatusIsOK() throws Exception {
        ClientUser clientUser = ClientUserBuilder.user().build();
        when(userServiceMock.addUser(any(), any())).thenReturn(addIdToClientUser(clientUser));

        JSONObject body = generateClientUserBody(clientUser);
        MvcResult mvcResult = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(clientUser.id())))
                .andExpect(jsonPath("username", is(clientUser.username())))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();

    }

    @Test
    public void addingAClientUserWithAnEmptyUsernameReturnsBadRequest() throws Exception {
        ClientUser clientUser = ClientUserBuilder.user().withEmptyUsername();

        String content = objectMapper.writeValueAsString(clientUser);
        MvcResult mvcResult = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void addingAClientUserWhichIsAlreadyRegisterUsernameReturnsBadRequest() throws Exception {
        ClientUser clientUser = ClientUserBuilder.user().build();
        when(userServiceMock.addUser(any(), any())).thenThrow(new NotAvailableUserNameException());

        JSONObject body = generateClientUserBody(clientUser);
        MvcResult mvcResult = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(body)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void addingAClientUserWithAnEmptyPasswordReturnsBadRequest() throws Exception {
        ClientUser clientUser = ClientUserBuilder.user().withEmptyPassword();

        JSONObject body = generateClientUserBody(clientUser);
        MvcResult mvcResult = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(body)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void addingAValidStoreAdminReturnsTheStoreAdminAnd200Status() throws Exception {
        StoreAdminUser aStoreAdmin = StoreAdminBuilder.aStoreAdmin().build();
        Store aStore = aStoreAdmin.store();
        when(userServiceMock.addStoreAdmin(any())).thenReturn(aStoreAdmin);

        String content = objectMapper.writeValueAsString(aStoreAdmin);
        MvcResult mvcResult = mockMvc.perform(post("/storeAdmin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(JsonPath.parse(response).read("username"), aStoreAdmin.username());
        assertEquals(JsonPath.parse(response).read("password"), aStoreAdmin.password());
        assertEquals(JsonPath.parse(response).read("store.storeName"), aStore.name());
        assertEquals(JsonPath.parse(response).read("store.storeAddress"), aStore.address());
        assertEquals(JsonPath.parse(response).read("store.storeCategories"),storeTestHelper.storeCategoriesToString(aStore.storeCategories()));
        assertEquals(JsonPath.parse(response).read("store.deliveryDistanceInKm"), aStore.deliveryDistanceInKm());
        assertEquals(JsonPath.parse(response).read("store.availablePaymentMethods"), aStore.availablePaymentMethods());
        assertEquals(JsonPath.parse(response).read("store.storeSchedule.openingTime"), aStore.storeSchedule().openingTime().toString());
        assertEquals(JsonPath.parse(response).read("store.storeSchedule.closingTime"), aStore.storeSchedule().closingTime().toString());
        assertEquals(JsonPath.parse(response).read("store.storeSchedule.openingDays"), storeTestHelper.storeOpeningDaysToString(aStore));
    }

    @Test
    public void addingAStoreAdminWithAnEmptyUsernameReturnsBadRequest() throws Exception {
        StoreAdminUser aStoreAdmin = StoreAdminBuilder.aStoreAdmin().withEmptyUsername();

        String content = objectMapper.writeValueAsString(aStoreAdmin);
        MvcResult mvcResult = mockMvc.perform(post("/storeAdmin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void addingAStoreAdminUserWhichIsAlreadyRegisterUsernameReturnsBadRequest() throws Exception {
        StoreAdminUser aStoreAdmin = StoreAdminBuilder.aStoreAdmin().build();
        when(userServiceMock.addStoreAdmin(any())).thenThrow(new NotAvailableUserNameException());
        when(storeService.addStore(any())).thenReturn(aStoreAdmin.store());

        String content = objectMapper.writeValueAsString(aStoreAdmin);
        MvcResult mvcResult = mockMvc.perform(post("/storeAdmin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void addingAStoreAdminWithAnEmptyPasswordReturnsBadRequest() throws Exception {
        StoreAdminUser aStoreAdmin = StoreAdminBuilder.aStoreAdmin().withEmptyPassword();

        String content = objectMapper.writeValueAsString(aStoreAdmin);
        MvcResult mvcResult = mockMvc.perform(post("/storeAdmin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    private JSONObject generateStoreAdminBody(StoreAdminUser storeAdminUser) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", storeAdminUser.username());
        jsonObject.put("password", storeAdminUser.password());

        return jsonObject;
    };

    private JSONObject generateClientUserBody(ClientUser clientUser) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", clientUser.username());
        jsonObject.put("password", clientUser.password());

        return jsonObject;
    }

    private ClientUser addIdToClientUser(ClientUser aUser){
        aUser.setId(new Random().nextLong());
        return aUser;
    }

}
