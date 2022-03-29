package peaksoft.driverapp.dto.mapper;

import org.springframework.stereotype.Component;
import peaksoft.driverapp.dto.client.ClientResponseDto;
import peaksoft.driverapp.dto.client.ClientSaveDto;
import peaksoft.driverapp.models.entities.AuthInfo;
import peaksoft.driverapp.models.entities.Authority;
import peaksoft.driverapp.models.entities.BankAccount;
import peaksoft.driverapp.models.entities.Client;

import java.math.BigDecimal;

/**
 * @author Beksultan
 */
@Component
public class ClientMapper implements Converter<Client, ClientSaveDto, ClientResponseDto>{
    @Override
    public Client convert(ClientSaveDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());

        AuthInfo authInfo = new AuthInfo();
        authInfo.setEmail(clientDto.getEmail());
        authInfo.setPassword(clientDto.getPassword());
        authInfo.setAuthority(Authority.CLIENT);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setFullName(clientDto.getName());
        bankAccount.setAccountNumber(clientDto.getAccountNumber());
        bankAccount.setBigDecimal(new BigDecimal(0));

        client.setBankAccount(bankAccount);
        client.setAuthInfo(authInfo);

        return client;
    }

    @Override
    public ClientResponseDto deConvert(Client client) {
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setName(client.getName());
        clientResponseDto.setPhoneNumber(client.getPhoneNumber());
        clientResponseDto.setId(client.getId());
        return clientResponseDto;
    }
}
