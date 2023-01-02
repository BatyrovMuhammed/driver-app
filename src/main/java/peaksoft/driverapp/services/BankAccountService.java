package peaksoft.driverapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.driverapp.exceptions.NotFoundException;
import peaksoft.driverapp.models.entities.AuthInfo;
import peaksoft.driverapp.models.entities.BankAccount;
import peaksoft.driverapp.repositories.ClientRepository;
import peaksoft.driverapp.repositories.DriverRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author B.Muhammed
 */
@Service
@AllArgsConstructor
public class BankAccountService {

    private final DriverRepository driverRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public Map<String, String> addMoney(AuthInfo principal, BigDecimal money) {
        BankAccount bankAccount =  switch (principal.getAuthority()) {
            case CLIENT -> clientRepository.findBankAccountByClientEmail(principal.getEmail())
                    .orElseThrow(NotFoundException::new);
            case DRIVER -> driverRepository.findBankAccountByDriverEmail(principal.getEmail())
                    .orElseThrow(NotFoundException::new);
            case ADMIN -> null;
        };

        bankAccount.addMoney(money);

        return Map.of(
                "status", "SUCCESS"
        );
    }
}
