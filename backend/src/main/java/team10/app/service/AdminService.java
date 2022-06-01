package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team10.app.dto.AdminDto;
import team10.app.model.Admin;
import team10.app.model.RegistrationRequest;
import team10.app.repository.AdminRepository;
import team10.app.repository.RegistrationRequestRepository;
import team10.app.repository.UserRepository;
import team10.app.util.EmailBuilder;
import team10.app.util.Validator;
import team10.app.util.exceptions.EmailTakenException;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final RegistrationRequestRepository registrationRequestRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    private final Validator validator;

    public AdminDto getUserDetails(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found!"));
        return new AdminDto(admin);
    }

    public void acceptBusinessClient(UUID registrationRequestId) throws EntityNotFoundException {
        RegistrationRequest rr = registrationRequestRepository.findById(registrationRequestId)
                .orElseThrow(() -> new EntityNotFoundException("Registration request not found!"));
        userRepository.enableUser(rr.getBusinessClient().getEmail());
        registrationRequestRepository.review(registrationRequestId);
        emailService.send(
                rr.getBusinessClient().getEmail(),
                EmailBuilder.getAcceptEmail(rr.getBusinessClient().getFirstName())
        );
    }

    public void declineBusinessClient(UUID registrationRequestId, String declineReason) throws EntityNotFoundException {
        RegistrationRequest rr = registrationRequestRepository.findById(registrationRequestId)
                .orElseThrow(() -> new EntityNotFoundException("Registration request not found!"));
        registrationRequestRepository.review(registrationRequestId);
        emailService.send(
                rr.getBusinessClient().getEmail(),
                EmailBuilder.getDeclineEmail(rr.getBusinessClient().getFirstName(), declineReason)
        );
    }

    public AdminDto createAdmin(AdminDto adminDto) throws EmailTakenException {
        if (userRepository.findByEmail(adminDto.getEmail()).isPresent())
            throw new EmailTakenException(adminDto.getEmail());
        Admin admin = userService.buildAdmin(adminDto);
        admin.setPassword(bCryptPasswordEncoder.encode(adminDto.getPassword()));
        adminRepository.save(admin);
        return new AdminDto(admin);
    }


    public boolean isMainAdmin(String email) {
        Admin admin = (Admin) userRepository.findByEmail(email)
                .orElseGet( () -> {
                    throw new UsernameNotFoundException("User not found!");
                });
        return admin.isMain();
    }
}
