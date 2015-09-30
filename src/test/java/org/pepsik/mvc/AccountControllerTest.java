package org.pepsik.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.services.Reworked.AccountService;
import org.pepsik.rest.mvc.Reworked.AccountController;
import org.pepsik.rest.utilities.AccountList;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    public void findAllAccounts() throws Exception {
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
                        hasItems(endsWith("accountA"), endsWith("accountB"))))
                .andExpect(status().isOk());
    }
}
