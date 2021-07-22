package nl.rabobank.service;

import nl.rabobank.authorizations.PowerOfAttorney;
import nl.rabobank.mongo.repository.PowerOfAttorneyRepository;
import nl.rabobank.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for the poa api
 */
@Service
public class PowerOfAttorneyService {
    @Autowired
    private PowerOfAttorneyRepository powerOfAttorneyRepository;

    /**
     * creates new user
     * @param user
     * @return
     */
    public User createNewUser(User user) {
        return powerOfAttorneyRepository.save(user);
    }

    /**
     * Updates user, accounts and poas not updated here
     * @param id
     * @param user
     * @return
     */
    public User updateUserInfo(String id, User user) {
        User existingUser = powerOfAttorneyRepository.findById(id).orElse(null);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setMobile(user.getMobile());
        existingUser.setEmail(user.getEmail());
        return powerOfAttorneyRepository.save(existingUser);
    }

    /**
     * Updates only account
     * @param id
     * @param user
     * @return
     */
    public User updateAccount(String id, User user) {
        User existingUser = powerOfAttorneyRepository.findById(id).orElse(null);
        existingUser.setAccounts(user.getAccounts());
        return powerOfAttorneyRepository.save(existingUser);
    }

    /**
     * Updates onlu poas
     * @param id
     * @param user
     * @return
     */
    public User updatePoa(String id, User user) {
        User existingUser = powerOfAttorneyRepository.findById(id).orElse(null);
        existingUser.setPoas(user.getPoas());
        return powerOfAttorneyRepository.save(existingUser);
    }

    /**
     * get the list of poa for the user
     * @param id
     * @return
     */
    public List<PowerOfAttorney> getPoas(String id) {
        User user = powerOfAttorneyRepository.findById(id).orElse(null);
        List<PowerOfAttorney> pos = user.getPoas();
        return pos;
    }

    public User getUserById(String id) {
        return powerOfAttorneyRepository.findById(id).orElse(null);
    }

}