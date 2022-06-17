package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.dto.ReservationDto;
import team10.app.model.Client;
import team10.app.model.Reservation;
import team10.app.repository.ClientRepository;

@AllArgsConstructor
@Service
public class ClientService implements UserDetailsService {

    private final static String CLIENT_NOT_FOUND = "client with email %s not found";
    private final ClientRepository clientRepository;

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
}
