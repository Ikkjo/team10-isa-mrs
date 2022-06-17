package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team10.app.dto.AddressDto;
import team10.app.dto.BusinessClientDto;
import team10.app.dto.PasswordChangeDto;
import team10.app.model.Address;
import team10.app.model.BusinessClient;
import team10.app.model.Reservation;
import team10.app.repository.BusinessClientRepository;
import team10.app.repository.UserRepository;
import team10.app.util.Validator;
import team10.app.util.exceptions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BusinessClientService {

    private final BusinessClientRepository businessClientRepository;
    private final AddressService addressService;
    private final Validator validator;

    public BusinessClientDto getUserDetails(String email) throws UsernameNotFoundException {
        BusinessClient businessClient = businessClientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("VacationHomeOwner not found!"));
        return new BusinessClientDto(businessClient);
    }

    public BusinessClient getByEmail(String email) {
        return businessClientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public void updateAddress(AddressDto addressDto, String email) {
        Address address = addressService.getAddress(addressDto);
        businessClientRepository.updateAddress(address, email);
    }

    public void updateDateOfBirth(String dateOfBirth, String email) {
        if (!validator.validateDateOfBirth(dateOfBirth))
            throw new DateOfBirthInvalidException(dateOfBirth);
        businessClientRepository.updateDateOfBirth(dateOfBirth, email);
    }


    public void addReservation(String email, Reservation reservation) {
        BusinessClient businessClient = getByEmail(email);
        businessClient.addReservation(reservation);
        businessClientRepository.saveAndFlush(businessClient);
    }

}
