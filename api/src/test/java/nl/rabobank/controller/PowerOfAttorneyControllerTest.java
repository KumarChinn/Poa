package nl.rabobank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.rabobank.account.Account;
import nl.rabobank.account.AccountType;
import nl.rabobank.authorizations.AuthType;
import nl.rabobank.authorizations.PowerOfAttorney;
import nl.rabobank.mongo.repository.PowerOfAttorneyRepository;
import nl.rabobank.service.PowerOfAttorneyService;
import nl.rabobank.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(PowerOfAttorneyController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
class PowerOfAttorneyControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private PowerOfAttorneyService powerOfAttorneyService;

    @MockBean
    private PowerOfAttorneyRepository powerOfAttorneyRepository;

    private User mockCreateUser;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
        mockCreateUser = new User("123456", "Kumar", "Chinnathambi", "3874658324", "kumar@gmail.com", null, null);
    }

    @AfterEach
    public void tearDown() {
        mockCreateUser = null;
    }

    @Test
    public void createUser() throws Exception {
        Mockito.when(
                powerOfAttorneyService.createNewUser(Mockito.any(User.class))).thenReturn(mockCreateUser);
        String userJson = new ObjectMapper().writeValueAsString(mockCreateUser);
        mockMvc.perform(post("/poa/create-user")
                .content(userJson)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(userJson))
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    void updateUser() throws Exception {
        mockCreateUser.setMobile("6029384441");
        Mockito.when(
                powerOfAttorneyService.updateUserInfo(Mockito.any(String.class), Mockito.any(User.class))).thenReturn(mockCreateUser);
        String userJson = new ObjectMapper().writeValueAsString(mockCreateUser);
        mockMvc.perform(put("/poa/update-user/123456")
                .content(userJson)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(userJson))
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));

    }

    @Test
    void updateAccount() throws Exception {
        List<Account> accounts = Arrays.asList(new Account("ACS93485644", AccountType.SAVINGS, 234.3),
                new Account("ACP65946594", AccountType.PAYMENT, 100.98));
        mockCreateUser.setAccounts(accounts);
        Mockito.when(
                powerOfAttorneyService.updateUserInfo(Mockito.any(String.class), Mockito.any(User.class))).thenReturn(mockCreateUser);
        String userJson = new ObjectMapper().writeValueAsString(mockCreateUser);
        mockMvc.perform(put("/poa/update-user/123456")
                .content(userJson)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(userJson))
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    void updatePoa() throws Exception {
        List<PowerOfAttorney> poas = Arrays.asList(new PowerOfAttorney("324233", Arrays.asList(AuthType.READ, AuthType.WRITE),
                Arrays.asList(new Account("ACP43564564", AccountType.PAYMENT, 234.3))));
        mockCreateUser.setPoas(poas);
        Mockito.when(powerOfAttorneyService.updatePoa(Mockito.any(String.class), Mockito.any(User.class))).thenReturn(mockCreateUser);
        String userJson = new ObjectMapper().writeValueAsString(mockCreateUser);
        mockMvc.perform(put("/poa/update-poa/123456")
                .content(userJson)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(userJson))
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    void getPoas() throws Exception {
        List<PowerOfAttorney> poas = Arrays.asList(new PowerOfAttorney("324233", Arrays.asList(AuthType.READ, AuthType.WRITE),
                Arrays.asList(new Account("ACP43564564", AccountType.PAYMENT, 234.3))));
        mockCreateUser.setPoas(poas);
        Mockito.when(powerOfAttorneyService.getPoas(Mockito.anyString())).thenReturn(poas);
        String poasJson = new ObjectMapper().writeValueAsString(poas);
        mockMvc.perform(get("/poa/get-poas/123456")
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(poasJson))
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    void getUserById() throws Exception {
        Mockito.when(powerOfAttorneyService.getUserById(Mockito.anyString())).thenReturn(mockCreateUser);
        String userJson = new ObjectMapper().writeValueAsString(mockCreateUser);
        mockMvc.perform(get("/poa/get-user/123456")
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(userJson))
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));

    }
}