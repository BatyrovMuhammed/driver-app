package peaksoft.driverapp.dto.mapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import peaksoft.driverapp.dto.client.GetClientDto;
import peaksoft.driverapp.models.entities.Client;

/**
 * @author Beksultan
 */
@Component
public class GetClientMapper implements Converter<Client, GetClientDto> {
    @Override
    public GetClientDto convert(Client client) {
        GetClientDto getClientDto = new GetClientDto();
        getClientDto.setId(client.getId());
        getClientDto.setName(client.getName());
        getClientDto.setEmail(client.getAuthInfo().getEmail());
        getClientDto.setPhoneNumber(client.getPhoneNumber());
        return getClientDto;
    }
}
