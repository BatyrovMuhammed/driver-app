package peaksoft.driverapp.apis;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.driverapp.dto.client.ClientResponseDto;
import peaksoft.driverapp.dto.client.ClientSaveDto;
import peaksoft.driverapp.dto.client.GetClientDto;
import peaksoft.driverapp.services.ClientService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * @author Beksultan
 */
@RestController
@RequestMapping("api/clients")
@AllArgsConstructor
public class ClientApi {

    private final ClientService clientService;

    @PostMapping("/register")
    @PermitAll
    public ClientResponseDto save(@RequestBody @Valid ClientSaveDto client) {
        return clientService.register(client);
    }

    @GetMapping
    public List<ClientResponseDto> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/find/by")
    public GetClientDto findBy(@RequestParam(required = false) UUID id,
                               @RequestParam(required = false) String email,
                               @RequestParam(required = false) String phoneNumber) {
        return clientService.findBy(id, email, phoneNumber);
    }

    @DeleteMapping("/delete/{clientId}")
    public void deleteById(@PathVariable UUID clientId) {
        clientService.delete(clientId);
    }

    @PutMapping("/update/{clientId}")
    public ClientResponseDto update(@PathVariable UUID clientId,
                                    @RequestBody @Valid ClientSaveDto clientSaveDto) {
        return clientService.update(clientId, clientSaveDto);
    }

}
