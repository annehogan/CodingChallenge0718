package simpleatm.model;

/**
 * BalanceDTO - POJO class for passing balance data to the front end
 *
 * @Author Anne Hogan
 * @Copyright 2018
 */

import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceDTO that = (BalanceDTO) o;
        return Objects.equals(currentBalance, that.currentBalance) &&
                Objects.equals(maxWithdrawal, that.maxWithdrawal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentBalance, maxWithdrawal);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BalanceDTO{");
        sb.append("currentBalance=").append(currentBalance);
        sb.append(", maxWithdrawal=").append(maxWithdrawal);
        sb.append('}');
        return sb.toString();
    }
}
