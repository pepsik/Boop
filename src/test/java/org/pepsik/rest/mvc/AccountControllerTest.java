package org.pepsik.rest.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.pepsik.core.models.entities.Account;
import org.pepsik.core.services.AccountService;
import org.pepsik.core.services.exceptions.AccountExistsException;
import org.pepsik.rest.utilities.AccountList;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by pepsik on 9/30/2015.
 */
public class AccountControllerTest {
    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    private MockMvc mockMvc;

    private ArgumentCaptor<Account> argumentCaptor;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();

        argumentCaptor = ArgumentCaptor.forClass(Account.class);
    }

    @Test
    public void getAllAccounts() throws Exception {
        List<Account> accounts = new ArrayList<>();

        Account accountA = new Account();
        accountA.setId(1L);
        accountA.setUsername("accountA");
        accountA.setPassword("accountA");
        accounts.add(accountA);

        Account accountB = new Account();
        accountB.setId(2L);
        accountB.setUsername("accountB");
        accountB.setPassword("accountB");
        accounts.add(accountB);

        AccountList accountList = new AccountList(accounts);

        when(accountService.findAllAccounts()).thenReturn(accountList);

        mockMvc.perform(get("/rest/accounts"))
                .andExpect(jsonPath("$.accounts[*].username",
                        hasItems(is("accountA"), is("accountB"))))
                .andExpect(status().isOk());
    }

    @Test
    public void getAccountByUsername() throws Exception {
        Account accountB = new Account();
        accountB.setId(2L);
        accountB.setUsername("accountB");
        accountB.setPassword("accountB");

        when(accountService.findAccountByUsername("accountB")).thenReturn(accountB);

        mockMvc.perform(get("/rest/accounts").param("username", "accountB"))
                .andExpect(jsonPath("$.accounts[0].rid", is(2)))
                .andExpect(jsonPath("$.accounts[0].username", is("accountB")))
                .andExpect(jsonPath("$.accounts[0].password", nullValue()));
    }

    @Test
    public void getAccountById() throws Exception {
        Account accountA = new Account();
        accountA.setId(1L);
        accountA.setUsername("accountA");
        accountA.setPassword("accountA");

        when(accountService.findAccountById(1L)).thenReturn(accountA);

        mockMvc.perform(get("/rest/accounts/1"))
                .andExpect(jsonPath("$.rid", is(1)))
                .andExpect(jsonPath("$.username", is("accountA")))
                .andExpect(jsonPath("$.password", nullValue()));
    }

    @Test
    public void getNonExistAccountById() throws Exception {
        when(accountService.findAccountById(5L)).thenReturn(null);

        mockMvc.perform(get("/rest/accounts/5"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createAccountNonExistingUsername() throws Exception {
        Account createdAccount = new Account();
        createdAccount.setId(2L);
        createdAccount.setUsername("username");
        createdAccount.setPassword("password");

        when(accountService.createAccount(any(Account.class))).thenReturn(createdAccount);

        mockMvc.perform(post("/rest/accounts")
                .content("{\"username\":\"test\",\"password\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is(createdAccount.getUsername())))
                .andExpect(jsonPath("$.password", nullValue()))
                .andExpect(status().isCreated());
    }

    @Test
    public void createAccountExistingUsername() throws Exception {
        when(accountService.createAccount(any(Account.class))).thenThrow(new AccountExistsException());

        mockMvc.perform(post("/rest/accounts")
                .content("{\"username\":\"test\",\"password\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
}
