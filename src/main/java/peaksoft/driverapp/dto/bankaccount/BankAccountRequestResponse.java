package peaksoft.driverapp.dto.bankaccount;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Beksultan
 */
@Builder
@Getter @Setter
public class BankAccountRequestResponse {
    private String name;
    private String accountNumber;
}
