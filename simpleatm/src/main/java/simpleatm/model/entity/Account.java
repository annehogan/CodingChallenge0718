package simpleatm.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Account.findByNumberAndPin", query = "SELECT a FROM Account a WHERE accountNumber = (?1) and pin = (?2) ")
public class Account {

    @Id
    @GeneratedValue
    private Long id;
    private Long accountNumber;
    private Long pin;
    private Long balance;
    private Long overdraftAllowance;

    public Account() {
        // empty constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getPin() {
        return pin;
    }

    public void setPin(Long pin) {
        this.pin = pin;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getOverdraftAllowance() {
        return overdraftAllowance;
    }

    public void setOverdraftAllowance(Long overdraftAllowance) {
        this.overdraftAllowance = overdraftAllowance;
    }
}
