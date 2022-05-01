package team10.app.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.model.Address;
import team10.app.repository.AddressRepository;

import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class AddressValidator implements Predicate<Address> {

    private final AddressRepository addressRepository;

    @Override
    public boolean test(Address address) {
        return addressRepository
                .getAddressByAddressCityCountry(
                address.getAddress(),
                address.getCity(),
                address.getCountry()) == null;

    }
}
