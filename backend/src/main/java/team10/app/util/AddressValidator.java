package team10.app.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.model.Address;
import team10.app.repository.AddressRepository;
import team10.app.repository.AdventureRepository;
import team10.app.repository.ShipRepository;
import team10.app.repository.VacationHomeRepository;

import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class AddressValidator implements Predicate<Address> {

    private final AddressRepository addressRepository;
    private final VacationHomeRepository vacationHomeRepository;
    private final ShipRepository shipRepository;
    private final AdventureRepository adventureRepository;

    @Override
    public boolean test(Address address) {
        return addressRepository
                .getAddressByAddressCityCountry(
                address.getAddress(),
                address.getCity(),
                address.getCountry()).isEmpty();

    }

    public boolean testVacationHome(Address address) {
        return vacationHomeRepository.findByAddress(address.getAddress(), address.getCity(), address.getCountry()).isEmpty();
    }

    public boolean testShip(Address address) {
        return shipRepository.findByAddress(address).isEmpty();
    }

    public boolean testAdventure(Address address) {
        return adventureRepository.findByAddress(address).isEmpty();
    }

}
