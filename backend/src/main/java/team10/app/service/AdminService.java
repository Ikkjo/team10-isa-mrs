package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.dto.*;
import team10.app.model.*;
import team10.app.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import team10.app.util.EmailBuilder;
import team10.app.util.Sorting;
import team10.app.util.Validator;
import team10.app.util.exceptions.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final RegistrationRequestRepository registrationRequestRepository;
    private final DeletionRequestRepository deletionRequestRepository;
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final ClientRepository clientRepository;
    private final EmailService emailService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private final Validator validator;

    public AdminDto getUserDetails(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found!"));
        return new AdminDto(admin);
    }

    public Page<RegistrationRequest> getRegistrationRequests(String sort, int page, int size) {
        Pageable paging = PageRequest.of(page, size, Sort.by(registrationRequestGetSort(sort)));
        return registrationRequestRepository.findAllNotReviewed(paging);
    }

    private List<Sort.Order> registrationRequestGetSort(String sort) {
        List<Sort.Order> orders = new ArrayList<>();
        String[] sortTokens = sort.split(",");
        sortTokens[0] = sortTokens[0].equals("registrationReason")
                ? "description" : "businessClient." + sortTokens[0];
        orders.add(Sorting.getSorting(sortTokens));
        return orders;
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
            System.err.println("Email service not available");
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
            System.err.println("Email service not available");
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
        return admin.getRole() == UserRole.MAIN_ADMIN;
    }

    public Page<DeletionRequest> getDeletionRequests(String sort, int page, int size) {
        Pageable paging = PageRequest.of(page, size, Sort.by(deletionRequestGetSort(sort)));
        return deletionRequestRepository.findAllNotReviewed(paging);
    }

    private List<Sort.Order> deletionRequestGetSort(String sort) {
        List<Sort.Order> orders = new ArrayList<>();
        String[] sortTokens = sort.split(",");
        switch (sortTokens[0]) {
            case "role":
            case "firstName":
            case "lastName":
            case "email":
                sortTokens[0] = "user." + sortTokens[0];
                break;
            default:
                break;
        }
        orders.add(Sorting.getSorting(sortTokens));
        return orders;
    }

    public void acceptDeletionRequest(UUID deletionRequestId, String response) throws EntityNotFoundException, RegistrationRequestReviewedException {
        DeletionRequest dr = deletionRequestRepository.findById(deletionRequestId)
                .orElseThrow( () -> new EntityNotFoundException("Deletion request not found!"));
        if (dr.isReviewed()) {
            throw new DeletionRequestReviewedException("Deletion request already reviewed!");
        }
        deletionRequestRepository.review(deletionRequestId);
        userRepository.deleteUser(dr.getUser().getEmail());
        try {
            emailService.send(
                    dr.getUser().getEmail(),
                    EmailBuilder.getAcceptDeletionEmail(dr.getUser().getFirstName(), dr.getDeletionReason(), response)
            );
        } catch (Exception e) {
            System.err.println("Email service not available");
        }
    }

    public void declineDeletionRequest(UUID deletionRequestId, String response) {
        DeletionRequest dr = deletionRequestRepository.findById(deletionRequestId)
                .orElseThrow( () -> new EntityNotFoundException("Deletion request not found!"));
        if (dr.isReviewed()) {
            throw new DeletionRequestReviewedException("Deletion request already reviewed!");
        }
        deletionRequestRepository.review(deletionRequestId);
        try {
            emailService.send(
                    dr.getUser().getEmail(),
                    EmailBuilder.getDeclineDeletionEmail(dr.getUser().getFirstName(), dr.getDeletionReason(), response)
            );
        } catch (Exception e) {
            System.err.println("Email service not available");
        }
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

    public List<BusinessClientRegistrationRequestNoPasswordDto> getRegistrationRequestsDtoList(
            List<RegistrationRequest> registrationRequests
    )
    {
        return registrationRequests.stream()
                .map(BusinessClientRegistrationRequestNoPasswordDto::new)
                .collect(Collectors.toList());
    }

    public List<DeletionRequestDto> getDeletionRequestsDtoList(List<DeletionRequest> deletionRequests) {
        return deletionRequests.stream()
                .map(DeletionRequestDto::new)
                .collect(Collectors.toList());
    }

    public void penalizeClient(UUID reportId, boolean penalize) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow( () -> new ReportNotFoundException(reportId));
        if (userRepository.existsById(report.getClient().getId()))
            throw new UsernameNotFoundException(report.getClient().getEmail());
        if (penalize)
            clientRepository.addPenalty(report.getClient().getId());
        try {
            if (penalize) {
                emailService.send(
                        report.getRentalEntity().getOwner().getEmail(),
                        EmailBuilder.getClientPenalizedForBusinessClientEmail(
                                report.getRentalEntity().getOwner().getFirstName(),
                                report.getClient().getEmail()
                        ));
                emailService.send(
                        report.getClient().getEmail(),
                        EmailBuilder.getClientPenalizedForClientEmail(
                                report.getClient().getFirstName(),
                                report.getRentalEntity().getTitle()
                        ));
            }
            else {
                emailService.send(
                        report.getRentalEntity().getOwner().getEmail(),
                        EmailBuilder.getClientNotPenalizedForBusinessClientEmail(
                                report.getRentalEntity().getOwner().getFirstName(),
                                report.getClient().getEmail()
                        ));
                emailService.send(
                        report.getClient().getEmail(),
                        EmailBuilder.getClientNotPenalizedForClientEmail(
                                report.getClient().getFirstName(),
                                report.getRentalEntity().getTitle()
                        ));
            }
        } catch (Exception e) {
            System.err.println("Email service not available.");
        }
    }
}
