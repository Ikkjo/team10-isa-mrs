package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.dto.BusinessClientDto;
import team10.app.model.BusinessClient;
import team10.app.model.VacationHomeOwner;
import team10.app.repository.BusinessClientRepository;

@Service
@AllArgsConstructor
public class BusinessClientService {

    private final BusinessClientRepository businessClientRepository;

    public BusinessClientDto getUserDetails(String email) throws UsernameNotFoundException {
        BusinessClient businessClient = businessClientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("VacationHomeOwner not found!"));
        return new BusinessClientDto(businessClient);
    }

}
