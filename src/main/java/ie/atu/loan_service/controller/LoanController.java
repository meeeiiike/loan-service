package ie.atu.loan_service.controller;

import ie.atu.loan_service.client.UserClient;
import ie.atu.loan_service.model.Loan;
import ie.atu.loan_service.model.UserDTO;
import ie.atu.loan_service.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/loan")
@RestController
public class LoanController {

    // Constructor Based Dependency Injection
    private final LoanService loanService;
    @Autowired
    private final UserClient userClient;

    public LoanController(LoanService loanService, UserClient userClient){
        this.loanService = loanService;
        this.userClient = userClient;
    }

    @GetMapping("/returnDueLoans")
    public ResponseEntity<List<Loan>> getReturnDueLoans(){
        return ResponseEntity.ok(loanService.getDueLoans());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDTO> testGetUser(@PathVariable String userId){
        UserDTO fromUsers = userClient.getUserById(userId);
        return ResponseEntity.ok(fromUsers);
    }

    // Get Request to Return List of all Loans
    @GetMapping("/returnAllLoans")
    public ResponseEntity<List<Loan>> getLoanList(){
        return ResponseEntity.ok(loanService.getLoanList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable String loanId){
        return ResponseEntity.ok(loanService.getLoanByID(loanId));
    }

    @PostMapping
    public ResponseEntity<Loan> create(@Valid @RequestBody Loan loan){
        Loan loanCreated = loanService.createLoan(loan);
        return ResponseEntity
                .created(URI.create("/api/loan" + loanCreated.getLoanId()))
                .body(loanCreated);
    }

}