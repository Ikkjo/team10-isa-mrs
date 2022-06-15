package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.dto.AdminDto;
import team10.app.dto.AdminRegistrationDto;
import team10.app.dto.BusinessClientRegistrationRequestNoPasswordDto;
import team10.app.model.*;
import team10.app.repository.AdminRepository;
import team10.app.repository.DeletionRequestRepository;
import team10.app.repository.RegistrationRequestRepository;
import team10.app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import team10.app.util.EmailBuilder;
import team10.app.util.Validator;
import team10.app.util.exceptions.DeletionRequestReviewedException;
import team10.app.util.exceptions.EmailTakenException;
import team10.app.util.exceptions.PasswordInvalidException;
import team10.app.util.exceptions.RegistrationRequestReviewedException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final RegistrationRequestRepository registrationRequestRepository;
    private final DeletionRequestRepository deletionRequestRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private final Validator validator;

    public AdminDto getUserDetails(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found!"));
        return new AdminDto(admin);
    }

    public List<BusinessClientRegistrationRequestNoPasswordDto> getRegistrationRequests() {
        List<BusinessClientRegistrationRequestNoPasswordDto> regReqs = new ArrayList<>();
        for (RegistrationRequest rr : registrationRequestRepository.findNotReviewed()) {
            regReqs.add(new BusinessClientRegistrationRequestNoPasswordDto(rr));
        }
        return regReqs;
    }

    public void acceptBusinessClient(UUID registrationRequestId) throws EntityNotFoundException, RegistrationRequestReviewedException {
        RegistrationRequest rr = registrationRequestRepository.findById(registrationRequestId)
                .orElseThrow(() -> new EntityNotFoundException("Registration request not found!"));
        if (rr.isReviewed()) {
            throw new RegistrationRequestReviewedException("Registration request already reviewed!");
        }
        userRepository.enableUser(rr.getBusinessClient().getEmail());
        registrationRequestRepository.review(registrationRequestId);
        try {
            emailService.send(
                    rr.getBusinessClient().getEmail(),
                    EmailBuilder.getAcceptEmail(rr.getBusinessClient().getFirstName())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void declineBusinessClient(UUID registrationRequestId, String declineReason) throws EntityNotFoundException, RegistrationRequestReviewedException {
        RegistrationRequest rr = registrationRequestRepository.findById(registrationRequestId)
                .orElseThrow(() -> new EntityNotFoundException("Registration request already reviewed!"));
        if (rr.isReviewed()) {
            throw new RegistrationRequestReviewedException("");
        }
        registrationRequestRepository.review(registrationRequestId);
        try {
            emailService.send(
                    rr.getBusinessClient().getEmail(),
                    EmailBuilder.getDeclineEmail(rr.getBusinessClient().getFirstName(), declineReason)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AdminDto createAdmin(AdminRegistrationDto adminDto) throws EmailTakenException {
        if (userRepository.findByEmail(adminDto.getEmail()).isPresent())
            throw new EmailTakenException(adminDto.getEmail());
        Admin admin = userService.buildAdmin(adminDto);
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        adminRepository.save(admin);
        return new AdminDto(admin);
    }


    public boolean isMainAdmin(String email) {
        Admin admin = (Admin) userRepository.findByEmail(email)
                .orElseGet( () -> {
                    throw new UsernameNotFoundException("User not found!");
                });
        return admin.getRole() == UserRole.ADMIN;
    }

    public void acceptDeletionRequest(UUID deletionRequestId, String response) throws EntityNotFoundException, RegistrationRequestReviewedException {
        DeletionRequest dr = deletionRequestRepository.findById(deletionRequestId)
                .orElseThrow( () -> new EntityNotFoundException("Deletion request not found!"));
        if (dr.isReviewed()) {
            throw new DeletionRequestReviewedException("Deletion request already reviewed!");
        }
        deletionRequestRepository.review(deletionRequestId);
        userRepository.deleteUser(dr.getUser().getEmail());
        emailService.send(
                dr.getUser().getEmail(),
                EmailBuilder.getAcceptDeletionEmail(dr.getUser().getFirstName(), dr.getDeletionReason(), response)
        );
    }

    public void declineDeletionRequest(UUID deletionRequestId, String response) {
        DeletionRequest dr = deletionRequestRepository.findById(deletionRequestId)
                .orElseThrow( () -> new EntityNotFoundException("Deletion request not found!"));
        if (dr.isReviewed()) {
            throw new DeletionRequestReviewedException("Deletion request already reviewed!");
        }
        deletionRequestRepository.review(deletionRequestId);
        emailService.send(
                dr.getUser().getEmail(),
                EmailBuilder.getDeclineDeletionEmail(dr.getUser().getFirstName(), dr.getDeletionReason(), response)
        );
    }

    public AdminDto verifyAdmin(String email, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        if (passwordEncoder.matches(newPassword, userRepository.getByEmail(email).getPassword()))
            throw new PasswordInvalidException(newPassword);
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found!"));
        admin.setRole(UserRole.ADMIN);
        admin.setPassword(encodedPassword);
        return new AdminDto(admin);
    }
}
