package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.LoyaltyDto;
import team10.app.model.Loyalty;
import team10.app.repository.LoyaltyRepository;
import team10.app.util.exceptions.LoyaltyAlreadyExistsException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoyaltyProgramsService {
    private final LoyaltyRepository loyaltyRepository;

    public List<Loyalty> getLoyaltyPrograms() {
        return loyaltyRepository.findAll();
    }

    public List<LoyaltyDto> getLoyaltyDtoList(List<Loyalty> loyalties) {
        return loyalties.stream()
                .map(LoyaltyDto::new)
                .collect(Collectors.toList());
    }

    public Loyalty getLoyaltyProgram(UUID loyaltyId) {
        return loyaltyRepository.findById(loyaltyId)
                .orElseThrow( () -> new EntityNotFoundException("Loyalty Program with this id doesnt exist: "+loyaltyId));
    }

    public LoyaltyDto getLoyaltyDto(Loyalty loyaltyProgram) {
        return new LoyaltyDto(loyaltyProgram);
    }

    private void loyaltyExists(UUID id) {
        if (loyaltyRepository.existsById(id))
            return;
        throw new EntityNotFoundException("Loyalty program not found.");
    }

    public void updateTitle(UUID loyaltyId, String title) {
        loyaltyExists(loyaltyId);
        loyaltyRepository.updateTitle(loyaltyId, title);
    }

    public void updateAvailableAtPoints(UUID loyaltyId, int points) {
        loyaltyExists(loyaltyId);
        loyaltyRepository.updateAvailableAtPoints(loyaltyId, points);
    }

    public void updateClientDiscount(UUID loyaltyId, double percentage) {
        loyaltyExists(loyaltyId);
        loyaltyRepository.updateClientDiscount(loyaltyId, percentage);
    }

    public void updateBusinessClientCut(UUID loyaltyId, double percentage) {
        loyaltyExists(loyaltyId);
        loyaltyRepository.updateBusinessClientCut(loyaltyId, percentage);
    }

    public void updateBusinessClientPointsPerReservation(UUID loyaltyId, int points) {
        loyaltyExists(loyaltyId);
        loyaltyRepository.updateBusinessClientPointsPerReservation(loyaltyId, points);
    }

    public void updateClientPointsPerReservation(UUID loyaltyId, int points) {
        loyaltyExists(loyaltyId);
        loyaltyRepository.updateClientPointsPerReservation(loyaltyId, points);
    }

    public void updateColor(UUID loyaltyId, String color) {
        loyaltyExists(loyaltyId);
        loyaltyRepository.updateColor(loyaltyId, color);
    }

    public LoyaltyDto addLoyaltyProgram(LoyaltyDto loyaltyDto) {
        if (loyaltyRepository.existsByTitle(loyaltyDto.getTitle()))
            throw new LoyaltyAlreadyExistsException("Loyalty with name: "+loyaltyDto.getTitle());
        Loyalty loyalty = new Loyalty(loyaltyDto);
        loyaltyRepository.save(loyalty);
        return new LoyaltyDto(loyalty);
    }

    public void deleteLoyaltyProgram(UUID id) {
        loyaltyExists(id);
        loyaltyRepository.deleteById(id);
    }
  
    public Loyalty getByLoyaltyPoints(int loyaltyPoints) {
        List<Loyalty> loyalties = loyaltyRepository.getByLoyaltyPoints(loyaltyPoints);
        int max = 0;
        Loyalty loyalty = new Loyalty();
        for (Loyalty l : loyalties) {
            if (l.getAvailableAtPoints() >= max && loyaltyPoints >= l.getAvailableAtPoints()) {
                max = l.getAvailableAtPoints();
                loyalty = l;
            }
        }
        return loyalty;
    }
}
