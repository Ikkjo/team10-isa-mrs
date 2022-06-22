package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.ClientReportDto;
import team10.app.model.ClientReport;
import team10.app.repository.ClientReportRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientReportService {

    private final ClientReportRepository clientReportRepository;

    public List<ClientReport> getClientReports() {
        return clientReportRepository.findAll();
    }

    public List<ClientReportDto> getClientReportDtoList(List<ClientReport> clientReports) {
        return clientReports.stream()
                .map(ClientReportDto::new)
                .collect(Collectors.toList());
    }
}
