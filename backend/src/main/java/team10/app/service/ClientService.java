package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.dto.ClientDto;
import team10.app.model.Client;
import team10.app.model.Loyalty;
import team10.app.model.Reservation;
import team10.app.repository.ClientRepository;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ClientService implements UserDetailsService {

    private final static String CLIENT_NOT_FOUND = "Client with email %s not found";
    private final ClientRepository clientRepository;
    private final LoyaltyProgramsService loyaltyProgramsService;

    public ClientDto getUserDetails(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findClientByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(CLIENT_NOT_FOUND, email)));
        Loyalty l = loyaltyProgramsService.getByLoyaltyPoints(client.getLoyaltyPoints());
        ClientDto clientDto = new ClientDto(client);
        clientDto.setLoyaltyStatus(l.getTitle());
        clientDto.setLoyaltyColor(l.getColor());
        return clientDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return clientRepository.findClientByEmail(email)
                .orElseThrow( () ->
                        new UsernameNotFoundException(String.format(CLIENT_NOT_FOUND, email)));
    }

    public Client getByUsername(String email) {
        return clientRepository.findClientByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format(CLIENT_NOT_FOUND, email)));
    }

    public void addReservation(Client client, Reservation reservation) {
        client.addReservation(reservation);
        clientRepository.saveAndFlush(client);
    }

    public void addPenalty(UUID id) {
        clientRepository.addPenalty(id);
    }

}
