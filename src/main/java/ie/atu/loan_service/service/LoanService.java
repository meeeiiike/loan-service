package ie.atu.loan_service.service;

import ie.atu.loan_service.client.NotificationClient;
import ie.atu.loan_service.errorHandling.DuplicateExceptionHandling;
import ie.atu.loan_service.errorHandling.NotFoundException;
import ie.atu.loan_service.client.UserClient;
import ie.atu.loan_service.model.Loan;
import ie.atu.loan_service.model.NotificationDTO;
import ie.atu.loan_service.model.UserDTO;
import ie.atu.loan_service.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final UserClient userClient;
    private final LoanRepository loanRepository;
    private final NotificationClient notificationClient;

    public LoanService(UserClient userClient, LoanRepository loanRepository, NotificationClient notificationClient) {
        this.userClient = userClient;
        this.loanRepository = loanRepository;
        this.notificationClient = notificationClient;
    }

    // Defensive Copy of Loan List
    public List<Loan> getLoanList() { return loanRepository.findAll(); }

    // Search By ID
    public Loan getLoanByID(String loanId){
        return loanRepository.findByLoanId(loanId).orElseThrow(() -> new NotFoundException(loanId + " not found"));
    }

    public List<Loan> getDueLoans(){
        return loanRepository.findByDueDateAfter(LocalDate.now());
    }

    public Loan createLoan(Loan loan) {
        if(loanRepository.findByLoanId(loan.getLoanId()).isPresent()){
            throw new DuplicateExceptionHandling(loan.getLoanId() + " Already Exists");
        }
        UserDTO user = userClient.getUserById(loan.getUserId());
        if(user == null){
            throw new NotFoundException(loan.getUserId() + " doesnt Exist");
        }
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusWeeks(1));
        loan.setReminderDate(LocalDate.now().plusWeeks(1).minusDays(1));
        notificationClient.sendNotification(new NotificationDTO(loan.getUserId(), "Loan Created"));
        return loanRepository.save(loan);
    }

    // Add Update

    // Add Delete
}
