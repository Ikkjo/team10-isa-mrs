package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.repository.VacationHomeRepository;
import team10.app.util.Validator;
import team10.app.util.exceptions.VacationHomeBedsInvalidException;
import team10.app.util.exceptions.VacationHomeRoomsInvalidException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class VacationHomeService {

    private final VacationHomeRepository vacationHomeRepository;
    private final Validator validator;

    public void updateRooms(int rooms, UUID id) {
        if (!validator.validateVacationHomeRooms(rooms))
            throw new VacationHomeRoomsInvalidException(rooms);
        vacationHomeRepository.updateRooms(rooms, id);
    }


    public void updateBeds(int beds, UUID id) {
        if (!validator.validateVacationHomeBeds(beds))
            throw new VacationHomeBedsInvalidException(beds);
        vacationHomeRepository.updateBeds(beds, id);
    }
}
