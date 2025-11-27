package ie.atu.loan_service.errorHandling;

public class NotFoundException extends  RuntimeException {
    private String field;

    public NotFoundException(String message, String field) {
        super(message);
        this.field = field;
    }

    public NotFoundException(String message) {
        super(message);
    }

}
