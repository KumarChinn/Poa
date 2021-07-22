package nl.rabobank.mongo.repository;

import nl.rabobank.account.Account;
import nl.rabobank.account.AccountType;
import nl.rabobank.authorizations.AuthType;
import nl.rabobank.authorizations.PowerOfAttorney;
import nl.rabobank.user.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@SpringBootApplication
class PowerOfAttorneyRepositoryTest {

    @Autowired
    private PowerOfAttorneyRepository powerOfAttorneyRepository;

    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = new User();
    }

    @AfterEach
    public void tearDown() {
        powerOfAttorneyRepository.deleteAll();
        mockUser = null;
    }

    @Test
    public void addAndGetUser() {
        User saveduser=powerOfAttorneyRepository.save(mockUser);
        User fetchedUser = powerOfAttorneyRepository.findById(saveduser.getId()).get();
        assertEquals(saveduser.getId(), fetchedUser.getId());
    }

}