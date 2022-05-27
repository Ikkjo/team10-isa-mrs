package team10.app.util.exceptions;

import team10.app.dto.AddressDto;

public class RentalEntityAddressInvalidException extends RentalEntityDetailsException {
    public RentalEntityAddressInvalidException(AddressDto addressDto) {
        super(String.format("RentalEntity address invalid: %s, %s, %s", addressDto.getAddress(), addressDto.getCity(), addressDto.getCountry()));
    }
}
