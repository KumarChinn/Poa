package nl.rabobank.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    String accountNumber;
    AccountType accountType;
    Double balance;
}
