package team10.app.util.exceptions;

public class RentalEntityRulesOfConductInvalidException extends RentalEntityDetailsException {
    public RentalEntityRulesOfConductInvalidException(String rulesOfConduct) {
        super(String.format("RentalEntity rules of conduct invalid:\n%s", rulesOfConduct));
    }
}
