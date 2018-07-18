package simpleatm.model;

/**
 * POJO class for passing data to the front end
 */

import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

@JsonRootName("balance")
public class BalanceDTO implements Serializable {
    private Long currentBalance;
    private Long maxWithdrawal;

    public BalanceDTO(Long balance, Long maxWithdrawal) {
        this.currentBalance = balance;
        this.maxWithdrawal = maxWithdrawal;
    }

    public Long getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Long currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Long getMaxWithdrawal() {
        return maxWithdrawal;
    }

    public void setMaxWithdrawal(Long maxWithdrawal) {
        this.maxWithdrawal = maxWithdrawal;
    }
}
