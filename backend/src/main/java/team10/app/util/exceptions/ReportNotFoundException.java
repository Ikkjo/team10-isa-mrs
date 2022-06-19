package team10.app.util.exceptions;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class ReportNotFoundException extends EntityNotFoundException {
    public ReportNotFoundException(UUID reportId) {
        super("Report not found: "+reportId);
    }
}
