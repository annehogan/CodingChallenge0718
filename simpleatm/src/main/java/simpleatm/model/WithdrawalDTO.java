package simpleatm.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.List;

@JsonRootName("withdrawal")
public class WithdrawalDTO implements Serializable {

    private List<BanknotePile> banknotePiles;
    private Long remainingBalance;

    public WithdrawalDTO(List<BanknotePile> banknotePiles, Long remainingBalance) {
        this.banknotePiles = banknotePiles;
        this.remainingBalance = remainingBalance;
    }

    public List<BanknotePile> getBanknotePiles() {
        return banknotePiles;
    }

    public void setBanknotePiles(List<BanknotePile> banknotePiles) {
        this.banknotePiles = banknotePiles;
    }

    public Long getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(Long remainingBalance) {
        this.remainingBalance = remainingBalance;
    }
}
