package peaksoft.driverapp.dto.bankaccount;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BankAccountRequestDto {
    private String accountNumber;
    private String fullName;
}
