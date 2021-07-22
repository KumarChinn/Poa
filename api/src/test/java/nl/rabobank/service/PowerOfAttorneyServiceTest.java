package nl.rabobank.service;

import nl.rabobank.account.Account;
import nl.rabobank.account.AccountType;
import nl.rabobank.authorizations.AuthType;
import nl.rabobank.authorizations.PowerOfAttorney;
import nl.rabobank.mongo.repository.PowerOfAttorneyRepository;
import nl.rabobank.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PowerOfAttorneyService.class)
class PowerOfAttorneyServiceTest {

    @MockBean
    private PowerOfAttorneyRepository powerOfAttorneyRepository;

    @Autowired
    private PowerOfAttorneyService powerOfAttorneyService;

    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = new User("23422", "Kumar", "Chinnathambi", "3874658324", "kumar@gmail.com",
                Arrays.asList(new Account("234324324", AccountType.PAYMENT, 234.3)),
                Arrays.asList(new PowerOfAttorney("234233", Arrays.asList(AuthType.READ), Arrays.asList(new Account("234324324", AccountType.PAYMENT, 234.3)))));
    }

    @AfterEach
    public void tearDown() {
        mockUser = null;
    }

    @Test
    void createNewUser() {
        Mockito.when(powerOfAttorneyRepository.save(mockUser)).thenReturn(mockUser);
        powerOfAttorneyService.createNewUser(mockUser);
        Mockito.verify(powerOfAttorneyRepository, Mockito.times(1)).save((mockUser));
    }

    @Test
    void updateUserInfo() {
        Mockito.when(powerOfAttorneyRepository.findById("23422")).thenReturn(Optional.ofNullable(mockUser));
        Mockito.when(powerOfAttorneyRepository.save(mockUser)).thenReturn(mockUser);
        powerOfAttorneyService.updateUserInfo("23422", mockUser);
        assertEquals(powerOfAttorneyService.getUserById(mockUser.getId()), mockUser);
        Mockito.verify(powerOfAttorneyRepository, Mockito.times(1)).save((mockUser));
    }

    @Test
    void updateAccount() {
        Mockito.when(powerOfAttorneyRepository.findById("23422")).thenReturn(Optional.ofNullable(mockUser));
        Mockito.when(powerOfAttorneyRepository.save(mockUser)).thenReturn(mockUser);
        powerOfAttorneyService.updateAccount("23422", mockUser);
        assertEquals(powerOfAttorneyService.getUserById(mockUser.getId()), mockUser);
        Mockito.verify(powerOfAttorneyRepository, Mockito.times(1)).save((mockUser));
    }

    @Test
    void updatePoa() {
        Mockito.when(powerOfAttorneyRepository.findById("23422")).thenReturn(Optional.ofNullable(mockUser));
        Mockito.when(powerOfAttorneyRepository.save(mockUser)).thenReturn(mockUser);
        powerOfAttorneyService.updatePoa("23422", mockUser);
        assertEquals(powerOfAttorneyService.getUserById(mockUser.getId()), mockUser);
        Mockito.verify(powerOfAttorneyRepository, Mockito.times(1)).save((mockUser));
    }

    @Test
    void getPoas() {
        Mockito.when(powerOfAttorneyRepository.findById("23422")).thenReturn(Optional.ofNullable(mockUser));
        List<PowerOfAttorney> poaList = Arrays.asList(new PowerOfAttorney("234233", Arrays.asList(AuthType.READ), Arrays.asList(new Account("234324324", AccountType.PAYMENT, 234.3))));
        assertEquals(powerOfAttorneyService.getPoas(mockUser.getId()), poaList);
    }

    @Test
    void getUserById() {
        Mockito.when(powerOfAttorneyRepository.findById("23422")).thenReturn(Optional.ofNullable(mockUser));
        assertEquals(powerOfAttorneyService.getUserById(mockUser.getId()), mockUser);
    }
}