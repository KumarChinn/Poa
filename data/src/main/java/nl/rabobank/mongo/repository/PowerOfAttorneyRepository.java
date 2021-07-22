package nl.rabobank.mongo.repository;

import nl.rabobank.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerOfAttorneyRepository extends MongoRepository<User, String> {
}
