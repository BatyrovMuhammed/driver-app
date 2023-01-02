package peaksoft.driverapp.apis;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.driverapp.models.entities.AuthInfo;
import peaksoft.driverapp.services.BankAccountService;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("api/bankAccount")
@AllArgsConstructor
public class BankAccountApi {

    private final BankAccountService bankAccountService;

    @PostMapping("/add/money")
    @PreAuthorize("hasAnyAuthority('DRIVER', 'CLIENT')")
    public Map<String, String> addMoney(Authentication authentication,
                                        @RequestParam BigDecimal bigDecimal) {
        return bankAccountService.addMoney(
                (AuthInfo) authentication.getPrincipal(),
                bigDecimal
                );
    }
}
