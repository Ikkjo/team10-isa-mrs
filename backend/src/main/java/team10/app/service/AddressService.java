package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.AddressDto;
import team10.app.model.Address;
import team10.app.repository.AddressRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address getAddress(AddressDto addressDto) {
        Optional<Address> address =  addressRepository.getAddressByAddressCityCountry(addressDto.getAddress(), addressDto.getCity(), addressDto.getCountry());
        return address.orElseGet(() -> addressRepository.save(new Address(addressDto)));
    }

}
