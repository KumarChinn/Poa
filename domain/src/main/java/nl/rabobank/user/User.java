package nl.rabobank.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.rabobank.account.Account;
import nl.rabobank.authorizations.PowerOfAttorney;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
@CompoundIndex(name = "account_idx", def = "{'accounts.accountNumber' : 1, 'accounts.accountType' : 1}", unique = true)
@CompoundIndex(name = "poas_idx", def = "{'poas.grantorId' : 1}", unique = true)
public class User {
    @Id
    String id;
    String firstName;
    String lastName;
    String mobile;
    @Indexed(name = "email_index_unique", unique = true)
    String email;
    List<Account> accounts;
    List<PowerOfAttorney> poas;
}
