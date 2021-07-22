package nl.rabobank.authorizations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.rabobank.account.Account;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerOfAttorney
{
    @Field(targetType = FieldType.OBJECT_ID)
    String grantorId;
    List<AuthType> authType;
    List<Account> accounts;
}
